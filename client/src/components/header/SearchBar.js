import { useSelector, useDispatch } from "react-redux";
import { useNavigate, useLocation } from "react-router-dom";
import { change, focus, blur, reset } from "../../store/searchSlice";
import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { useEffect } from "react";

const SearchInput = styled.input`
  width: 100%;
  padding: 0.4rem 0.5rem;
  font-size: 13px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #babfc4;
  padding-left: 32px;
  border-radius: 5px;
`;

const SearchModal = styled.div`
  position: absolute;
  background-color: white;
  box-shadow: 1px;
  left: 50%;
  top: var(--h-header);
  transform: translate(-50%, 0%);
  width: 100%;
  padding: 10px;
  gap: 1rem;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;

  > div {
    > span:first-child {
      font-size: 1rem;
      margin-right: 0.5rem;
      font-weight: 600;
    }
    > span:last-child {
      font-size: 0.8rem;
      color: #5e666e;
    }
  }
`;

const SearchBar = () => {
  // slice를 생성할 때 지정해준 이름을 이용해 state를 이용한다.
  const { pathname } = useLocation();
  const { word, focused } = useSelector((state) => state.search);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    if (pathname !== "/search") {
      dispatch(reset());
    }
  }, [pathname]);

  const handleKeyUp = (e) => {
    if (e.key === "Enter") {
      if (word === "") {
        return;
      }
      dispatch(blur());
      navigate(`/search?value=${word}`);
    }
  };

  const handleChange = (e) => {
    // 정의한 액션 생성 함수를 이용, state를 업데이트 한다.
    // action 생성자 함수로 넘겨주는 인자는 action.payload에 저장됨
    dispatch(change(e.target.value));
  };

  return (
    <div className="w-[100%] pr-[5px] pl-[5px] max-h-max relative rounded-[3px]">
      <SearchInput
        type="text"
        value={word}
        placeholder="Search..."
        onKeyUp={handleKeyUp}
        onChange={handleChange}
        onFocus={() => dispatch(focus())}
        onBlur={() => dispatch(blur())}
      ></SearchInput>
      <FontAwesomeIcon
        icon={faMagnifyingGlass}
        className="absolute top-1/2 mt-[-9px] left-[0.8rem] text-[#838C95]"
      />
      {focused ? (
        <SearchModal>
          <div>
            <span>{"[tag]"}</span>
            <span>search within a tag</span>
          </div>
          <div>
            <span>{'"title"'}</span>
            <span>search by title</span>
          </div>
          <div>
            <span>content</span>
            <span>search by content</span>
          </div>
        </SearchModal>
      ) : null}
    </div>
  );
};

export default SearchBar;
