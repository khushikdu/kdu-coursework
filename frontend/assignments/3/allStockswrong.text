// import React, { useEffect, useState } from "react";
// import { useDispatch, useSelector } from "react-redux";
// import { styles } from "./AllStocksComponents.styles";
// import { RootState } from "../../redux/store";
// import { stockthunk } from "../../redux/thunk/stockThunk";
// import { StockDetails } from "../../utils/stocksDetailsType";
// import Pagination from "@mui/material/Pagination";
// import Stack from "@mui/material/Stack";
// import IconButton from "@mui/material/IconButton";
// import AddIcon from "@mui/icons-material/Add";
// import { addCart } from "../../redux/stockSlice";
// import { Link } from "react-router-dom";
// import Header from "../Header/Header";

// const ITEMS_PER_PAGE = 7;

// const AllStockComponents: React.FC = () => {
//   const dispatch = useDispatch();
//   const stocks = useSelector<RootState, StockDetails[]>(
//     (state) => state.stocks.stockList
//   );
//   const wishlistedStock = useSelector<RootState, StockDetails[]>(
//     (state) => state.stocks.wishlist
//   );

//   const [currentPage, setCurrentPage] = useState(1);
//   const [list, setList] = useState<StockDetails[]>([...stocks]);

//   useEffect(() => {
//     dispatch(stockthunk());
//     setList([...stocks])
//   }, [dispatch]);

//   const indexOfLastItem = currentPage * ITEMS_PER_PAGE;
//   const indexOfFirstItem = indexOfLastItem - ITEMS_PER_PAGE;
//   const currentStocks = stocks.slice(indexOfFirstItem, indexOfLastItem);

//   const handlePageChange = (
//     event: React.ChangeEvent<unknown>,
//     page: number
//   ) => {
//     setCurrentPage(page);
//   };

//   const handleAddClick = (stocks: StockDetails) => {
//     dispatch(addCart(stocks));
//     console.log(wishlistedStock);
//   };

//   return (
//     <div >
//       <Header />

//      <div style={styles.main}>
//      <div className="exploreWatchlist" style={styles.exploreWatchlist}>
//         <div style={styles.exploreWatchlist}>
//           <div className="explore" style={styles.exploreWatchlist}>
//             <button>Explore</button>
//           </div>
//         </div>
//         <div style={styles.exploreWatchlist}>
//           <div className="myWatchlist" style={styles.exploreWatchlist}>
//             <button>My Watchlist</button>
//           </div>
//         </div>
//       </div>

//       <div
//         className="all-stocks-container"
//         style={{
//           border: "1px solid black",
//           borderRadius: "25px",
//           margin: "2rem 8rem",
//           padding: "1rem",
//         }}
//       >
//         {stocks.length === 0 ? (
//           <p>Loading...</p>
//         ) : (
//           <>
//             <div
//               className="allStocksFromStore"
//               style={styles.allStocksFromStore}
//             >
//               {
//                 <div className="row" style={styles.row}>
//                   <div className="stockCell" style={styles.stockCell}>
//                     <div>Company</div>
//                     <div>Base Price</div>
//                   </div>
//                   <div
//                     className="watchlist"
//                     style={{
//                       width: "20%",
//                       marginTop: "15px",
//                       fontSize: "18px",
//                     }}
//                   >
//                     Watchlist
//                   </div>
//                 </div>
//               }
//             </div>

//             <div
//               className="allStocksFromStore"
//               style={styles.allStocksFromStore}
//             >
//               {currentStocks.map((stock) => (
//                 <div key={stock.stock_name} className="row" style={styles.row}>
//                   <div className="stockCell" style={styles.stockCell}>
//                     <div>{stock.stock_symbol} </div>
//                     <div>{stock.base_price}</div>
//                   </div>
//                   <div className="add" style={styles.add}>
//                     <IconButton
//                       style={{
//                         borderRadius: "50%",
//                         border: "1px solid #1971c2",
//                         color: "#1971c2",
//                         padding: "0px",
//                         marginTop: "15px",
//                       }}
//                       onClick={() => handleAddClick(stock)}
//                     >
//                       <AddIcon />
//                     </IconButton>
//                   </div>
//                 </div>
//               ))}
//             </div>
//             <Stack
//               spacing={2}
//               alignItems="center"
//               justifyContent="center"
//               sx={{ marginTop: 2 }}
//             >
//               <Pagination
//                 count={Math.ceil(stocks.length / ITEMS_PER_PAGE)}
//                 page={currentPage}
//                 onChange={handlePageChange}
//                 color="primary"
//               />
//             </Stack>
//           </>
//         )}
//       </div>
//      </div>
//     </div>
//   );
// };

// export default AllStockComponents;