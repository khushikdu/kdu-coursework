import React, { useEffect, useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../redux/store";
import { Transactions } from "../../utils/transactionType";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  IconButton,
  TablePagination,
} from "@mui/material";
import FiberManualRecordIcon from "@mui/icons-material/FiberManualRecord";
import Header from "../Header/Header";
import { styles } from "../Header/Header.styles";
import { transactionThunk } from "../../redux/thunk/transactionThunk";
import Filter from "./Filter/Filter";

export function Portfolio() {
  const dispatch = useDispatch();
  const transactions = useSelector(
    (state: RootState) => state.transaction.transactions
  );
  const filter = useSelector((state: RootState) => state.transaction.filter);

  useEffect(() => {
    dispatch(transactionThunk());
  }, [dispatch]);

  const filteredTransactions = useMemo(() => {
    return transactions.filter((transaction) => {
      const stockFilterMatch =
        transaction.stock_name
          .toLowerCase()
          .includes(filter.stockFilter.toLowerCase()) ||
        transaction.stock_symbol
          .toLowerCase()
          .includes(filter.stockFilter.toLowerCase());

      const statusFilterMatch =
        !filter.statusFilter || transaction.status === filter.statusFilter;

      const stockNameMatch =
        filter.selectedStocks.length === 0 ||
        filter.selectedStocks.includes(transaction.stock_name);

      const stockSymbolFilterMatch =
        filter.stockSymbolFilter.length === 0 ||
        filter.stockSymbolFilter.includes(transaction.stock_symbol);

      const startDateMatch =
        !filter.startDate ||
        new Date(transaction.timestamp) >= new Date(filter.startDate);
      const endDateMatch =
        !filter.endDate ||
        new Date(transaction.timestamp) <= new Date(filter.endDate);

      return (
        stockFilterMatch &&
        statusFilterMatch &&
        stockSymbolFilterMatch &&
        stockNameMatch &&
        startDateMatch &&
        endDateMatch
      );
    });
  }, [transactions, filter]);

  const groupedTransactions = useMemo(() => {
    const groupedData: { [date: string]: Transactions[] } = {};
    filteredTransactions.forEach((transaction) => {
      const date = new Date(transaction.timestamp).toLocaleDateString();
      if (!groupedData[date]) {
        groupedData[date] = [];
      }
      groupedData[date].push(transaction);
    });
    return groupedData;
  }, [filteredTransactions]);

  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(30);

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <div style={{}}>
      <div style={{ position: "sticky", top: 8, zIndex: "10" }}>
        <Header />
      </div>
      <div className="main" style={{ ...styles.main, display: "flex" }}>
        <Filter />
        <div
          className="table"
          style={{ width: "75%", position: "sticky", top: 70, zIndex: "0" }}
        >
          <TableContainer component={Paper} style={{ marginTop: "2rem" }}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Date</TableCell>
                  <TableCell>Stock Name</TableCell>
                  <TableCell>Stock Symbol</TableCell>
                  <TableCell>Transaction Price</TableCell>
                  <TableCell>Time</TableCell>
                  <TableCell>Status</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {Object.entries(groupedTransactions)
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map(([date, dateTransactions]) => (
                    <React.Fragment key={date}>
                      <TableRow>
                        <TableCell colSpan={6} style={{ fontWeight: "bold" }}>
                          {date}
                        </TableCell>
                      </TableRow>
                      {dateTransactions.map((transaction) => (
                        <TableRow key={transaction.timestamp}>
                          <TableCell></TableCell>
                          <TableCell>{transaction.stock_name}</TableCell>
                          <TableCell>{transaction.stock_symbol}</TableCell>
                          <TableCell>{transaction.transaction_price}</TableCell>
                          <TableCell>
                            {new Date(transaction.timestamp).toLocaleTimeString(
                              [],
                              {
                                hour: "2-digit",
                                minute: "2-digit",
                              }
                            )}
                          </TableCell>
                          <TableCell>
                            <IconButton>
                              <FiberManualRecordIcon
                                style={{
                                  color:
                                    transaction.status === "Passed"
                                      ? "green"
                                      : "red",
                                }}
                              />
                            </IconButton>
                          </TableCell>
                        </TableRow>
                      ))}
                    </React.Fragment>
                  ))}
              </TableBody>
            </Table>
            <TablePagination
              rowsPerPageOptions={[10, 20, 30, 50, 100]}
              component="div"
              count={Object.entries(groupedTransactions).length}
              rowsPerPage={rowsPerPage}
              page={page}
              onPageChange={handleChangePage}
              onRowsPerPageChange={handleChangeRowsPerPage}
            />
          </TableContainer>
        </div>
      </div>
    </div>
  );
}
