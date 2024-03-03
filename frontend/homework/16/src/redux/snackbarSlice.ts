import { createSlice, PayloadAction } from "@reduxjs/toolkit";
interface MessageInterface {
  type: string;
  message: string;
}
export interface ISnackbar {
  successSnackbarOpen: boolean;
  successSnackbarMessage: string;
}

const initialState: ISnackbar = {
  successSnackbarOpen: true,
  successSnackbarMessage: "",
};

export const snackbarSlice = createSlice({
  name: "snackbar",
  initialState,
  reducers: {
    showSnackbar: (state, action: PayloadAction<MessageInterface>) => {
      switch (action.payload.type) {
        case "SNACKBAR_SHOW":
          return {
            ...state,
            successSnackbarOpen: true,
            successSnackbarMessage: action.payload.message,
          };
        case "SNACKBAR_DISSAPPEAR":
          return {
            ...state,
            successSnackbarOpen: false,
            errorSnackBarOpen: false,
            infoSnackBarOpen: false,
          };
        default:
          return state;
      }
    },
  },
});

export const { showSnackbar } = snackbarSlice.actions;
export default snackbarSlice.reducer;
