import { configureStore } from "@reduxjs/toolkit";
import searchReducer from "./searchSlice";
import loginSlice from "./loginSlice";
import signupSlice from "./signupSlice";
const store = configureStore({
  reducer: { search: searchReducer, login: loginSlice, signup: signupSlice },
});

export default store;
