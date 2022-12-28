import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const loginUser = createAsyncThunk(
  "login/loginUser",
  async (data, { rejectWithValue }) => {
    // const wait = (timeToDelay) =>
    //   new Promise((resolve) =>
    //     setTimeout(() => {
    //       resolve(axios.post("/users/login", data));
    //     }, timeToDelay)
    //   );
    // await wait(1000).then((res) => console.log(res));
    try {
      const res = await axios.post("/login", data);
      return res.data;
    } catch (error) {
      return rejectWithValue(error.response);
    }
  }
);

const loginSlice = createSlice({
  name: "login",
  initialState: {
    email: "",
    password: "",
    isFetching: false,
    isError: false,
    isLogined: false,
    error: "",
  },
  reducers: {
    logout: (state) => {
      console.log("로그아웃 성공");
      state.isLogined = false;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginUser.pending, (state) => {
        state.isFetching = true;
      })
      .addCase(loginUser.fulfilled, (state, { payload }) => {
        state.email = payload.email;
        state.password = payload.password;
        state.isFetching = false;
        state.isLogined = true;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.error = action.payload;
        state.isFetching = false;
        state.isError = false;
      });
  },
});

export const loginActions = loginSlice.actions;
export default loginSlice.reducer;
