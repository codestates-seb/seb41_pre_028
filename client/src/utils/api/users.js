import axios from "axios";

export const getUserProfile = (userId) =>
  axios.get(`http://localhost:3001/users/${userId}`);
