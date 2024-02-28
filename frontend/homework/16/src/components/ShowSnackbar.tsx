import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/store";
import { clearSnackbar } from "../actions/actions";
import { Icon, IconButton } from "@mui/material";
import Snackbar from "@material-ui/core/Snackbar";

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
        horizontal: "left",
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
      message={
        <span id="client-snackbar">
          <Icon>check_circle</Icon>
          {successSnackbarMessage}
        </span>
      }
      action={[
        <IconButton
          key="close"
          aria-label="close"
          color="inherit"
          onClick={handleClose}
        >
          <Icon>close</Icon>
        </IconButton>,
      ]}
    />
  );
}
