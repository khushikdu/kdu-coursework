import { AppDispatch, RootState } from "../redux/store";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { getProducts } from "../redux/thunk/getProducts";
import "../styles/ProductComponent.scss";
import { showSuccessSnackbar } from "../actions/actions";

export function ProductComponent() {
  const dispatch: AppDispatch = useDispatch();
  const apiData = useSelector((state: RootState) => state.products.productList);
  console.log(apiData);
  const loadingStatus = useSelector(
    (state: RootState) => state.products.status
  );
  useEffect(() => {
    dispatch(getProducts());
  }, [dispatch]);

  if (loadingStatus === "pending") {
    return (
      <div className="landing-page">
        <div className="header-area"></div>
        <p className="heading">KDU Marketplace</p>
        <div className="products">
          <div className="loader"></div>
        </div>
      </div>
    );
  } else if (loadingStatus === "fullfilled") {
    dispatch(showSuccessSnackbar("Success!"));

    console.log("hetre");
    return (
      <div className="landing-page">
        <div className="header-area"></div>
        <p className="heading">KDU Marketplace</p>
        <div className="products">
          {apiData.map((individual_data) => (
            <div className="indiv-data" key={individual_data.id}>
              <div className="image">
                {" "}
                <img src={individual_data.image} alt="" />
              </div>{" "}
              <p>
                {individual_data.title} - <br />
                <span>Price ${individual_data.price}</span>
              </p>
            </div>
          ))}
        </div>
      </div>
    );
  }
}
