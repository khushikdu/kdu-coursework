const express = require('express');
const cors = require('cors');
const http = require('http');
const socketIO = require('socket.io');

const app = express();
const server = http.createServer(app);

const io = new socketIO.Server(server, {
  cors: {
    origin: "*"
  }
});

/**to store all the connected users */
const users={};

app.use(cors());
app.use(express.json());

/**to listen to all the socket connections */
io.on('connection', (socket) => {
  console.log('User connected');


  /**when the user becomes offline */
  socket.on('disconnect', () => {
    console.log('User disconnected');
  });

  /**to handle the event whenever a new user joins */
  socket.on('new_connection_join',(newUsername)=>{
    console.log("new user joined : ",newUsername);
    users[socket.id]=newUsername;
    
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
