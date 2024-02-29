import React, { useState } from 'react';
import './App.css';
import { Body } from './Todo-useState/Body';
import { Header } from './Todo-useState/Header';

interface IListItem{
  id:number,
  todo:string
}

export function App() {
  const [listItem,setListItem]=useState<IListItem[]>([]);
  const [searchQuery,setSearchQuery]=useState<string>("")

  const handleSearch=(query:string)=>{
    setSearchQuery(query);
  };

  const handleAddItem = (text: string)=>{
    const newItem:IListItem={
      id:listItem.length+1,
      todo:text
    };
    setListItem([...listItem,newItem]);
  }

  const handleDeleteItem=(id:number)=>{
      setListItem(listItem.filter(item=>item.id!==id))
    }
  
  return (
    <div className="App">
      <Header onSearch={handleSearch}/>
      <Body
        list={listItem}
        searchItem={searchQuery}
        addItem={handleAddItem}
        deleteItem={handleDeleteItem}
      />
    </div>
  );
}
