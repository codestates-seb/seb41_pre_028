import axios from "axios";
import { isCookieExist } from "../cookie";

/** Questions */
// page, size : 필수 정보임!
export const getQuestionList = (params) => axios.get("/questions", { params });

export const getQuestionDetail = (questionId) =>
  axios.get(`/questions/${questionId}`);

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
export const getUserProfile = (userId) => axios.get(`/users/${userId}`);

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

export const fetchDeleteQuestion = async (fetchData) => {
  console.log(fetchData);
  return fetch(`/questions/${fetchData.questionId}`, {
    method: "DELETE",
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

export const fetchAnswer = async (fetchData) => {
  console.log(fetchData);
  return fetch(`/answers`, {
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

/**
 * request body : 
 * {
    "userId": 2,
    "questionId": 2,
    "content":"answer3 content"
    }
 * response body : 
 * {
    "data": {
        "answerId": 5,
        "questionId": 2,
        "userId": 2,
        "email": "test2@gmail.com",
        "nickname": "test2",
        "content": "answer3 content",
        "comments": null,
        "answerStatus": "일반 답변",
        "createdAt": "2022-12-29T08:25:08.080866511",
        "modifiedAt": "2022-12-29T08:25:08.080866511",
        "createdBy": "amdin",
        "modifiedBy": "amdin"
    }
}
 */

export const fetchQuestionComment = async (fetchData) => {
  console.log(fetchData);
  return fetch(`/questions/${fetchData.questionId}/comments`, {
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

/**
 * request body :
 * {
 *  "userId":2,
 *  "questionId":1,
 *  "content":"comment3"
 * }
 */

export const fetchAnswerComment = async (fetchData) => {
  console.log(fetchData);
  return fetch(`/answers/${fetchData.answerId}/comments`, {
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
/**
 * request body :
 * {
 * "userId":2,
 * "answerId":1,
 * "content":"comment3"
 * }
 */
