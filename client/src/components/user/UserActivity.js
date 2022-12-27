import { useEffect, useState } from "react";
import { userActivityTabList as tabList } from "../../static/filterAndTabList";

const UserActivity = ({ userId }) => {
  const [curTab, setCurTab] = useState(0);

  useEffect(() => {
    //axios get 요쳥 by userID
    console.log(tabList[curTab].getData);
  }, [curTab]);

  return (
    <div className="flex flex-row">
      <nav>
        <ul>
          {tabList.map((el, idx) => (
            <li key={el.id} className={idx === curTab ? "focus" : ""}>
              <button onClick={() => setCurTab(idx)}>{el.title}</button>
            </li>
          ))}
        </ul>
      </nav>
      <div>
        <h2>{userId}의 activity</h2>
      </div>
    </div>
  );
};

export default UserActivity;
