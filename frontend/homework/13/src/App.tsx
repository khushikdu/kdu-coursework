import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ProductList } from "../src/pages/ProductList";
import { ProductDetails } from "../src/pages/ProductDetails";
import "./App.css";
import { useEffect, useState, createContext, useMemo } from "react";
import { ProductDetailsType } from "./types/ProductDetailsType";

interface ProductProps {
  allProduct: ProductDetailsType[];
  searchQuery: string;
  handleSearch: (query: string) => void;
  filterQuery: string;
  handleFilter: (query: string) => void;
}

//importing the product details interface
export const ProductDetailsContext = createContext<ProductProps>({
  allProduct: [],
  searchQuery: "",
  handleSearch: () => {},
  filterQuery: "",
  handleFilter: () => {},
});

//creating context for api
interface ProductContextAPI {
  children: React.ReactNode;
}

//provider
export const ProviderUsage = ({ children }: ProductContextAPI) => {
  // const [eachProduct,setEachProduct]=useState<ProductDetailsType>();
  const [allProduct, setAllProducts] = useState<ProductDetailsType[]>([]);
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [filterQuery, setFilterQuery] = useState<string>("");

  //fetch the data from api
  useEffect(() => {
    fetch("https://fakestoreapi.com/products")
      .then((response) => response.json())
      .then((data: ProductDetailsType[]) => {
        setAllProducts(data);
      });
  }, []);

  //searching for the item
  const handleSearch = (query: string) => {
    setSearchQuery(query);
  };

  //filtering
  const handleFilter = (query: string) => {
    setFilterQuery(query);
  };

  const ContextProviderType = useMemo(
    () => ({
      allProduct: allProduct,
      searchQuery: searchQuery,
      handleSearch: handleSearch,
      filterQuery: filterQuery,
      handleFilter: handleFilter,
    }),
    [allProduct, searchQuery]
  );

  return (
    <ProductDetailsContext.Provider value={ContextProviderType}>
      {children}
    </ProductDetailsContext.Provider>
  );
};

function App() {
  return (
    <div className="app-main-page">
      <ProviderUsage>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<ProductList />} />
            <Route path="/productDetails/:id" element={<ProductDetails />} />
          </Routes>
        </BrowserRouter>
      </ProviderUsage>
    </div>
  );
}

export default App;
