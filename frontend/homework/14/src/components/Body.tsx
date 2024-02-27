import "../styles/Body.scss";
import { useSelector, useDispatch } from "react-redux";
import { addTodo, deleteTodo, toggleItem, clearTodo } from "../redux/todoSlice";
import { useState } from "react";
import { RootState } from "../redux/store";

export function Body() {
  const dispatch = useDispatch();

  const [text, setText] = useState("");
  const todos = useSelector((state: RootState) => state.todo.list);
  const searchQuery = useSelector((state: RootState) => state.todo.searchQuery);

  const handleAddItemClick = () => {
    if (text.trim() === "") return;
    dispatch(addTodo(text));
    setText("");
  };

  const handleDeleteItemClick = (id: number) => {
    dispatch(deleteTodo(id));
  };

  const handleToggleItem = (id: number) => {
    dispatch(toggleItem(id));
  };

  const handleClearBtn = () => {
    dispatch(clearTodo());
  };

  const searchList = todos.filter((item) =>
    item.todo.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="todo-section">
      <h2 className="body">Add Items</h2>
      <input
        type="text"
        id="input-todo"
        placeholder=""
        value={text}
        onChange={(e) => setText(e.target.value)}
      />

      <button className="add-btn" onClick={handleAddItemClick}>
        Submit
      </button>

      <button className="clear-todo add-btn" onClick={handleClearBtn}>
        Clear Todo
      </button>
      <h2 className="sub-head">Items</h2>

      {searchList.length === 0 && searchQuery !== "" ? (
        <p>Not Found</p>
      ) : (
        <ul className="search-list">
          {searchList.map((item) => (
            <li className="list-item" key={item.id}>
              <div className="item-checkbox">
                <input
                  type="checkbox"
                  checked={item.completed}
                  onChange={() => handleToggleItem(item.id)}
                />
                <span
                  style={{
                    textDecoration: item.completed ? "line-through" : "none",
                  }}
                >
                  <p> {item.todo}</p>
                </span>
              </div>

              <button
                className="delete-btn"
                onClick={() => handleDeleteItemClick(item.id)}
              >
                X
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
