import { useEffect, useRef, useState } from "react";
import "./Form.scss";

export function Form() {
  const [inputValue, setInputValue] = useState<string>("");
  const focusRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (focusRef.current) {
      focusRef.current.focus();
    }
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  return (
    <div className="form-section">
      <h1>Focusing on the 1st input elements using useRef</h1>
      <input
        type="text"
        className="autofocus"
        ref={focusRef}
        placeholder="Focus on input bar using useref"
        onChange={handleChange}
      />
      <p>Input String : {inputValue}</p>
    </div>
  );
}
