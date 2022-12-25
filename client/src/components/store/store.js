import { configureStore } from "@reduxjs/toolkit";
import LoginSlice from "./loginSlice";
import SignupSlice from "./signupSlice";

export const store = configureStore({
  reducer: { login: LoginSlice, signup: SignupSlice },
});
