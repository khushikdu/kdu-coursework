import { createAsyncThunk } from "@reduxjs/toolkit";

export const getRooms = createAsyncThunk("getProducts", async () => {
  try {
    const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json");
    console.log("in response",typeof response);

    const data = await response.json();
    console.log(data.roomTypes);
    return data.roomTypes;
  } catch {
    console.log("Error encountered");
  }
});