import { createAsyncThunk } from "@reduxjs/toolkit";
import { Transactions } from "../../utils/transactionType";

export const transactionThunk = createAsyncThunk<Transactions[], void>(
  "transactionThunk",
  async () => {
    try {
      const response = await fetch(
        "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json"
        );
      return response.json();
    } catch (error) {
      console.log("Error encountered");
      throw error;
    }
  }
);
