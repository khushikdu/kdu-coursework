import React from "react";
import Header from "../Header/Header";
import { StockDropdown } from "./LiveDetails/LiveDetails";

export function StockPage() {
  return (
    <>
      <Header />
      <StockDropdown />
    </>
  );
}

export default StockPage;
