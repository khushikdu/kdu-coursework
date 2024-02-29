import React from "react"
import './Header.scss'

interface HeaderProps{
    onSearch:(query: string)=>void
}
export function Header({onSearch}:HeaderProps){
    const handleSearch=(e:React.ChangeEvent<HTMLInputElement>)=>{
        onSearch(e.target.value);
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