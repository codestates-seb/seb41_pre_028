import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { change } from "../../store/searchSlice";
import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

const SearchInput = styled.input`
  width: 95%;
  padding: 0.4rem 0.5rem;
  font-size: 13px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #babfc4;
  padding-left: 32px;
  border-radius: 5px;
`;

const SearchBar = () => {
  // slice를 생성할 때 지정해준 이름을 이용해 state를 이용한다.
  const searchWord = useSelector((state) => state.search.word);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleKeyUp = (e) => {
    if (e.key === "Enter") {
      console.log(searchWord);
      navigate(`/search/${searchWord}`);
      // 여기에 추가적인 작업 필요
    }
  };

  const handleChange = (e) => {
    // 정의한 액션 생성 함수를 이용, state를 업데이트 한다.
    // action 생성자 함수로 넘겨주는 인자는 action.payload에 저장됨
    dispatch(change(e.target.value));
  };

  return (
    <div className="w-full max-h-max relative rounded-[3px]">
      <SearchInput
        type="text"
        placeholder="Search..."
        onKeyUp={handleKeyUp}
        onChange={handleChange}
      ></SearchInput>
      <FontAwesomeIcon
        icon={faMagnifyingGlass}
        className="absolute top-1/2 mt-[-9px] left-[0.5rem] text-[#838C95]"
      />
    </div>
  );
};

export default SearchBar;
