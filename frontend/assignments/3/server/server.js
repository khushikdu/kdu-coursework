import express from "express";
import http from "http";
import { Server } from "socket.io";
import cors from "cors";

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
  cors: {
    origin: "http://localhost:5173",
    methods: ["GET", "POST"],
  },
});

app.use(cors());

io.on("connection", (socket) => {
  console.log("Client connected", socket.id);

  socket.on("disconnect", () => {
    console.log("Client disconnected");
  });

  socket.on("joinRoom", (symbol) => {
    socket.join(symbol);
    console.log(`${socket.id} joined room for ${symbol}`);
  });

  socket.on("buyStock", ({ symbol, quantity }) => {
    const timestamp = new Date().toUTCString();
    const userTransaction = `${quantity} stocks BUY\n${timestamp}`;
    console.log(`${socket.id} ${userTransaction}`);
    console.log(`${socket.id} ${userTransaction}`);

    socket.emit("userTransactionUpdate", userTransaction);

    socket.to(symbol).emit("broadcastedTransactionUpdate", userTransaction);
  });

  socket.on("sellStock", ({ symbol, quantity }) => {
    const timestamp = new Date().toUTCString();
    const userTransaction = `${quantity} stocks SELL\n${timestamp}`;
    console.log(`${socket.id} ${userTransaction}`);

    socket.emit("userTransactionUpdate", userTransaction);

    socket.to(symbol).emit("broadcastedTransactionUpdate", userTransaction);
  });
});
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});
