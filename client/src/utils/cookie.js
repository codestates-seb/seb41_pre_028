import { Cookies } from "react-cookie";

const cookies = new Cookies();

export const setCookie = (key, value, options) => {
  return cookies.set(key, value, { ...options });
};
export const getCookie = (key) => {
  return cookies.get(key);
};
export const removeCookie = (key) => {
  return cookies.remove(key);
};

export const isCookieExist = getCookie("Authorization");
// post 이거 사용해서 접근권한 만들기
// 댓글 post 에도 이거 사용해서 접근권한 만들기
