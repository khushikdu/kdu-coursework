import express from "express";
import http from "http";
import { Server } from "socket.io";
import cors from "cors";

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
  cors: {
    origin: "http://localhost:5174",
    methods: ["GET", "POST"],
  },
});

app.use(cors());

const userWallets = {};

io.on("connection", (socket) => {
  console.log("Client connected", socket.id);

  userWallets[socket.id] = { balance: 10000, stocks: {} };

  socket.on("disconnect", () => {
    console.log("Client disconnected");
  });

  socket.on("joinRoom", (symbol) => {
    socket.join(symbol);
    console.log(`${socket.id} joined room for ${symbol}`);
  });

  let stockCurrentPrice = 0;
  socket.on("updateStockPrice", ({ symbol, currentPrice }) => {
    stockCurrentPrice = currentPrice;
    console.log(`Received stock price update for ${symbol}: $${currentPrice}`);
  });

  socket.on("getWalletBalance", () => {
    io.to(socket.id).emit("updateWalletBalance", {
      userId: socket.id,
      walletBalance: userWallets[socket.id].balance,
    });
  });
  socket.on("buyStock", ({ symbol, quantity }) => {

    if (quantity > 0) {
      const totalPrice = quantity * stockCurrentPrice;
      if (userWallets[socket.id].balance <= totalPrice) {
        console.log(`${socket.id} Transaction failed: Low Balance`);
        const msg = "Transaction failed: Low Balance";
        socket.emit("failedTransaction", msg);
      } else {
        userWallets[socket.id].balance -= totalPrice;
        userWallets[socket.id].stocks[symbol] =
          (userWallets[socket.id].stocks[symbol] || 0) + parseInt(quantity);

        const timestamp = new Date().toUTCString();
        const userTransaction = `${quantity} stocks BUY\n${timestamp}`;
        console.log(`${socket.id} ${userTransaction}`);

        io.to(socket.id).emit("updateWalletBalance", {
          userId: socket.id,
          walletBalance: userWallets[socket.id].balance,
        });

        socket.emit("userTransactionUpdate", userTransaction);
        socket.to(symbol).emit("broadcastedTransactionUpdate", userTransaction);
        console.log(userWallets[socket.id]);
      }
    }
  });

  socket.on("sellStock", ({ symbol, quantity }) => {
    const availableStocks = userWallets[socket.id].stocks[symbol] || 0;

    if (availableStocks >= quantity && quantity > 0) {
      userWallets[socket.id].balance += quantity * stockCurrentPrice;
      userWallets[socket.id].stocks[symbol] -= parseInt(quantity);

      const timestamp = new Date().toUTCString();
      const userTransaction = `${quantity} stocks SELL\n${timestamp}`;
      console.log(`${socket.id} ${userTransaction}`);

      io.to(socket.id).emit("updateWalletBalance", {
        userId: socket.id,
        walletBalance: userWallets[socket.id].balance,
      });

      socket.emit("userTransactionUpdate", userTransaction);
      socket.to(symbol).emit("broadcastedTransactionUpdate", userTransaction);
      console.log(userWallets[socket.id]);
    } else {
      console.log(
        `${socket.id} Transaction failed: Insufficient stocks or invalid quantity`
      );
      const msg = `Transaction failed: Insufficient stocks or invalid quantity`;
      socket.emit("failedTransaction", msg);
    }
  });
});
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});
