import { useEffect, useState } from "react";

const UserProfile = ({ user, isMyPage }) => {
  const [editModeOn, setEditModeOn] = useState(false);

  useEffect(() => {}, [isMyPage]);
  return (
    <div>
      <div>{user.userId}의 프로파일</div>
      <div className="flex flex-row">
        <div>
          <div>Stats</div>
          {isMyPage ? (
            <button onClick={() => setEditModeOn(!editModeOn)}>Edit</button>
          ) : null}
        </div>
        {editModeOn ? (
          <div>
            <div>
              <input type="text" className="w-100 bg-stone-200"></input>
              <input type="text" className="w-100 bg-stone-200"></input>
            </div>
          </div>
        ) : null}
      </div>
    </div>
  );
};

export default UserProfile;
