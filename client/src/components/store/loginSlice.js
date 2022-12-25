import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const loginUser = createAsyncThunk("login/loginUser", async (data) => {
  const wait = (timeToDelay) =>
    new Promise((resolve) =>
      setTimeout(() => {
        resolve(axios.post("/users/login", data));
      }, timeToDelay)
    );
  await wait(1000).then(() => console.log("로그인 성공"));
});

const LoginSlice = createSlice({
  name: "login",
  initialState: {
    email: "",
    password: "",
    isFetching: false,
    isError: false,
    isLogined: false,
    errorMsg: "",
  },
  reducers: {},
  extraReducers: {
    [loginUser.pending]: (state) => {
      state.isFetching = true;
    },
    [loginUser.fulfilled]: (state, payload) => {
      console.log(payload);
      state.email = payload.email;
      state.password = payload.password;
      state.isFetching = false;
      state.isLogined = true;
    },
    [loginUser.rejected]: (state, payload) => {
      state.isFetching = false;
      state.isError = false;
      state.errorMsg = payload.message;
    },
  },
});
export default LoginSlice.reducer;
