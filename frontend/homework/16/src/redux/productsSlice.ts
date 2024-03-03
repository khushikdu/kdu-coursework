import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Product } from "../interface/Products";
import { getProducts } from "./thunk/getProducts";

interface ProductState {
  productList: Product[];
  status: "pending" | "fullfilled" | "error";
  error?: string;
}

const initialState: ProductState = {
  productList: [],
  status: "pending",
};

export const productSlice = createSlice({
  name: "products",
  initialState,
  reducers: {
    setData: (state, action) => {
      console.log("here in set data");
      state.productList = action.payload;
      state.status = "fullfilled";
    },
    setError: (state, action: PayloadAction<string>) => {
      state.status = "error";
      state.error = action.payload;
    },
  },
  extraReducers(builder) {
    builder
      .addCase(getProducts.pending, (state) => {
        state.status = "pending";
      })
      .addCase(getProducts.fulfilled, (state, action) => {
        state.productList = action.payload as Product[];
        state.status = "fullfilled";
      })
      .addCase(getProducts.rejected, (state, action) => {
        state.error = action.payload as string;
      });
  },
});

export const { setData, setError } = productSlice.actions;
export default productSlice.reducer;
