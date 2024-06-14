import { StockName } from "./StockName";
import { StockSymbol } from "./enums/StockSymbol";

export interface Transactions {
    stock_name:        StockName;
    stock_symbol:      StockSymbol;
    transaction_price: number;
    timestamp:         string;
    status:            Status;
}

export enum Status {
    Failed = "Failed",
    Passed = "Passed",
}
