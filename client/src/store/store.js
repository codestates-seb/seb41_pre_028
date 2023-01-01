import { configureStore } from "@reduxjs/toolkit";
import searchReducer from "./searchSlice";
import loginSlice from "./loginSlice";
import signupSlice from "./signupSlice";
import logoutSlice from "./logoutSlice";

const store = configureStore({
  reducer: {
    search: searchReducer,
    login: loginSlice,
    signup: signupSlice,
    logout: logoutSlice,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({ serializableCheck: false }),
});

export default store;
