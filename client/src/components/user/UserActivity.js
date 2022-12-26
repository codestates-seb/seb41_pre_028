import { useEffect, useState } from "react";

const UserActivity = ({ userId }) => {
  const [curTab, setCurTab] = useState(0);

  const tabList = [
    {
      id: 1,
      getData: "",
      title: "Answers",
    },
    {
      id: 2,
      getData: "",
      title: "Questions",
    },
  ];

  useEffect(() => {
    //axios get 요쳥 by userID
  }, [curTab]);

  return (
    <div className="flex flex-row">
      <h2>{userId}의 activity</h2>
      <nav>
        <ul>
          {tabList.map((el, idx) => (
            <li key={el.id} className={idx === curTab ? "focus" : ""}>
              <button onClick={() => setCurTab(idx)}>{el.title}</button>
            </li>
          ))}
        </ul>
      </nav>
      <div></div>
    </div>
  );
};

export default UserActivity;
