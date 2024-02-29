import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/store";
import { clearSnackbar } from "../actions/actions";
import { Icon } from "@mui/material";
import IconButton from "@mui/material/IconButton";
import Snackbar from "@mui/material/Snackbar";

export function ShowSnackbar() {
  const dispatch: AppDispatch = useDispatch();
  const { successSnackbarMessage, successSnackbarOpen } = useSelector(
    (state: RootState) => state.snackbar
  );

  function handleClose() {
    dispatch(clearSnackbar());
  }

  return (
    <Snackbar
      anchorOrigin={{
        vertical: "bottom",
        horizontal: "center",
      }}
      open={successSnackbarOpen}
      autoHideDuration={4000}
      onClose={handleClose}
      aria-describedby="client-snackbar"
      ContentProps={{
        style: {
          backgroundColor: "green",
        },
      }}
      message={<span id="client-snackbar">{successSnackbarMessage}</span>}
      action={[
        <IconButton
          key="close"
          aria-label="close"
          color="inherit"
          onClick={handleClose}
        >
          <Icon>x</Icon>
        </IconButton>,
      ]}
    />
  );
}
