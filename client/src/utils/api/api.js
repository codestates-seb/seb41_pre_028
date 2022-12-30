import axios from "axios";
import { getCookie } from "../cookie";

/** Questions */
// page, size : 필수 정보임!
export const getQuestionList = (params) =>
  axios.get(`/questions`, { params: { ...params, page: params.page - 1 } });

export const getQuestionDetail = (questionId) =>
  axios.get(`/questions/${questionId}`);

// export const getQuestionListByUserId = (userId) => {
//    axios.get("http://localhost:3001/question", { params: {userId}});
// }

export const searchQuestionsByValue = (params) =>
  axios.get("/search", { params: { ...params, page: params.page - 1 } });

/** Answers */
export const getAnswerList = (params) => axios.get("/answers", { params });

/** Answers/answer-id/comments */
// export const getAnswerCommentList = (params) =>
//   axios.get("/answers", { params });

// export const getAnswerListByUserId = (userId) => {
//     axios.get("http://localhost:3001/question", { params: {userId}});
// }

/** Users */
export const getUserProfile = (userId) =>
  axios.get(`/users/${userId}`, {
    headers: { Authorization: getCookie("Authorization") },
  });
