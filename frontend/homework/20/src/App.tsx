import React from "react";
import { useRef, useState } from "react";
import "./App.scss";
import { Timer } from "./components/Timer";
import { ScrollToTop } from "./components/ScrollToTop";
import { Form } from "./components/Form";

function App() {
  const windowRef = useRef<HTMLDivElement | null>(null);

  const scrollToTop = () => {
    if (windowRef.current) {
      windowRef.current.scrollIntoView({
        behavior: "smooth",
        block: "start",
      });
    }
  };
  return (
    <div className="App" ref={windowRef}>
      <Form/>
      <Timer />
      <ScrollToTop />
      <div className="sc" >
        <button className="scroll-to-top" onClick={scrollToTop}>
          Scroll to Top
        </button>
      </div>
    </div>
  );
}

export default App;
