import { useEffect, useState } from "react";
import { userActivityTabList as tabList } from "../../static/filterAndTabList";

// {
//   "data": {
//       "userId": 2,
//       "nickname": "test1",
//       "email": "test1@email.com",
//       "reputation": null,
//       "answers": [],
//       "questions": [
//           {
//               "questionId": 1,
//               "title": "aaa"
//           }
//       ]
//   }
// }

const UserActivity = ({ user }) => {
  const [curTab, setCurTab] = useState(0);
  const [activityList, setActivityList] = useState([]);

  useEffect(() => {
    setActivityList(tabList[curTab].getData(user));
  }, []);

  const handleClick = (tabIndex) => {
    setCurTab(tabIndex);
    console.log(tabList[tabIndex].getData(user));
    setActivityList(tabList[tabIndex].getData(user));
  };

  return (
    <div className="flex flex-row">
      <nav>
        <ul>
          {tabList.map((el, idx) => (
            <li key={el.id} className={idx === curTab ? "focus" : ""}>
              <button onClick={() => handleClick(idx)}>{el.title}</button>
            </li>
          ))}
        </ul>
      </nav>
      <div>
        <h2>{activityList.id}의 activity</h2>
      </div>
    </div>
  );
};

export default UserActivity;
