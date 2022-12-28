import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const logoutUser = createAsyncThunk("logout/logoutUser", async () => {
  try {
    await axios.get("/logout");
  } catch (error) {
    console.log(error);
  }
});

const logoutSlice = createSlice({
  name: "logout",
  initialState: {
    isLogout: false,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(logoutUser.pending, (state) => {
        if (window.confirm("로그아웃 하시겠습니까?")) {
          return;
        }
        state.isLogout = false;
      })
      .addCase(logoutUser.fulfilled, (state) => {
        state.isLogout = true;
      })
      .addCase(logoutUser.rejected, (state) => {
        state.isLogout = false;
      });
  },
});

export const logoutActions = logoutSlice.actions;
export default logoutSlice.reducer;
