import { configureStore } from "@reduxjs/toolkit";
import stockReducer from "./stockSlice";
import transactionReducer from "./transactionSlice";

export const store = configureStore({
  reducer: {
    stocks: stockReducer,
    transaction: transactionReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
