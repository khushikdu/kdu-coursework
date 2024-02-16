const express = require('express');
const http = require('http');
const socketIo = require('socket.io');
const cors=require('cors')

const app = express();

const server = http.createServer(app);
const io=new socketIo.Server(server,{
    cors:{
        origin:"*"
    }
});

app.use(cors());

app.use(express.json())

let stockData={
    name: 'Zomato',
    initialPrice: 5000,
};

let stockHistory = [];

app.get('/stock', (req, res) => {
  res.json(stockData);
});

app.get('/history', (req, res) => {
  res.json(stockHistory);
});

io.on('connection', (socket) => {
  socket.emit('initialData', stockData);

  setInterval(() => {
    const priceChange = Math.floor(Math.random() * 500);
    const isIncrease = Math.random() < 0.5;

    stockData.initialPrice = isIncrease
      ? stockData.initialPrice + priceChange
      : stockData.initialPrice - priceChange;

    stockHistory.push({
      timestamp: Date.now(),
      price: stockData.initialPrice,
    });

    io.emit('realTimeUpdate', {
      timestamp: Date.now(),
      price: stockData.initialPrice,
      isIncrease,
    });
  }, 5000);
});

const PORT = 3000;
server.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
