import { createSlice } from "@reduxjs/toolkit";

/** 초기 상태 선언 **/
const initialState = {
  focused: false,
  word: "",
};

/** Slice **/
// store 안에 작은 store 같은 느낌
// 리덕스 툴킷에서 적용되는 개념
export const searchSlice = createSlice({
  // slice를 구분하기 위한 이름
  name: "search",
  // 초기 상태
  initialState,
  // 상태를 업데이트할 함수들
  // 리덕스 툴킷을 사용하면 기존에 상태에 대한 복사본을 만들고 업데이트하는 방식을 사용하지 않아도 된다.
  // (기존에는 변수의 불변성 때문에 이러한 방식을 사용 (객체, 배열 등))
  reducers: {
    focus: (state) => {
      state.focused = true;
    },
    blur: (state) => {
      state.focused = false;
    },
    change: (state, action) => {
      state.word = action.payload;
    },
    reset: (state) => {
      state.focused = false;
      state.word = "";
    },
  },
});

/** Action Creator **/
// 각 리듀서 함수마다 액션 생성 함수가 만들어진다.
// 이를 dispatch를 이용해 사용할 것 이기 때문에 export 해준다.
export const { focus, blur, change, reset } = searchSlice.actions;

/** Reducer (reducers 와 다름) **/
// reducers 에서 정의한 리듀서 함수들이 자동으로 하나로 합쳐진다.
// store에 전달해줌
export default searchSlice.reducer;
