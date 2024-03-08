import { PayloadAction,createSlice } from "@reduxjs/toolkit";

interface TodoState{
    id:number;
    todo: string;
    completed:boolean;
}

interface TodoStateList{
    list: TodoState[],
    searchQuery:string
}

const initialState: TodoStateList={
    list:[],
    searchQuery:"",
}

const todoSlice=createSlice({
    name: "todos",
    initialState,
    reducers:{
        setSearchQuery(state,action:PayloadAction<string>){
            state.searchQuery=action.payload;
        },

        addTodo(state,action:PayloadAction<string>){
            const newTodo : TodoState={
                id:Date.now(),
                todo:action.payload,
                completed:false,
            };
            state.list.push(newTodo)
        },

        deleteTodo(state,action:PayloadAction<number>){
            state.list=state.list.filter((item)=>item.id!=action.payload)
        },

        toggleItem(state,action:PayloadAction<number>){
            const id=action.payload;
            const toggleTodo=state.list.find((item)=>item.id===id)
            if(toggleTodo){
                toggleTodo.completed=!toggleTodo.completed
            }
        },
        clearTodo(state){
            state.list=[];
        }
        
    }
});

export const {setSearchQuery,addTodo,deleteTodo,toggleItem,clearTodo}=todoSlice.actions;
export default todoSlice.reducer;