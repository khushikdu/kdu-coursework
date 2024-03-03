import { createSlice } from "@reduxjs/toolkit";
import { RoomType } from "../Interface/RoomDetail";
import { getRooms } from "./thunk/getRooms";

 interface RoomDetail {
    roomTypes: RoomType[],
    status:"pending" | "fullfilled" | "error";
}

const initialState: RoomDetail={
    roomTypes:[],
    status:"pending",
}

export const roomSlice=createSlice({
    name:"room",
    initialState,
    reducers:{
        setData:(state,action)=>{
            state.roomTypes=action.payload
        }
    },
    extraReducers(builder) {
        builder
          .addCase(getRooms.pending, (state) => {
            state.status = "pending";
          })
          .addCase(getRooms.fulfilled, (state, action) => {
            state.roomTypes = action.payload as RoomType[];
            state.status = "fullfilled";
          });
        },
})


export const { setData } = roomSlice.actions;
export default roomSlice.reducer;