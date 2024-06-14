import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { styles } from "./AllStocksComponents.styles";
import { RootState } from "../../../redux/store";
import { stockthunk } from "../../../redux/thunk/stockThunk";
import { StockDetails } from "../../../utils/stocksDetailsType";
import Pagination from "@mui/material/Pagination";
import Stack from "@mui/material/Stack";
import IconButton from "@mui/material/IconButton";
import AddIcon from "@mui/icons-material/Add";
import DoneIcon from "@mui/icons-material/Done";
import ClearIcon from "@mui/icons-material/Clear";
import { addCart, removeFromCart } from "../../../redux/stockSlice";
import { Link } from "react-router-dom";

const ITEMS_PER_PAGE = 7;

const AllStockComponents: React.FC = () => {
  const dispatch = useDispatch();
  const stocks = useSelector<RootState, StockDetails[]>(
    (state) => state.stocks.stockList
  );
  const wishlistedStock = useSelector<RootState, StockDetails[]>(
    (state) => state.stocks.wishlist
  );

  const [currentPage, setCurrentPage] = useState(1);
  const [activeSection, setActiveSection] = useState("explore");
  const [list, setList] = useState<StockDetails[]>([...stocks]);

  const [addedStocks, setAddedStocks] = useState<string[]>([]);

  useEffect(() => {
    dispatch(stockthunk());
  }, [dispatch]);

  useEffect(() => {
    setList(
      activeSection === "explore"
        ? [...stocks].sort((a, b) => a.stock_name.localeCompare(b.stock_name))
        : [...wishlistedStock].sort((a, b) =>
            a.stock_name.localeCompare(b.stock_name)
          )
    );
  }, [activeSection, stocks, wishlistedStock]);

  const indexOfLastItem = currentPage * ITEMS_PER_PAGE;
  const indexOfFirstItem = indexOfLastItem - ITEMS_PER_PAGE;
  const currentStocks = list.slice(indexOfFirstItem, indexOfLastItem);

  const handlePageChange = (
    event: React.ChangeEvent<unknown>,
    page: number
  ) => {
    setCurrentPage(page);
  };

  const handleAddClick = (stock: StockDetails) => {
    if (addedStocks.includes(stock.stock_name)) {
      dispatch(removeFromCart(stock));
      setAddedStocks((prev) =>
        prev.filter((stockName) => stockName !== stock.stock_name)
      );
    } else {
      dispatch(addCart(stock));
      setAddedStocks((prev) => [...prev, stock.stock_name]);
    }
  };

  const handleWatchlistClick = (stock: StockDetails) => {
    if (addedStocks.includes(stock.stock_name)) {
      dispatch(removeFromCart(stock));
      setAddedStocks((prev) =>
        prev.filter((stockName) => stockName !== stock.stock_name)
      );
    } else {
      dispatch(addCart(stock));
      setAddedStocks((prev) => [...prev, stock.stock_name]);
    }
  };

  const handleTabClick = (section: string) => {
    setActiveSection(section);
  };

  return (
    <div>
      <div style={styles.main}>
        <div className="exploreWatchlist" style={styles.exploreWatchlist}>
          <button
            style={styles.buttonStyle}
            onClick={() => handleTabClick("explore")}
            className={activeSection === "explore" ? "activeTab" : ""}
          >
            Explore
          </button>
          <button
            style={styles.buttonStyle}
            onClick={() => handleTabClick("watchlist")}
            className={activeSection === "watchlist" ? "activeTab" : ""}
          >
            My Watchlist
          </button>
        </div>

        <div
          className="all-stocks-container"
          style={{
            border: "1px solid black",
            borderRadius: "25px",
            margin: "2rem 8rem",
            padding: "1rem",
          }}
        >
          {stocks.length === 0 ? (
            <p>Loading...</p>
          ) : (
            <>
              {
                <div className="row" style={styles.row}>
                  <div
                    className="stockCell"
                    style={{ ...styles.stockCell, margin: "0 3rem" }}
                  >
                    <div>Company</div>
                    <div>Base Price</div>
                  </div>
                  <div
                    className="watchlist"
                    style={{
                      width: "20%",
                      marginTop: "15px",
                      fontSize: "18px",
                    }}
                  >
                    Watchlist
                  </div>
                </div>
              }
              <div
                className="allStocksFromStore"
                style={styles.allStocksFromStore}
              ></div>

              <div
                className="allStocksFromStore"
                style={styles.allStocksFromStore}
              >
                {currentStocks.map((stock) => (
                  <div
                    key={stock.stock_name}
                    className="row"
                    style={styles.row}
                  >
                    <div className="stockCell" style={styles.stockCell}>
                      <div>
                        <Link
                          style={{ textDecoration: "none", color: "inherit" }}
                          to={`/stock/${stock.stock_symbol}`}
                          key={stock.stock_symbol}
                        >
                          {stock.stock_symbol}
                        </Link>
                      </div>
                      <div>{stock.base_price}</div>
                    </div>
                    {activeSection === "explore" && (
                      <div className="add" style={styles.add}>
                        <IconButton
                          style={{
                            borderRadius: "50%",
                            border: addedStocks.includes(stock.stock_name)
                              ? "none"
                              : "1px solid #1971c2",
                            color: addedStocks.includes(stock.stock_name)
                              ? "red"
                              : "#1971c2",
                            padding: "0px",
                            margin: "15px 0 0 5rem ",
                          }}
                          onClick={() => handleAddClick(stock)}
                        >
                          {addedStocks.includes(stock.stock_name) ? (
                            <ClearIcon />
                          ) : (
                            <AddIcon />
                          )}
                        </IconButton>
                      </div>
                    )}
                    {activeSection === "watchlist" &&
                      addedStocks.includes(stock.stock_name) && (
                        <div className="doneIcon">
                          <DoneIcon
                            style={styles.doneIcon}
                            onClick={() => handleWatchlistClick(stock)}
                          />
                        </div>
                      )}
                  </div>
                ))}
              </div>
              <Stack
                spacing={2}
                alignItems="center"
                justifyContent="center"
                sx={{ marginTop: 2 }}
              >
                <Pagination
                  count={Math.ceil(list.length / ITEMS_PER_PAGE)}
                  page={currentPage}
                  onChange={handlePageChange}
                  color="primary"
                />
              </Stack>
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default AllStockComponents;
