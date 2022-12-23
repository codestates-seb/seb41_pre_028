import styled from "styled-components";

const SearchForm = styled.form`
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
  return (
    <SearchForm>
      <span>search 아이콘</span>
      <input type="text" placeholder="Search..."></input>
    </SearchForm>
  );
};

export default SearchBar;
