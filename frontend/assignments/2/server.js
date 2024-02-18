const express = require('express');
const cors = require('cors');
const http = require('http');
const socketIO = require('socket.io');
const { loginUser } = require('./userRoutes'); 
const User = require('./user'); 
const { log } = require('console');

const app = express();
const server = http.createServer(app);

const io = new socketIO.Server(server, {
  cors: {
    origin: "*"
  }
});

/**to store all the connected users */
const activeUsers = new Set();
const userObj = new User();

let currentUser = "";

app.use(cors());
app.use(express.json());

app.post("/api/user/login",(req,res)=>{
  const { username, password } = req.body;
  currentUser = username;

  const user = userObj.verifyUser(username, password);
  if (user.user_name == username) {
    console.log("fetching vaidation");
    activeUsers.add(username);
    res.json({ message: "Login successful", user });
    console.log("response successful");
  } else {
    res.status(401).json({ error: "Invalid username or password" });
  }
});

app.get('/api/active-users', (req, res) => {
  const activeUsersArray = Array.from(activeUsers);
  res.json(activeUsersArray);
});


/**to listen to all the socket connections */
io.on('connection', (socket) => {
  console.log('User connected');

  /**when the user becomes offline */
  socket.on('disconnect', () => {
    console.log('User disconnected');
    activeUsers.delete(socket.username);

    io.emit(
      "activeUsers",
      Array.from(activeUsers)
  );
  });

  /**to handle the event whenever a new user joins */
  socket.on('new_connection_join',(newUsername)=>{
    console.log("new user joined : ",newUsername);
    
    socket.broadcast.emit('user_joined',newUsername);
    /**broadcast can be used in place of .except*/
  });

  socket.on('send',(message)=>{
    socket.broadcast.emit('receive',message);
  });

});

const PORT = process.env.PORT || 8080;

server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
