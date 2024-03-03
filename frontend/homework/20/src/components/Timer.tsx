import { useState, useRef, useEffect } from "react";
import './Timer.scss'

export function Timer(){
    const [seconds,setSeconds]=useState(0);
    const timerRef=useRef<NodeJS.Timeout>();

    const startTimer=()=>{
        if(!timerRef.current){
            timerRef.current=setInterval(()=>{
                setSeconds(prev=>prev+1)
            },1000);
        }
    }
    useEffect(()=>{
        return ()=>{
            if (timerRef.current){
                clearInterval(timerRef.current)
                setSeconds(0)
            }
        };
    },[]);

    const stopTimer=()=>{
        clearInterval(timerRef.current)
        timerRef.current=undefined
    }

    const resetTimer=()=>{
        setSeconds(0);
    }
    return(
        <div className="timer-container">
            <p>Timer using useRef</p>
            <button className="btn" onClick={startTimer}>Start</button>
            <button className="btn" onClick={resetTimer}>Reset</button>
            <button className="btn" onClick={stopTimer}>Stop</button>
            <p>Seconds : {seconds}</p>
        </div>
    )
}