import { useEffect } from "react";
import "./App.css";
import { ProductComponent } from "./components/ProductComponent";
import { useDispatch, useSelector } from "react-redux";
import { ShowSnackbar } from "./components/ShowSnackbar";
import { RootState, AppDispatch } from "./redux/store";
import { showSuccessSnackbar } from "./actions/actions";

function App() {
  const { status, error } = useSelector((state: RootState) => state.products);
  const dispatch: AppDispatch = useDispatch();

  useEffect(() => {
    if (status === "pending") {
      dispatch(showSuccessSnackbar("Products Loading"));
    }
    if (status === "fullfilled") {
      dispatch(showSuccessSnackbar("Product Details Fetched Successfully"));
    }
    if (error != null) {
      dispatch(
        showSuccessSnackbar("Failed to fetch product details: " + error)
      );
    }
  }, [status, dispatch, error]);
  return (
    <>
      <ProductComponent />
      <ShowSnackbar />
    </>
  );
}

export default App;
