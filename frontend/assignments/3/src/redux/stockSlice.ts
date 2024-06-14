import { StockDetails } from "../utils/stocksDetailsType";
import { createSlice } from "@reduxjs/toolkit";
import { stockthunk } from "./thunk/stockThunk";

interface StockState {
  stockList: StockDetails[];
  status: "pending" | "fullfilled" | "error";
  wishlist: StockDetails[];
}

const initialState: StockState = {
  stockList: [],
  status: "pending",
  wishlist: [],
};

const stockSlice = createSlice({
  name: "stocks",
  initialState,
  reducers: {
    setStocks: (state, action) => {
      state.stockList = action.payload;
      state.status = "fullfilled";
    },
    addCart: (state, action) => {
      state.wishlist.push(action.payload);
    },
    removeFromCart: (state, action) => {
      state.wishlist = state.wishlist.filter(
        (item) => item.stock_name !== action.payload.stock_name
      );
    },
  },
  extraReducers(builder) {
    builder
      .addCase(stockthunk.pending, (state) => {
        state.status = "pending";
      })
      .addCase(stockthunk.fulfilled, (state, action) => {
        state.stockList = action.payload as StockDetails[];
        state.status = "fullfilled";
      });
  },
});

export const { setStocks, addCart, removeFromCart } = stockSlice.actions;
export default stockSlice.reducer;
