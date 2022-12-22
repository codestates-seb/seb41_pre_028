import { Link } from "react-router-dom";
import styled from "styled-components";
import { PrimaryLink, SecondaryLink } from "./StyledLink";

const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  height: 100%;
  align-items: center;
  max-width: 100%;
  width: var(--screen-full);
`;

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

const Header = () => {
  return (
    <header className="fixed w-screen h-header-height bg-[#F8F9F9] flex justify-center">
      <HeaderContainer>
        <Link to={"/"} className="h-full flex items-center justify-center">
          <span className="logo-img h-[30px] w-[150px] mt-[-4px]"></span>
        </Link>
        <SearchForm>
          <div>
            <span>search 아이콘</span>
            <input type="text" placeholder="Search..."></input>
          </div>
        </SearchForm>
        {/* <ul className="flex flex-row">
          <li>프로필</li>
          <li>로그아웃</li>
        </ul> */}
        <ul className="flex flex-row">
          <li>
            <SecondaryLink to={"/login"}>Log in</SecondaryLink>
          </li>
          <li>
            <PrimaryLink to={"/signup"}>Sign up</PrimaryLink>
          </li>
        </ul>
      </HeaderContainer>
    </header>
  );
};

export default Header;
