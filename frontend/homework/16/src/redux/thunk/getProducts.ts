import { createAsyncThunk } from "@reduxjs/toolkit";

export const getProducts = createAsyncThunk("getProducts", async () => {
  try {
    console.log("here");
    const response = await fetch("https://fakestoreapi.com/products");
    return response.json();
  } catch {
    console.log("Error encountered");
  }
});
