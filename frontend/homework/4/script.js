//get the add button
let addButton = document.getElementById("add-btn")

//add a click listener addTodo
addButton.addEventListener("click",addTodo)

//define addTodo
function addTodo() {
    //get the input tag
    let inputTag=document.getElementById("todo-input");

    //get data from the input tag
    const data=inputTag.value;
    console.log(data);
    //create a new list item
    let todoItem=document.createElement("li");

    if(data === ""){
        return;
    }
    //set the text content to the data received from the user
    todoItem.textContent=data;

    //create a button
    let deleteBtn=document.createElement("button")

    deleteBtn.textContent="Delete";
    deleteBtn.addEventListener("click",()=>{
        todoList.removeChild(todoItem);
    })

        //create a button
        let editBtn=document.createElement("button")

        editBtn.textContent="Edit";
        editBtn.addEventListener("click",()=>{
            todoList.removeChild(todoItem);
        })

    //get the ul object reference
    let todoList=document.getElementById("todo-list");

    //append li to ul
    todoItem.appendChild(editBtn);
    todoItem.appendChild(deleteBtn);
    todoList.appendChild(todoItem);
}