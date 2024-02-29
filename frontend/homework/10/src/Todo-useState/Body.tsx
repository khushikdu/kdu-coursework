import { useState } from "react";
import { ListSectionProps } from "./ListSectionProps";
import './Body.scss'
export function Body({list, searchItem, addItem, deleteItem}:Readonly<ListSectionProps>){
    const [inputText,setInputText]=useState('');

    const handleAddItemClick=()=>{
        if(inputText.trim()==='')
            return;
        addItem(inputText);
        setInputText('');
    };

    const handleDeleteItemClick = (id:number)=>{
        deleteItem(id);
    };

    const searchResult = list.filter(item => item.todo.toLowerCase().includes(searchItem.toLowerCase()));


    return (
        <div className="todo-section">
            <h2 className="body">Add Items</h2>
            <input 
                type="text"  
                id="input-todo"
                value={inputText}
                onChange={(e)=>setInputText(e.target.value)} 
                placeholder=""
            />
            <button className="add-btn" onClick={handleAddItemClick}>Submit</button>
            <h2 className="sub-head">Items</h2>
            
            {(searchResult.length===0&&searchItem!=="")?(<p>Not Found</p>):(
                <ul className="search-list">
                    {searchResult.map(item=>(
                        <li className="list-item" key={item.id}>
                            {item.todo} &nbsp;
                            <button className="delete-btn" onClick={()=>handleDeleteItemClick(item.id)}>X</button>
                        </li>
                    ))}
                </ul>
           ) }
        </div>
    );
}