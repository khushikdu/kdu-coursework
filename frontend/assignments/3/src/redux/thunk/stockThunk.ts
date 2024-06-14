import { createAsyncThunk } from "@reduxjs/toolkit";
import { StockDetails } from "../../utils/stocksDetailsType";

export const stockthunk = createAsyncThunk<StockDetails[], void>(
  "stockThunk",
  async () => {
    try {
      const response = await fetch(
        "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"
      );
      return response.json();
    } catch (error) {
      console.log("Error encountered");
      throw error;
    }
  }
);
