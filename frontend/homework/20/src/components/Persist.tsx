import { useState, useRef, useEffect } from "react";
import './Timer.scss'

export function Persist(){
    const [value,setValue]=useState(0);
    let valRef=useRef(0);
    
    const incrementCounter=()=>{
        valRef.current++;
        console.log(valRef.current)
    }
    const reflectChange=()=>{
        setValue(valRef.current)
    }
    
    return(
        <div className="timer-container">
            <p>Value : {value}</p>
            <p>Persisted Value : {valRef.current}</p>
            <button className="btn" onClick={incrementCounter}>Increment</button>
            <button className="btn" onClick={reflectChange}>Reflect</button>
        </div>
    )
}