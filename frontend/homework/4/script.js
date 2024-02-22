let addButton = document.getElementById("add-btn")

addButton.addEventListener("click",addTodo)

function addTodo() {
    
    let inputTag=document.getElementById("todo-input");

    const data=inputTag.value;
    console.log(data);
    let todoItem=document.createElement("li");

    if(data === ""){
        return;
    }
    todoItem.textContent=data;

    let deleteBtn=document.createElement("button")

    deleteBtn.textContent="Delete";
    deleteBtn.addEventListener("click",()=>{
        todoList.removeChild(todoItem);
    })
        let editBtn=document.createElement("button")

        editBtn.textContent="Edit";
        editBtn.addEventListener("click",()=>{
            todoList.removeChild(todoItem);
        })
    let todoList=document.getElementById("todo-list");

    todoItem.appendChild(editBtn);
    todoItem.appendChild(deleteBtn);
    todoList.appendChild(todoItem);
}