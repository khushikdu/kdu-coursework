import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../redux/store";
import {
  setFilter,
  clearFilter,
  setFilterPartial,
} from "../../../redux/transactionSlice";
import { styles } from "./Filter.styles";
import { StockSymbol } from "../../../utils/transactionType";

const Filter: React.FC = () => {
  const dispatch = useDispatch();
  const filter = useSelector((state: RootState) => state.transaction.filter);
  const transactions = useSelector(
    (state: RootState) => state.transaction.transactions
  );

  const handleStockSymFilterChange = (stockSymbol: StockSymbol) => {
    const updatedSelectedStocks = filter.selectedStocks.includes(stockSymbol)
      ? filter.selectedStocks.filter((symbol) => symbol !== stockSymbol)
      : [...filter.selectedStocks, stockSymbol];

    dispatch(setFilterPartial({ selectedStocks: updatedSelectedStocks }));
  };

  const handleFilterInputChange = (value: string) => {
    dispatch(setFilter({ ...filter, stockFilter: value }));
  };

  const handleStatusFilterChange = (value: string | null) => {
    dispatch(setFilter({ ...filter, statusFilter: value }));
  };

  const handleStartDateChange = (value: string | null) => {
    dispatch(setFilter({ ...filter, startDate: value }));
  };

  const handleEndDateChange = (value: string | null) => {
    dispatch(setFilter({ ...filter, endDate: value }));
  };

  const handleClearAllFilters = () => {
    dispatch(clearFilter());
  };

  return (
    <div className="filter-container" style={styles.main}>
      <div style={{ padding: "10px", borderBottom: "1px solid black" }}>
        Filters
        <button
          onClick={handleClearAllFilters}
          style={{
            backgroundColor: "none",
            border: "none",
            float: "right",
            color: "#1971c2",
          }}
        >
          Clear All
        </button>
      </div>

      <div className="filter-section" style={styles.filterSection}>
        <input
          style={{ width: "100%", height: "2rem" }}
          type="text"
          value={filter.stockFilter}
          placeholder="Search by Stock Name/Symbol"
          onChange={(e) => handleFilterInputChange(e.target.value)}
        />
      </div>

      <div className="filter-section" style={styles.filterSection}>
        <div>
          <input
            type="checkbox"
            id="statusAll"
            checked={filter.statusFilter === ""}
            onChange={() => handleStatusFilterChange("")}
          />
          <label htmlFor="statusAll">All</label>
        </div>

        <div>
          <input
            type="checkbox"
            id="statusPassed"
            checked={filter.statusFilter === "Passed"}
            onChange={() => handleStatusFilterChange("Passed")}
          />
          <label htmlFor="statusPassed">Passed</label>
        </div>

        <div>
          <input
            type="checkbox"
            id="statusFailed"
            checked={filter.statusFilter === "Failed"}
            onChange={() => handleStatusFilterChange("Failed")}
          />
          <label htmlFor="statusFailed">Failed</label>
        </div>
      </div>

      <div
        style={{
          padding: "10px",
          borderBottom: "1px solid black",
          display: "flex",
          flexWrap: "wrap",
        }}
      >
        <div className="filter-section">
          <label>Start Date:</label>
          <input
            type="date"
            value={filter.startDate || ""}
            placeholder=""
            onChange={(e) => handleStartDateChange(e.target.value || null)}
          />
        </div>
        <div className="filter-section">
          <label>End Date:</label>
          <input
            type="date"
            value={filter.endDate || ""}
            onChange={(e) => handleEndDateChange(e.target.value || null)}
          />
        </div>
      </div>

      <div
        className="filter-section"
        style={{ ...styles.filterSection, borderBottom: "none" }}
      >
        <label>Stocks: </label>
        {Object.values(StockSymbol).map((stockSymbol) => (
          <div key={stockSymbol}>
            <input
              type="checkbox"
              id={`stock_${stockSymbol}`}
              checked={filter.selectedStocks.includes(stockSymbol)}
              onChange={() => handleStockSymFilterChange(stockSymbol)}
            />
            <label htmlFor={`stock_${stockSymbol}`}>{stockSymbol}</label>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Filter;
