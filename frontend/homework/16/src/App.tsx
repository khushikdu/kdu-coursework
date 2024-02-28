import { useEffect } from "react";
import "./App.css";
import { ProductComponent } from "./components/ProductComponent";
import { Provider, useDispatch, useSelector } from "react-redux";
import { store } from "./redux/store";
import { ShowSnackbar } from "./components/ShowSnackbar";
import { RootState, AppDispatch } from "./redux/store";
import { showSuccessSnackbar } from "./actions/actions";

function App() {
  console.log("in useEffect 3");

  const status: string = useSelector(
    (state: RootState) => state.products.status
  );

  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    console.log("in useEffect");
    if (status === "fullfilled") {
      dispatch(showSuccessSnackbar("Product Details Fetched Successfully"));
    }
  }, [dispatch]);
  return (
    <Provider store={store}>
      <ProductComponent />
      <ShowSnackbar />
    </Provider>
  );
}

export default App;
