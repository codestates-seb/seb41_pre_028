import axios from "./axios";

export const getQuestionList = (params) =>
  axios.get("http://localhost:3001/question", { params });

export const searchQuestionsByWord = (word) =>
  axios.get("/question", { params: { word } });
