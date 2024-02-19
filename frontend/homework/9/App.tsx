import React from 'react';
import './App.css';
import UserDetails from '../src/Profile/details.json'
import {Profile} from '../src/Profile/Profile'
import { Skills } from './Profile/Skills';
import { Hobby } from './Profile/Hobby';


console.log(UserDetails)
function App() {
  return (
    <>
    <div className="app-file">
      <Profile/>
      <div className="column-data">
        <Skills skills={UserDetails.skills}/>
        <Hobby hobby={UserDetails.hobbies}/>
      </div>
    </div>
    </>
     
  );
}

export default App;