import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
export const SignupUser = createAsyncThunk(
  "signup/signupUser",
  async (data, { rejectWithValue }) => {
    try {
      const res = await axios.post("/users/signup", data);
      return res.data;
    } catch (error) {
      return rejectWithValue(error);
    }
  }
);

const signupSlice = createSlice({
  name: "signup",
  initialState: {
    nickName: "",
    email: "",
    password: "",
    isSignedUp: false,
    isSuccess: false,
    signUpError: "",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(SignupUser.pending, (state) => {
        state.isSignedUp = false;
        state.isSuccess = true;
      })

      .addCase(SignupUser.fulfilled, (state, { payload }) => {
        state.isSignedUp = true;
        state.isSuccess = false;
        state.nickName = payload.nickName;
        state.email = payload.email;
        state.password = payload.password;
      })
      .addCase(SignupUser.rejected, (state, action) => {
        console.log(action.payload.response.data.message);
        if (action.payload.response.data.message === "Nickname Exists") {
          alert("이미 사용중인 디스플레이 네임입니다.");
        } else if (action.payload.response.data.message === "Email Exists") {
          alert("이미 사용중인 이메일입니다.");
        }
        state.signUpError = action.payload;
        state.isSuccess = false;
        state.isSignedUp = false;
      });
  },
});

export default signupSlice.reducer;
