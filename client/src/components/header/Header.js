import { Link } from "react-router-dom";
import styled from "styled-components";
import SearchBar from "./SearchBar";
import { PrimaryLink, SecondaryLink } from "./StyledLink";

const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  height: 100%;
  align-items: center;
  max-width: 100%;
  width: var(--screen-full);
`;

const Header = () => {
  return (
    <header className="z-40 fixed top-0 w-screen h-header-height bg-[#F8F9F9] flex justify-center">
      <HeaderContainer className="">
        <Link to={"/"} className="h-full flex items-center justify-center">
          <span className="logo-img h-[30px] w-[150px] mt-[-4px]"></span>
        </Link>
        <div>
          <SearchBar></SearchBar>
        </div>
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
