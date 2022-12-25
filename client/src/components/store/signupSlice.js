import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const SignupUser = createAsyncThunk(
  "signup/signupUser",
  async (data) => {
    try {
      await axios.post("/users/signup", data).then((res) => {
        if (res.status === 201) {
          alert(`${data.nickname} 님 환영합니다!`);
          console.log("회원가입 성공!");
        }
      });
    } catch (error) {
      console.log(error);
    }
  }
);

const SignupSlice = createSlice({
  name: "signup",
  initialState: {
    nickName: "",
    email: "",
    password: "",
    isFetching: false,
    isError: false,
  },
  reducers: {},
  extraReducers: {
    [SignupUser.pending]: (state) => {
      state.isFetching = true;
    },
    [SignupUser.fulfilled]: (state, payload) => {
      state.isFetching = false;
      state.nickName = payload.nickName;
      state.email = payload.email;
      state.password = payload.password;
    },
    [SignupUser.rejected]: (state) => {
      state.isFetching = false;
      state.isError = true;
    },
  },
});

export default SignupSlice.reducer;
