import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getUserProfile } from "../utils/api/users";
import { userPageTabList as tabList } from "../static/filterAndTabList";
import styled from "styled-components";
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

const UserPage = () => {
  const { userId } = useParams();
  const [userPageTab, setUserPageTab] = useState(0);
  const [user, setUser] = useState({
    name: "",
    avatar_img: "",
  });

  useEffect(() => {
    // id가 userId인 user 정보를 get 해와야함
    getUserProfile(userId)
      .then((res) => {
        setUser(res.data);
      })
      .catch((err) => console.log(err));
  }, [userId]);

  return (
    <ContentWrapper className="content">
      <main>
        <Profile>
          <div className="profile--img">
            <div className="flex items-center justify-center">
              <img
                className="block w-[128px] h-[128px] rounded-[3px]"
                src={user.avatar_img}
                alt={`${user.name}'s avatar`}
              />
            </div>
          </div>
          <div className="profile--content">
            <div className="text-[34px] mb-[10px]">{user.name}</div>
            <div>부가적인 정보들....</div>
          </div>
        </Profile>
        <div>
          <nav>
            <ul className="flex flex-row">
              {tabList.map((el, idx) => (
                <li key={el.id}>
                  <button onClick={() => setUserPageTab(idx)}>
                    {el.title}
                  </button>
                </li>
              ))}
            </ul>
          </nav>
          <div>{tabList[userPageTab].showContent(userId)}</div>
        </div>
      </main>
    </ContentWrapper>
  );
};

export default UserPage;
