import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "../utils/api/axios";

export const loginUser = createAsyncThunk(
  "login/loginUser",
  async (data, { rejectWithValue }) => {
    try {
      const res = await axios.post("/login", data);
      localStorage.setItem("authorizationToken", res.headers.authorization);

      return res;
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
    isLogined: false,
    loginError: "",
    authorizationToken: "",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(loginUser.pending, (state) => {
        state.isFetching = true;
      })
      .addCase(loginUser.fulfilled, (state, { payload }) => {
        state.authorizationToken = payload.headers.authorization;
        state.email = payload.email;
        state.password = payload.password;
        state.isFetching = false;
        state.isLogined = true;
      })
      .addCase(loginUser.rejected, (state, action) => {
        if (action.payload.data.cause === "User Not Found") {
          alert("존재하지않는 유저입니다.");
        } else if (
          action.payload.data.cause === "자격 증명에 실패하였습니다."
        ) {
          alert("이메일 또는 비밀번호를 확인해주세요");
        }
        state.loginError = action.payload;
        state.isFetching = false;
      });
  },
});

export const loginActions = loginSlice.actions;
export default loginSlice.reducer;
