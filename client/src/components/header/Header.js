import { Link, useNavigate } from "react-router-dom";
import { getCookie, removeCookie } from "../../utils/cookie";
import axios from "../../utils/api/axios";
import { getMyProfile } from "../../utils/api/api";
import styled from "styled-components";
import Dropdown from "./Dropdown";
import { PrimaryLink, SecondaryLink } from "../StyledLink";
import { faBars, faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import userImg from "../../static/identicon1.jpeg";
import DropdownList from "./DropdownList";
import useDetectClose from "../../hooks/useDetectClose";
import SearchBar from "./SearchBar";
const HeaderContainer = styled.div`
  display: flex;
  flex-direction: row;
  height: 100%;
  align-items: center;
  max-width: 100%;
  width: var(--screen-full);
`;

const Header = () => {
  const navigate = useNavigate();

  const [isOpen, ref, visibleHandler] = useDetectClose(false);

  const isCookieExist = getCookie("Authorization");
  const onLogout = async () => {
    await axios.get("/logout").then(() => {
      if (window.confirm("정말 로그아웃 하시겠습니까?")) {
        removeCookie("Authorization");
        navigate("/");
      }
    });
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
    <header className="fixed top-0 z-40 w-full h-header-height bg-[#f8f9f9] flex justify-center border-b-2 shadow border-t-amber-500	 border-t-4 drop-shadow-xl">
      {/** 드랍다운 */}
      <div className="invisible max-[640px]:visible flex justify-center items-center p-2 ">
        <button onClick={visibleHandler} ref={ref}>
          {isOpen ? (
            <FontAwesomeIcon icon={faXmark} width={24} height={24} />
          ) : (
            <FontAwesomeIcon icon={faBars} width={24} height={24} />
          )}
        </button>
        <Dropdown visibilty={isOpen}>
          <DropdownList />
        </Dropdown>
      </div>
      {/** */}
      <HeaderContainer>
        <Link to={"/"} className="h-full flex items-center justify-center">
          <span className="logo-img h-[30px] w-[150px] mt-[-4px]"></span>
        </Link>
        <div className="h-full flex items-center justify-center grow">
          <SearchBar></SearchBar>
        </div>
        {/** 로그인&비로그인 다르게 보여줌 */}
        {isCookieExist ? (
          <ul className="flex flex-row justify-center items-center gap-3">
            <li role="none">
              <button onClick={goToMyPage}>
                <div className="flex items-center justify-center p-2">
                  <img src={userImg} alt="" width={18} height={18} />
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
                  <path d="m8.9844-0.013672a1 1 0 0 0 -0.98438 1.0137v0.38281l-0.55273-0.27734a1 1 0 0 0 -0.48242 -0.11133 1 1 0 0 0 -0.41211 1.9004l1.4473 0.72266v3.6523l-3.1621-1.8262 0.097656-1.6152a1 1 0 0 0 -0.95117 -1.0742 1 1 0 0 0 -1.0449 0.95508l-0.037109 0.61719-0.33008-0.19141a1 1 0 0 0 -0.57422 -0.14062 1 1 0 0 0 -0.42578 1.8711l0.33203 0.19141-0.51758 0.3418a1 1 0 1 0 1.1016 1.668l1.3516-0.89258 3.1621 1.8262-3.1621 1.8262-1.3516-0.89258a1 1 0 0 0 -0.56445 -0.17383 1 1 0 0 0 -0.53711 1.8418l0.51758 0.3418-0.33203 0.19141a1 1 0 1 0 1 1.7305l0.33008-0.19141 0.037109 0.61719a1 1 0 1 0 1.9961 -0.11914l-0.097656-1.6152 3.1621-1.8262v3.6523l-1.4473 0.72266a1 1 0 0 0 0.89453 1.7891l0.55273-0.27734v0.38281a1 1 0 1 0 2 0v-0.38281l0.55273 0.27734a1 1 0 1 0 0.89453 -1.7891l-1.4473-0.72266v-3.6523l3.1621 1.8262-0.097656 1.6152a1 1 0 1 0 1.9961 0.11914l0.037109-0.61719 0.33008 0.19141a1 1 0 1 0 1 -1.7305l-0.33203-0.19141 0.51758-0.3418a1 1 0 0 0 -0.56641 -1.8418 1 1 0 0 0 -0.53516 0.17383l-1.3516 0.89258-3.1621-1.8262 3.1621-1.8262 1.3516 0.89258a1 1 0 1 0 1.1016 -1.668l-0.51758-0.3418 0.33203-0.19141a1 1 0 0 0 -0.45508 -1.8711 1 1 0 0 0 -0.54492 0.14062l-0.33008 0.19141-0.037109-0.61719a1 1 0 0 0 -0.97461 -0.95508 1 1 0 0 0 -1.0215 1.0742l0.097656 1.6152-3.1621 1.8262v-3.6523l1.4473-0.72266a1 1 0 1 0 -0.89453 -1.7891l-0.55273 0.27734v-0.38281a1 1 0 0 0 -1.0156 -1.0137z"></path>{" "}
                </svg>
              </a>
            </li>
            <li>
              <div className="cursor-pointer">
                <svg width={18} height={18} onClick={onLogout}>
                  <path d="M15 1H3a2 2 0 0 0-2 2v2h16V3a2 2 0 0 0-2-2ZM1 13c0 1.1.9 2 2 2h8v3l3-3h1a2 2 0 0 0 2-2v-2H1v2Zm16-7H1v4h16V6Z"></path>{" "}
                </svg>
              </div>
            </li>
          </ul>
        ) : (
          <ul className="flex flex-row ml-2	gap-2">
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
