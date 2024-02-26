import "../styles/ProductDetails.scss";
import { useContext } from "react";
import { ProductDetailsContext } from "../App";
import { ProductDetailsType } from "../types/ProductDetailsType";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";

export function ProductDetails() {
  const { allProduct } = useContext(ProductDetailsContext);
  const { id } = useParams<{ id: string }>();

  const selectedProduct: ProductDetailsType | undefined = allProduct.find(
    (product) => {
      console.log("Product ID:", product.id);
      console.log("Target ID:", id);
      return product.id === parseInt(id ?? "", 10);
    }
  );

  return (
    <div className="prod-details">
      <div className="header-area">
        <div className="search-bar">
          <input type="text" name="" id="searchbar" placeholder="Search" />
          <button className="search-btn">Search</button>
        </div>
      </div>

      {selectedProduct ? (
        <div className="all-contents">
          <div className="prod-img">
            <img src={selectedProduct.image} alt={selectedProduct.title} />
          </div>

          <div className="details">
            <h2>Name: {selectedProduct.title}</h2>
            <p className="prod-price">Price : ${selectedProduct.price}</p>
            <h3>Product Description :</h3>
            <p>{selectedProduct.description}</p>
            <p>Rating : {selectedProduct.rating.rate}</p>
            <p>Rating Count : {selectedProduct.rating.count}</p>

            <Link to={`/`}>
              <button className="back-to-home">Back to Products</button>
            </Link>
          </div>
        </div>
      ) : (
        <p>Product not found</p>
      )}
    </div>
  );
}
