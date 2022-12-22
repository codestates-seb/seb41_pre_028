import styled from "styled-components";
import { useSelector, useDispatch } from "react-redux";
import { change } from "../../features/search/searchSlice";

const SearchInput = styled.input`
  flex-grow: 1;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  div {
    display: flex;
    align-items: center;
    width: 100%;
    background-color: white;
  }
`;

const SearchBar = () => {
  // slice를 생성할 때 지정해준 이름을 이용해 state를 이용한다.
  const searchWord = useSelector((state) => state.search.word);
  const dispatch = useDispatch();

  const handleKeyUp = (e) => {
    if (e.key === "Enter") {
      console.log(searchWord);
      // 여기에 추가적인 작업 필요
    }
  };

  const handleChange = (e) => {
    // 정의한 액션 생성 함수를 이용, state를 업데이트 한다.
    // action 생성자 함수로 넘겨주는 인자는 action.payload에 저장됨
    dispatch(change(e.target.value));
  };
  return (
    <div>
      <SearchInput
        type="text"
        placeholder="Search..."
        onKeyUp={handleKeyUp}
        onChange={handleChange}
      ></SearchInput>
    </div>
  );
};

export default SearchBar;
