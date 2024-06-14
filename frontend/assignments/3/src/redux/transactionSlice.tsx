import { createAction, createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Transactions } from '../utils/transactionType';
import { transactionThunk } from './thunk/transactionThunk';

export const setFilterPartial = createAction<Partial<FilterState>>(
  'transaction/setFilterPartial'
);
export interface FilterState {
  stockFilter: string;
  statusFilter: string | null;
  selectedStocks: string[];
  startDate: string | null;
  endDate: string | null;
  stockSymbolFilter: string;
}

interface TransactionState {
  transactions: Transactions[];
  status: 'pending' | 'fullfilled' | 'error';
  filter: FilterState;
}

const initialState: TransactionState = {
  transactions: [],
  status: 'pending',
  filter: {
    stockFilter: '',
    statusFilter: null,
    selectedStocks: [],
    startDate: null,
    endDate: null,
    stockSymbolFilter: ''
  },
};

const transactionSlice = createSlice({
  name: 'transaction',
  initialState,
  reducers: {
    setTransactions: (state, action: PayloadAction<Transactions[]>) => {
      state.transactions = action.payload;
    },
    setFilter: (state, action: PayloadAction<FilterState>) => {
      state.filter = action.payload;
    },
    clearFilter: (state) => {
      state.filter = initialState.filter;
    },
    addTransaction:(state,action:PayloadAction<Transactions>)=>{
      state.transactions.push(action.payload);
    }
  },
  extraReducers(builder) {
    builder
      .addCase(transactionThunk.pending, (state) => {
        state.status = 'pending';
      })
      .addCase(transactionThunk.fulfilled, (state, action) => {
        state.transactions = action.payload as Transactions[];
        state.status = 'fullfilled';
      }).addCase(setFilterPartial, (state, action) => {
        state.filter = Object.assign({}, state.filter, action.payload);
      });
  },
});

export const { setTransactions, setFilter, clearFilter, addTransaction } = transactionSlice.actions;
export default transactionSlice.reducer;
