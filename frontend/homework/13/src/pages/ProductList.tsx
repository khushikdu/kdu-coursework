import { ProductDetailsType } from "../types/ProductDetailsType";
import { useState, useContext } from "react";
import { ProductDetailsContext } from "../App";
import { Link } from "react-router-dom";
import "../styles/ProductList.scss";

export function ProductList() {
  const { allProduct, searchQuery, handleSearch, filterQuery, handleFilter } =
    useContext(ProductDetailsContext);

  const [searchResult, setSearchResult] = useState<ProductDetailsType[]>([]);
  const [displayResult, setDisplayResult] = useState(false);

  const [modifiedResult, setModifiedResult] = useState<ProductDetailsType[]>([...allProduct]);
  //handling search
  const handleSearchButtonClick = () => {
    const result = modifiedResult.filter((item) =>
      item.title.toLowerCase().includes(searchQuery.toLowerCase())
    );
    setSearchResult(result);
    setModifiedResult(result);
    setDisplayResult(true);
  };

  //handling filter
  const handleFilterChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedCategory = e.target.value;
    handleFilter(selectedCategory);
    const result = modifiedResult.filter((item) =>
      item.category.toLowerCase().includes(selectedCategory.toLowerCase())
    );
    setSearchResult(result);
    setDisplayResult(true);
    setModifiedResult(result);
    console.log(searchResult);
  };

  //handling sort
  const handleSortChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedSort = e.target.value;
    const sortedProducts = [...modifiedResult];

    if (selectedSort === "ASC") {
      sortedProducts.sort((a, b) => a.price - b.price);
    } else if (selectedSort === "DSC") {
      sortedProducts.sort((a, b) => b.price - a.price);
    }

    setSearchResult(sortedProducts);
    setModifiedResult(sortedProducts);
    setDisplayResult(true);
  };

  return (
    <div className="all-content">
      <div className="header-area">
        <div className="left">
          <div className="search-bar">
            <input
              type="text"
              name=""
              id="searchbar"
              placeholder="Search"
              onChange={(e) => {
                handleSearch(e.target.value);
              }}
            />
            <button className="search-btn" onClick={handleSearchButtonClick}>
              Search
            </button>
          </div>
        </div>

        <div className="right">
          <div className="filter">
            Filter
            <select onChange={handleFilterChange} value={filterQuery}>
              <option value="All Brands">All Brands</option>
              <option value="electronics">Electronics</option>
              <option value="jewelery">Jewelery</option>
              <option value="men's clothing">Men's Clothing</option>
              <option value="women's clothing">Women's Clothing</option>
            </select>
          </div>

          <div className="sort">
            Sort
            <select onChange={handleSortChange} value={filterQuery}>
              <option value="All">All</option>
              <option value="ASC">ASC</option>
              <option value="DSC">DSC</option>
            </select>
          </div>
        </div>
      </div>

      <h1>KDU MARKETPLACE</h1>
      <div className="product-list">
        <br />
        {displayResult
          ? modifiedResult.map((individual_product) => (
              <div className="indiv-product" key={individual_product.id}>
                <div className="image">
                  <img src={individual_product.image} alt="prod-img" />
                </div>

                <Link
                  style={{ textDecoration: "none" }}
                  to={`/productDetails/${individual_product.id}`}
                  key={individual_product.id}
                >
                  <p>
                    {individual_product.title} - ${individual_product.price}
                  </p>
                </Link>
                <p id="category">Category: {individual_product.category}</p>
              </div>
            ))
          : allProduct.map((individual_product) => (
            <div className="indiv-product" key={individual_product.id}>
            <div className="image">
              <img src={individual_product.image} alt="prod-img" />
            </div>

            <Link
              style={{ textDecoration: "none" }}
              to={`/productDetails/${individual_product.id}`}
              key={individual_product.id}
            >
              <p>
                {individual_product.title} - ${individual_product.price}
              </p>
            </Link>
            <p id="category">Category: {individual_product.category}</p>
          </div>
            ))}
      </div>
    </div>
  );
}
