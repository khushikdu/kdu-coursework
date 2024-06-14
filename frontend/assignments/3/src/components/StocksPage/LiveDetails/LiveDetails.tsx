import React, { useEffect, useState } from "react";
import { styles } from "./LiveDetails.styles";
import { useParams } from "react-router-dom";
import { RootState } from "../../../redux/store";
import { Candlestick } from "./Candlestick/Candlestick";
import io from "socket.io-client";
import { Transactions } from "../../../utils/transactionType";
import { useDispatch, useSelector } from "react-redux";
import { addTransaction } from "../../../redux/transactionSlice";
import { Status } from "../../../utils/transactionType";
import { StockName } from "../../../utils/StockName";
import { StockSymbol } from "../../../utils/enums/StockSymbol";


export function StockDropdown() {
  const dispatch = useDispatch();
  const transactions = useSelector(
    (state: RootState) => state.transaction.transactions
  );
  const [socket, setSocket] = useState(null);
  const [userTransactions, setUserTransactions] = useState([]);
  const [broadcastedTransactions, setBroadcastedTransactions] = useState([]);
  const [quantity, setQuantity] = useState("");
  const { symbol } = useParams<{ symbol: string }>();

  useEffect(() => {
    const newSocket = io("http://localhost:3000");
    setSocket(newSocket);

    newSocket.on("failedTransaction", (message) => {
      alert(message);
    });

    newSocket.on("userTransactionUpdate", (updatedTransaction) => {
      setUserTransactions((prevTransactions) => [
        ...prevTransactions,
        updatedTransaction,
      ]);
    });

    newSocket.on("broadcastedTransactionUpdate", (updatedTransaction) => {
      setBroadcastedTransactions((prevTransactions) => [
        ...prevTransactions,
        updatedTransaction,
      ]);
    });

    newSocket.emit("joinRoom", symbol);

    return () => {
      newSocket.disconnect();
    };
  }, [symbol]);

  const handleBuyStock = () => {
    if (quantity && socket) {
      socket.emit("buyStock", { symbol, quantity });
    }
  };

  const handleSellStock = () => {
    if (quantity && socket) {
      socket.emit("sellStock", { symbol, quantity });
    }
  };
  const stockList = useSelector((state: RootState) => state.stocks.stockList);

  let selectedStock = useSelector((state: RootState) =>
    state.stocks.stockList.find((stock) => stock.stock_symbol === symbol)
  );

  const [currentPrice, setCurrentPrice] = useState<number>(
    selectedStock?.base_price || 0
  );
  const [priceHistory, setPriceHistory] = useState<number[]>([]);
  const [maxPercentChange, setMaxPercentChange] = useState<number>(0);

  useEffect(() => {
    const updatePrice = () => {
      const priceChange = (Math.random() * 4 - 2) * 10;
      const newPrice = currentPrice + priceChange;

      setCurrentPrice(newPrice);
      setPriceHistory((prevHistory) => [...prevHistory, priceChange]);
      setMaxPercentChange(Math.max(Math.abs(priceChange), maxPercentChange));
    };

    const intervalId = setInterval(updatePrice, 5000);
    return () => clearInterval(intervalId);
  }, [currentPrice, selectedStock]);

  useEffect(() => {
    if (socket && currentPrice > 0) {
      socket.emit("updateStockPrice", { symbol, currentPrice });
    }
  }, [socket, symbol, currentPrice]);

  if (!selectedStock) {
    return <p>No stock selected</p>;
  }
  const priceColor = priceHistory.slice(-1)[0] > 0 ? "green" : "red";
  const arrowDirection = priceHistory.slice(-1)[0] > 0 ? "up" : "down";

  const parseTransaction = (transaction) => {
    const match = transaction.match(/(\d+) stocks (BUY|SELL)\n(.+)/);
    if (match) {
      const quantity = match[1];
      const action = match[2];
      const timestamp = match[3];
      const dateObject = new Date(timestamp);

      const formattedTime = dateObject.toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit",
        hour12: true,
        hourCycle: "h23",
      });

      return { quantity, action, timestamp, formattedTime };
    }
    return null;
  };

  return (
    <div className="main" style={styles.main}>
      <div className="leftSection" style={styles.leftSection}>
        <div className="details" style={styles.details}>
          <div
            className="nameSymbol commonStyleForDetails"
            style={styles.commonStyleForDetails}
          >
            <div style={styles.logo}>{selectedStock.stock_symbol}</div>
            <div style={{ padding: "5px" }}>{selectedStock.stock_name}</div>
          </div>

          <div
            className="price commonStyleForDetails"
            style={{ ...styles.commonStyleForDetails, color: "black" }}
          >
            Price:
            <span style={{ color: priceColor }}>
              ${currentPrice.toFixed(2)}
            </span>
            {arrowDirection === "up" ? (
              <span style={{ color: "green" }}>↑</span>
            ) : (
              <span style={{ color: "red" }}>↓</span>
            )}
          </div>
          <input
            type="text"
            placeholder="Quantity"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            style={{ height: "3rem", marginTop: "0.6rem", textAlign: "center" }}
          />
          <button className="buy" style={styles.buy} onClick={handleBuyStock}>
            Buy
          </button>
          <button
            className="sell"
            style={styles.sell}
            onClick={handleSellStock}
          >
            Sell
          </button>
        </div>

        <div className="graph" style={{ ...styles.graph, overflowX: "auto" }}>
          {priceHistory.map((percentChange, index) => (
            <Candlestick
              key={index}
              percentChange={percentChange}
              maxPercentChange={maxPercentChange}
              color={percentChange > 0 ? "green" : "red"}
            />
          ))}
        </div>
      </div>

      <div className="rightSection" style={styles.rightSection}>
        <div className="history" style={styles.history}>
          History
          {userTransactions.map((transaction, index) => {
            const parsedTransaction = parseTransaction(transaction);
            return parsedTransaction ? (
              <div
                key={index}
                style={{
                  display: "flex",
                  border: "1px solid black",
                  padding: "2px 5px",
                  borderRadius: "15px",
                  margin: "5px",
                }}
              >
                <div>
                  <div style={{ fontWeight: "800", fontSize: "19px" }}>
                    {parsedTransaction.quantity} stocks{" "}
                  </div>
                  <div>{parsedTransaction.timestamp}</div>
                </div>
                <div
                  style={{
                    color: parsedTransaction.action === "BUY" ? "green" : "red",
                    fontWeight: "800",
                    display: "flex",
                    fontSize: "20px",
                    margin: "18px",
                    alignItems: "center",
                  }}
                >
                  {parsedTransaction.action}
                </div>
              </div>
            ) : null;
          })}
        </div>
        <div className="transaction" style={styles.history}>
          Transaction:
          {broadcastedTransactions.map((transaction, index) => {
            const parsedTransaction = parseTransaction(transaction);
            return parsedTransaction ? (
              <div key={index}>
                <p>
                  Khushi
                  {parsedTransaction.action === "BUY" ? " bought " : " sold "}
                  {parsedTransaction.quantity} stocks <br />
                  {parsedTransaction.formattedTime}
                </p>
              </div>
            ) : null;
          })}
        </div>
      </div>
    </div>
  );
}
