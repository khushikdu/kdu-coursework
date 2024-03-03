import { showSnackbar } from "../redux/snackbarSlice";
import { AppDispatch } from "../redux/store";

export const showSuccessSnackbar = (message: string) => {
  return (dispatch: AppDispatch) => {
    dispatch(showSnackbar({ type: "SNACKBAR_SHOW", message: message }));
  };
};

export const clearSnackbar = () => {
  return (dispatch: AppDispatch) => {
    dispatch(showSnackbar({ type: "SNACKBAR_DISSAPPEAR", message: "" }));
  };
};
