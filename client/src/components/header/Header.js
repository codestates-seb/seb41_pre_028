import { Link, useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import { getCookie, removeCookie } from "../../utils/cookie";
import axios from "../../utils/api/axios";
import { getMyProfile } from "../../utils/api/api";

import styled from "styled-components";
import { media } from "../../utils/style-utils";
import SearchBar from "./SearchBar";
import Dropdown from "./Dropdown";
import { PrimaryLink, SecondaryLink } from "../StyledLink";
import { faBars, faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  height: 100%;
  align-items: center;
  max-width: 1264px;
  padding-right: 24px;
  width: var(--screen-full);
  ${media.mobile`
    padding-right: 0px;
  `}
`;

const Header = () => {
  const location = useLocation();
  const navigate = useNavigate();
  // const navigate = useNavigate();
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const isCookieExist = getCookie("Authorization");
  const onLogout = async () => {
    const res = await axios.get("/logout").then(() => {
      if (window.confirm("정말 로그아웃 하시겠습니까?")) {
        removeCookie("Authorization");
        window.location.reload();
      }
    });
    console.log(res.data);
    // dispatch(logoutUser()).then(() => removeCookie("Authorization"));

    // if (isCookieExist && window.confirm("정말 로그아웃 하시겠습니까?")) {
    //   removeCookie("Authorization");
    //   window.location.reload();
    // }
  };

  const onDropdown = () => {
    setDropdownOpen(!dropdownOpen);
  };

  const goToMyPage = () => {
    if (isCookieExist) {
      getMyProfile()
        .then((res) => {
          navigate(`/users/${res.data.userId}`);
        })
        .catch((err) => {
          console.log(err);
          alert("올바르지 않은 접근입니다.");
        });
    }
  };

  return (
    <header className="fixed top-0 z-40 w-full h-header-height bg-[#F8F9F9] flex justify-center border-b-2 shadow border-t-amber-500 border-t-4 drop-shadow-xl">
      <HeaderContainer>
        <div className="flex sm:hidden justify-center items-center p-2">
          <button onClick={onDropdown}>
            {dropdownOpen ? (
              <FontAwesomeIcon icon={faXmark} width={24} height={24} />
            ) : (
              <FontAwesomeIcon icon={faBars} width={24} height={24} />
            )}
          </button>
          <Dropdown visibilty={dropdownOpen}>
            <ul className="flex flex-col ">
              <li>
                <Link to={"/"} className="flex p-2 ml-4">
                  Home
                </Link>
              </li>
              <li className="flex p-2 ml-4">PUBLIC</li>
              <li>
                <Link
                  to={"/questions"}
                  className={
                    location.pathname === "/questions"
                      ? "flex bg-stone-200 font-bold border-r-amber-500	 border-r-4 py-1"
                      : "flex py-1"
                  }
                >
                  <svg width="18px" height="18px" className="mr-1">
                    <path d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8ZM8 15.32a6.46 6.46 0 0 1-4.3-2.74 6.46 6.46 0 0 1-.93-5.01L7 11.68v.8c0 .88.12 1.32 1 1.32v1.52Zm5.72-2c-.2-.66-1-1.32-1.72-1.32h-1v-2c0-.44-.56-1-1-1H6V7h1c.44 0 1-.56 1-1V5h2c.88 0 1.4-.72 1.4-1.6v-.33a6.45 6.45 0 0 1 3.83 4.51 6.45 6.45 0 0 1-1.51 5.73v.01Z"></path>
                  </svg>
                  <span>Question</span>
                </Link>
              </li>
              <li>
                <Link
                  to={"/tags"}
                  className={
                    location.pathname === "/tags"
                      ? "flex bg-stone-200 font-bold border-r-orange-400 border-r-4 py-1"
                      : "flex py-1"
                  }
                >
                  <div className="ml-5">Tags</div>
                </Link>
              </li>
              <li>
                <Link
                  to={"/users"}
                  className={
                    location.pathname === "/users"
                      ? "flex bg-stone-200 font-bold border-r-orange-400 border-r-4 py-1"
                      : "flex py-1"
                  }
                >
                  <div className="ml-5">Users</div>
                </Link>
              </li>
            </ul>
          </Dropdown>
        </div>
        <Link
          to={"/"}
          className="h-full hidden sm:flex w-[164px] items-center justify-center pl-[10px]"
        >
          <span className="logo-img h-[30px] w-[150px] mt-[-4px]"></span>
        </Link>
        <Link
          to={"/"}
          className="h-full flex sm:hidden w-[40px] items-center justify-center"
        >
          <span className="logo-img h-[30px] w-[25px] mt-[-4px]"></span>
        </Link>
        <div className="h-full flex items-center justify-start grow">
          <SearchBar></SearchBar>
        </div>
        {/** 로그인&비로그인 다르게 보여줌 */}
        {isCookieExist ? (
          <ul className="flex flex-row justify-end items-center gap-3">
            <li role="none">
              <button onClick={goToMyPage}>
                <div className="flex justify-center items-center ">
                  <img
                    className="rounded"
                    src="https://post-phinf.pstatic.net/MjAxNzExMTRfMjUz/MDAxNTEwNjUwODAwOTYx.N8rgE6_pPw2VxVRy2Hqq8cSc16YcD574Y9Uix9ws8nUg.Jv5-95xGjRZGZiQAqOUo2pOe8cCbbuZyt3u_UqilgxEg.JPEG/Fotolia_129866260_Subscription_Monthly_M.jpg?type=w1200"
                    alt=""
                    width={24}
                    height={24}
                  />
                </div>
              </button>
            </li>

            <li>
              <a href="/">
                <svg width={18} height={18}>
                  <path d="M15 2V1H3v1H0v4c0 1.6 1.4 3 3 3v1c.4 1.5 3 2.6 5 3v2H5s-1 1.5-1 2h10c0-.4-1-2-1-2h-3v-2c2-.4 4.6-1.5 5-3V9c1.6-.2 3-1.4 3-3V2h-3ZM3 7c-.5 0-1-.5-1-1V4h1v3Zm8.4 2.5L9 8 6.6 9.4l1-2.7L5 5h3l1-2.7L10 5h2.8l-2.3 1.8 1 2.7h-.1ZM16 6c0 .5-.5 1-1 1V4h1v2Z"></path>{" "}
                </svg>
              </a>
            </li>
            <li>
              <a href="/">
                <svg width={18} height={18}>
                  <path d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8Zm.81 12.13c-.02.71-.55 1.15-1.24 1.13-.66-.02-1.17-.49-1.15-1.2.02-.72.56-1.18 1.22-1.16.7.03 1.2.51 1.17 1.23ZM11.77 8c-.59.66-1.78 1.09-2.05 1.97a4 4 0 0 0-.09.75c0 .05-.03.16-.18.16H7.88c-.16 0-.18-.1-.18-.15.06-1.35.66-2.2 1.83-2.88.39-.29.7-.75.7-1.24.01-1.24-1.64-1.82-2.35-.72-.21.33-.18.73-.18 1.1H5.75c0-1.97 1.03-3.26 3.03-3.26 1.75 0 3.47.87 3.47 2.83 0 .57-.2 1.05-.48 1.44Z"></path>{" "}
                </svg>
              </a>
            </li>
            <li>
              <a href="/">
                <svg width={18} height={18}>
                  <path d="m8.9844-0.013672a1 1 0 0 0 -0.98438 1.0137v0.38281l-0.55273-0.27734a1 1 0 0 0 -0.48242 -0.11133 1 1 0 0 0 -0.41211 1.9004l1.4473 0.72266v3.6523l-3.1621-1.8262 0.097656-1.6152a1 1 0 0 0 -0.95117 -1.0742 1 1 0 0 0 -1.0449 0.95508l-0.037109 0.61719-0.33008-0.19141a1 1 0 0 0 -0.57422 -0.14062 1 1 0 0 0 -0.42578 1.8711l0.33203 0.19141-0.51758 0.3418a1 1 0 1 0 1.1016 1.668l1.3516-0.89258 3.1621 1.8262-3.1621 1.8262-1.3516-0.89258a1 1 0 0 0 -0.56445 -0.17383 1 1 0 0 0 -0.53711 1.8418l0.51758 0.3418-0.33203 0.19141a1 1 0 1 0 1 1.7305l0.33008-0.19141 0.037109 0.61719a1 1 0 1 0 1.9961 -0.11914l-0.097656-1.6152 3.1621-1.8262v3.6523l-1.4473 0.72266a1 1 0 0 0 0.89453 1.7891l0.55273-0.27734v0.38281a1 1 0 1 0 2 0v-0.38281l0.55273 0.27734a1 1 0 1 0 0.89453 -1.7891l-1.4473-0.72266v-3.6523l3.1621 1.8262-0.097656 1.6152a1 1 0 1 0 1.9961 0.11914l0.037109-0.61719 0.33008 0.19141a1 1 0 1 0 1 -1.7305l-0.33203-0.19141 0.51758-0.3418a1 1 0 0 0 -0.56641 -1.8418 1 1 0 0 0 -0.53516 0.17383l-1.3516 0.89258-3.1621-1.8262 3.1621-1.8262 1.3516 0.89258a1 1 0 1 0 1.1016 -1.668l-0.51758-0.3418 0.33203-0.19141a1 1 0 0 0 -0.45508 -1.8711 1 1 0 0 0 -0.54492 0.14062l-0.33008 0.19141-0.037109-0.61719a1 1 0 0 0 -0.97461 -0.95508 1 1 0 0 0 -1.0215 1.0742l0.097656 1.6152-3.1621 1.8262v-3.6523l1.4473-0.72266a1 1 0 1 0 -0.89453 -1.7891l-0.55273 0.27734v-0.38281a1 1 0 0 0 -1.0156 -1.0137z"></path>{" "}
                </svg>
              </a>
            </li>
            <li>
              <div>
                <svg width={18} height={18} onClick={onLogout}>
                  <path d="M15 1H3a2 2 0 0 0-2 2v2h16V3a2 2 0 0 0-2-2ZM1 13c0 1.1.9 2 2 2h8v3l3-3h1a2 2 0 0 0 2-2v-2H1v2Zm16-7H1v4h16V6Z"></path>{" "}
                </svg>
              </div>
            </li>
          </ul>
        ) : (
          <ul className="flex flex-row ml-2	gap-2 justify-end">
            <li className="flex items-center justify-center ">
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
