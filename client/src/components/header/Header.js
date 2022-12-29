import { Link } from "react-router-dom";

import styled from "styled-components";
import SearchBar from "./SearchBar";
import { PrimaryLink, SecondaryLink } from "../StyledLink";
import { removeCookie, getCookie } from "../../utils/cookie";
const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  height: 100%;
  align-items: center;
  max-width: 100%;
  width: var(--screen-full);
`;

const Header = () => {
  const isCookieExist = getCookie("Authoriazation");
  const onLogout = () => {
    if (isCookieExist && window.confirm("정말 로그아웃 하시겠습니까?")) {
      removeCookie("Authoriazation");
      window.location.reload();
    }
  };
  console.log(isCookieExist);

  return (
    <header className="fixed top-0 z-40 w-screen h-header-height bg-[#F8F9F9] flex justify-center">
      <HeaderContainer>
        <Link to={"/"} className="h-full flex items-center justify-center">
          <span className="logo-img h-[30px] w-[150px] mt-[-4px]"></span>
        </Link>
        <div className="h-full flex items-center justify-center grow">
          <SearchBar></SearchBar>
        </div>
        {/** 로그인&비로그인 다르게 보여줌 */}
        {isCookieExist ? (
          <ul className="flex flex-row">
            <li>프로필</li>
            <button onClick={onLogout}>로그아웃</button>
          </ul>
        ) : (
          <ul className="flex flex-row">
            <li className="flex items-center justify-center">
              <SecondaryLink to={"/login"}>Log in</SecondaryLink>
            </li>
            <li className="flex items-center justify-center">
              <PrimaryLink to={"/signup"}>Sign up</PrimaryLink>
            </li>
          </ul>
        )}
      </HeaderContainer>
    </header>
  );
};

export default Header;
