import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { getQuestionList } from "../api/question";

// 사용해야 하는 이유??
// 액션 타입 문자열, 프로미스를 반환하는 비동기 함수, 추가 옵션 순서대로 인자를 받는 함수
const asyncGetQuestions = createAsyncThunk(
  "questionsSlice/getQuestions",
  async () => {
    const res = await getQuestionList();
    return res.data;
  }
);

const initialState = {
  data: [],
};

/** Slice **/
// pending : 비동기 호출 이전
// fulfilled : 비동기 호출 성공
// rejected : 비동기 호출 실패
export const questionsSlice = createSlice({
  name: "questions",
  initialState,
  // 비동기 상태 제어는 extraReducers 에서 한다.
  extraReducers: (builder) => {
    builder.addCase(asyncGetQuestions.pending, (state, action) => {
      state.status = "Loading";
    });
    builder.addCase(asyncGetQuestions.fulfilled, (state, action) => {
      state.value = action.payload;
      state.status = "complete";
    });
    builder.addCase(asyncGetQuestions.rejected, (state, action) => {
      state.status = "fail";
    });
  },
});

/** Action Creator **/
// 각 리듀서 함수마다 액션 생성 함수가 만들어진다.
// 이를 dispatch를 이용해 사용할 것 이기 때문에 export 해준다.

/** Reducer (reducers 와 다름) **/
// reducers 에서 정의한 리듀서 함수들이 자동으로 하나로 합쳐진다.
// store에 전달해줌
export default questionsSlice.reducer;
