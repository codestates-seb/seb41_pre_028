import axios from "axios";

// 모든 요청에 withCredentials가 true로 설정
axios.defaults.withCredentials = true;
// axios.defaults.baseURL = "http://localhost:3001";

export default axios;
