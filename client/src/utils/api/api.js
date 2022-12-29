import axios from "axios";
import { isCookieExist } from "../cookie";

/** Questions */
export const getQuestionList = (params) =>
  axios.get("http://localhost:3001/question", { params });

export const getQuestion = (questionId) => axios.get(`questions/${questionId}`);

// export const getQuestionListByUserId = (userId) => {
//    axios.get("http://localhost:3001/question", { params: {userId}});
// }

export const searchQuestionsByWord = (word) =>
  axios.get("/question", { params: { word } });

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
  axios.get(`http://localhost:3001/users/${userId}`);

export const fetchCreateQuestion = async (fetchData) => {
  console.log(fetchData);
  return fetch(`/questions`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: isCookieExist,
    },

    body: JSON.stringify(fetchData),
  })
    .then((response) => {
      if (!response.ok) {
        throw Error("유효하지 않은 요청입니다.");
      }
      return response.json();
    })
    .then((data) => {
      return data.data.questionId;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
