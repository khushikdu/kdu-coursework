import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import {ProductList} from '../src/pages/ProductList'
import {ProductDetails} from '../src/pages/ProductDetails'
import './App.css'
import { useEffect, useState } from "react";
import { ProductDetailsType } from "./types/ProductDetailsType";

function App() {
  const [eachProduct,setEachProduct]=useState<ProductDetailsType>();
  const [allProduct,setAllProducts]=useState<ProductDetailsType[]>([]);

  useEffect(()=>{
    fetch('https://fakestoreapi.com/products')
      .then((response)=>(response.json()))
      .then((data:ProductDetailsType[])=>{
        setAllProducts(data)
      });
  })

  return (
    <div className="app-main-page">
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<ProductList {}/>}/>
        <Route path="/productDetails" element={<ProductDetails/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  )  
}

export default App
