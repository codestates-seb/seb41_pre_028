import axios from "./axios";
import { getCookie, isCookieExist } from "../cookie";

const FETCH_BASIC_URL = "http://3.37.105.24:8080";

/** Questions */
// page, size : 필수 정보임!
export const getQuestionList = (params) => {
  return axios.get(`/questions`, {
    params: { ...params, page: params.page - 1 },
  });
};

export const getQuestionDetail = (questionId) =>
  axios.get(`/questions/${questionId}`);

export const getQuestion = (questionId) => axios.get(`questions/${questionId}`);

export const searchQuestionsByValue = (params) => {
  let rdx = /^\[.*\]$/;
  let value = params.value;
  if (rdx.test(value)) {
    value =
      encodeURI("[") + value.substring(1, value.length - 1) + encodeURI("]");
  }
  return axios.get(
    `/search?value=${value}&page=${params.page - 1}&size=${params.size}`
  );
};

/** Answers */
export const getAnswerList = (params) => axios.get("/answers", { params });

/** Users */
export const getUserProfile = (userId) =>
  axios.get(`/users/${userId}`, {
    headers: { Authorization: getCookie("Authorization") },
  });

export const getMyProfile = () =>
  axios.get("/users/mypage", {
    headers: { Authorization: getCookie("Authorization") },
  });

export const patchUserProfile = (userInfo) => {
  console.log(userInfo);
};

export const fetchCreateQuestion = async (fetchData) => {
  return fetch(`${FETCH_BASIC_URL}/questions`, {
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

export const fetchEditQuestion = async (fetchData) => {
  return fetch(`${FETCH_BASIC_URL}/questions/${fetchData.questionId}`, {
    method: "PATCH",
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
  return fetch(`${FETCH_BASIC_URL}/answers`, {
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

export const fetchQuestionComment = async (fetchData) => {
  return fetch(
    `${FETCH_BASIC_URL}/questions/${fetchData.questionId}/comments`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: isCookieExist,
      },

      body: JSON.stringify(fetchData),
    }
  )
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

export const fetchAnswerComment = async (fetchData) => {
  return fetch(`${FETCH_BASIC_URL}/answers/${fetchData.answerId}/comments`, {
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
