import React from "react"
import '../styles/Header.scss'
import { useDispatch } from "react-redux";
import { setSearchQuery } from "../redux/todoSlice";

export function Header(){

    const dispatch=useDispatch();

    const handleSearch=(e:React.ChangeEvent<HTMLInputElement>)=>{
        dispatch(setSearchQuery(e.target.value));
    };

    return (
        <div className="header">
            <h1>Item Lister</h1>
            <input 
                type="text" 
                id="search" 
                placeholder="Search"
                onChange={handleSearch}/>
        </div>
    );
}