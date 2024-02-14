// npm init -y
// npm install express cors
// npm install -D nodemon
// npm i socket.io

const express=require('express');
const cors=require('cors')
const http=require('http')
const socketIO=require('socket.io');
// const { Socket } = require('net');http://127.0.0.1:5501/index.html

const app=express();

const server=http.createServer(app);
const io=new socketIO.Server(server,{
    cors:{
        origin:"*"
    }
});
app.use(cors());

app.use(express.json())

app.get("/",(req,res) =>{
    res.json({
        msg: "Hello World"
    });
});

io.on("connection",(socket)=>{
    console.log("New User Joined");

    socket.on("message",(payload)=>{
        console.log("Payload",payload);
        io.except(socket.id).emit('new-message',payload)
    })
});

server.listen(5110,()=>{
    console.log('App started on port 5110')
});