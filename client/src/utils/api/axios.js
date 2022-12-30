import axios from "axios";

// 모든 요청에 withCredentials가 true로 설정
axios.defaults.withCredentials = true;
// axios.defaults.headers["Access-Control-Allow-Origin"] =
//   "http://3.37.105.24:8080";
// axios.defaults.baseURL = "http://3.37.105.24:8080";
export default axios;
