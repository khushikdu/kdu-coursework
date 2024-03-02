import React, { useContext } from "react"
import './Header.scss'
import { ListItemContext } from "../App"

export function Header() {
    const { handleSearch } = useContext(ListItemContext);



    return (
        <div className="header">
            <h1>Item Lister</h1>
            <input
                type="text"
                id="search"
                placeholder="Search"
                onChange={(e) => { handleSearch(e.target.value) }} />
        </div>
    );
}