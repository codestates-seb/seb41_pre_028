import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { userActivityTabList as tabList } from "../../static/filterAndTabList";

import styled from "styled-components";
import { TabButton } from "../StyledButton";
// import { media } from "../../utils/style-utils";

const TabContent = styled.div`
  width: 100%;
  height: 60px;
  padding: 16px;
  border-bottom: 1px solid #e3e6e8;
  display: flex;
  align-items: center;
  &:last-child {
    border-bottom: none;
  }
  > .content--title {
    font-size: 17px;
    color: #0063bf;
  }
`;

const ActivityTabButton = styled(TabButton)`
  width: 90px;
  font-size: 15px;
  &.active {
    background-color: #f1f2f3;
  }
`;

const UserActivity = ({ user }) => {
  const [curTab, setCurTab] = useState(0);
  const [activityList, setActivityList] = useState([]);

  useEffect(() => {
    setActivityList(tabList[curTab].getData(user));
  }, []);

  const handleClick = (tabIndex) => {
    setCurTab(tabIndex);
    setActivityList(tabList[tabIndex].getData(user));
  };

  return (
    <div className="flex sm:flex-row flex-col">
      <nav>
        <ul className="flex flex-row sm:flex-col gap-[3px]">
          {tabList.map((el, idx) => (
            <li key={el.id}>
              <ActivityTabButton
                className={idx === curTab ? "active" : ""}
                onClick={() => handleClick(idx)}
              >
                {el.title}
              </ActivityTabButton>
            </li>
          ))}
        </ul>
      </nav>
      <div className="flex-1 pl-[12px] mt-[10px] sm:pl-0 sm:ml-[15px] sm:mt-0">
        <h3 className="text-[20px] mb-[5px]">{tabList[curTab].title}</h3>
        <div className="border border-[#e3e6e8] rounded-[5px]">
          {activityList.length === 0 ? (
            <TabContent>
              <span>
                {tabList[curTab].zeroContent} by {user.nickname}
              </span>
            </TabContent>
          ) : (
            activityList.map((el) => {
              return (
                <TabContent key={el.id}>
                  <Link
                    to={`/questions/${el.questionId}`}
                    className="content--title"
                  >
                    {el.title}
                  </Link>
                </TabContent>
              );
            })
          )}
          {}
        </div>
      </div>
    </div>
  );
};

export default UserActivity;
