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
const postsList=[
  {"id": "1", "content": "Embarking on new adventures and embracing the journey of life. 🌟🚀"},
  {"id": "2", "content": "Diving into the world of literature and discovering new realms through books. 📚📖"},
  {"id": "3", "content": "Creating a positive impact through acts of kindness and compassion. ❤️🌈"},
  {"id": "4", "content": "Exploring the depths of creativity and expressing through various art forms. 🎨✨"},
  {"id": "5", "content": "Nurturing plants and cultivating a green oasis at home. 🌱🏡"},
  {"id": "6", "content": "Bridging cultures and fostering global connections through travel. ✈️🌍"},
  {"id": "7", "content": "Promoting fitness and well-being through daily workouts and healthy choices. 💪🥗"},
  {"id": "8", "content": "Engaging in mindful practices and finding tranquility in meditation. ☮️🧘"},
  {"id": "9", "content": "Advocating for environmental conservation and sustainable living. 🌿🌍"},
  {"id": "10", "content": "Celebrating the joy of cooking and experimenting with delicious recipes. 🍲👩‍🍳"},
  {"id": "11", "content": "Supporting local communities and small businesses. 🛍️🏪"},
  {"id": "12", "content": "Capturing moments of beauty and wonder through photography. 📷🌄"},
  {"id": "13", "content": "Exploring the wonders of science and technology. 🔬🤖"},
  {"id": "14", "content": "Spreading laughter and joy through humor and positive vibes. 😄🌟"},
  {"id": "15", "content": "Nurturing relationships and cherishing moments with loved ones. 👫❤️"},
  {"id": "16", "content": "Learning new languages and embracing cultural diversity. 🗣️🌏"},
  {"id": "17", "content": "Pursuing a healthy work-life balance and finding joy in everyday tasks. ⚖️😊"},
  {"id": "18", "content": "Adopting a pet companion and experiencing the joys of pet ownership. 🐾🐶"},
  {"id": "19", "content": "Supporting charitable causes and giving back to the community. 🤝🌟"},
  {"id": "20", "content": "Promoting self-love and embracing individuality. 💖🌈"},
  {"id": "21", "content": "Staying curious and constantly learning new things. 🤔📚"},
  {"id": "22", "content": "Engaging in outdoor activities and reconnecting with nature. 🌳🌲"},
  {"id": "23", "content": "Building a positive mindset and overcoming challenges with resilience. 💪🌟"},
  {"id": "24", "content": "Fostering a sense of gratitude and appreciating life's simple pleasures. 🙏😊"},
  {"id": "25", "content": "Sharing moments of joy and inspiration with the world. 🌟😃"}
];


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

app.get('/api/posts', (req, res) => {
  const { pageNumber, pageSize } = req.query;
  const start = (pageNumber - 1) * pageSize;
  const end = start + Number(pageSize);
  const paginatedPosts = postsList.slice(start, end);
  res.json(paginatedPosts);
});


app.get('/api/active-users', (req, res) => {
  const activeUsersArray = Array.from(activeUsers);
  res.json(activeUsersArray);
});


app.post("/api/posts", (req, res) => {
  const { content } = req.body;
  console.log('thi is ser',content);

  if (!content) {
    return res
      .status(400)
      .json({ error: "Content is a required field." });
  }

  const newPost = {
    id: postsList.length + 1,
    content,
  };

  postsList.push(newPost);
  console.log(newPost);
  res.status(201).json({ success: true, data: newPost });
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

