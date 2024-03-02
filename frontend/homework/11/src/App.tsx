import React, { useEffect, useState } from 'react';
import './App.scss';
import { ApiQuote } from './types/quotetypes';
import { Quote } from './components/Quote';
import { MdClose } from "react-icons/md";

function App() {
  const [quotes,setQuotes]=useState <ApiQuote[]> ([]);
  const [allQuotes, setAllQuotes]=useState <ApiQuote[]> ([]);
  const [selectedTag,setSelectedTag]=useState<string[]> ([]);
  
  //fetching new data from the button
  const fetchDataBtn=()=>{
    fetch('https://api.quotable.io/quotes/random')
    .then((response)=>response.json())
    .then((data:ApiQuote[])=>{
        setAllQuotes([...data,quotes[0],quotes[1]])
      });
  }

  // to fetch the initial data from the api
  useEffect(()=>{
    fetch('https://api.quotable.io/quotes/random?limit=3')
    .then((response)=>response.json())
    .then((data:ApiQuote[])=>{
        setAllQuotes(data)
      });
  },[]);

  // use effect to view only those quotes which has the required tags
  useEffect(()=>{
    setQuotes(
      allQuotes.filter((quote)=>{
        return selectedTag.every((tag)=>quote.tags.includes(tag))
      })
    );
  },[selectedTag,allQuotes]);

  //to add the selected tags to the list
  const onSelectTag=(tag:string)=>{
      if(!selectedTag.includes(tag)){
        setSelectedTag([...selectedTag,tag])
    }
  }

  //to delete the selected tags from the list
  const onDeleteTag=(tag:string)=>{
    setSelectedTag(selectedTag.filter((existingtag)=>existingtag!==tag))
  }

  return (
    <div className='class-container'>
      <div className="header">
      <button onClick={()=>{fetchDataBtn()}}>
        NEW QUOTE
      </button>
      <br />
      
      <div className="filterlist">
          <ul className="sel-tags"> 
          <p>Filter</p> 
            {
              selectedTag.map((tag)=>{
                return (
                <div className="tag-btns" key={tag} onClick={()=>onDeleteTag(tag)}>
                  {tag} 
                  <button className="remove"><MdClose className='md-close' /></button>
                </div>
              )})
            }
          </ul>
      </div>
      
      </div>

      {quotes.map((quote)=>{
          return <Quote key={quote._id} quote={quote} onSelectTag={onSelectTag}/>
        })}
    </div>
  );
}

export default App