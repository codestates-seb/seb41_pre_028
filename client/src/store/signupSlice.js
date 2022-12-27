import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const SignupUser = createAsyncThunk(
  "signup/signupUser",
  async (data, { rejectWithValue }) => {
    // const wait = (timeToDelay) =>
    //   new Promise((resolve) =>
    //     setTimeout(() => {
    //       resolve(axios.post("/users/signup", data));
    //     }, timeToDelay)
    //   );
    // await wait(1000).then(() => alert(`${data.nickname}님 환영합니다!`));
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
    isError: false,
    error: "",
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
        state.error = action.payload;
        state.isSignedUp = false;
        state.isError = true;
      });
  },
});

export default signupSlice.reducer;
