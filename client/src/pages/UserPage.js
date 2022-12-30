import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getUserProfile } from "../utils/api/api";
import { userPageTabList as tabList } from "../static/filterAndTabList";
import styled from "styled-components";
import { TabButton } from "../components/StyledButton";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEnvelope, faCakeCandles } from "@fortawesome/free-solid-svg-icons";
// import { media } from "../utils/style-utils";
const ContentWrapper = styled.div`
  display: flex;
  padding: 24px;

  > main {
    width: 100%;
  }
`;

const Profile = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;

  .profile--img {
    margin-right: 8px;
    margin-bottom: 8px;
  }

  .profile--content {
    margin-left: 8px;
  }
`;

const UserTabButton = styled(TabButton)`
  &.active {
    background-color: #f48224;
    color: white;
  }
`;

const UserPage = () => {
  const { userId } = useParams();
  const [userPageTab, setUserPageTab] = useState(0);
  const [errMsg, setErrMsg] = useState("");
  const [user, setUser] = useState({
    nickname: "",
    email: "",
    createdAt: "",
  });

  useEffect(() => {
    // id가 userId인 user 정보를 get 해와야함
    setUser({ ...user, name: "yerin" });
    getUserProfile(userId)
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => {
        if (err.response.status === 404) {
          setErrMsg("존재하지 않는 사용자입니다.");
        }
      });
  }, [userId]);

  return (
    <ContentWrapper className="content">
      {!errMsg ? (
        <main>
          <Profile>
            <div className="profile--img">
              <div className="flex items-center justify-center">
                <img
                  className="block w-[128px] h-[128px] rounded-[3px]"
                  src="https://www.gravatar.com/avatar/adef0b5893a6615076a5b41cbbcfc7bc?s=256&d=identicon&r=PG"
                  alt={`${user.nickname}'s avatar`}
                />
              </div>
            </div>
            <div className="profile--content">
              <div className="text-[34px] mb-[10px]">{user.nickname}</div>
              <div className="flex flex-row gap-[6px] text-[#6A737C]">
                <div>
                  <FontAwesomeIcon icon={faEnvelope} />
                  <span className="ml-[3px]">{user.email}</span>
                </div>
                <div>
                  <FontAwesomeIcon icon={faCakeCandles} />
                  <span>{user.createdAt}</span>
                </div>
              </div>
            </div>
          </Profile>
          <div>
            <nav>
              <ul className="flex flex-row">
                {tabList.map((el, idx) => (
                  <li key={el.id}>
                    <UserTabButton
                      className={userPageTab === idx ? "active" : ""}
                      onClick={() => setUserPageTab(idx)}
                    >
                      {el.title}
                    </UserTabButton>
                  </li>
                ))}
              </ul>
            </nav>
            <div>{tabList[userPageTab].showContent(userId)}</div>
          </div>
        </main>
      ) : (
        <div>{errMsg}</div>
      )}
    </ContentWrapper>
  );
};

export default UserPage;
