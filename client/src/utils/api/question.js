import axios from "./axios";

export const getQuestionList = () => axios.get("/question");

export const searchQuestionsByWord = (word) =>
  axios.get("/question", { params: { word } });
