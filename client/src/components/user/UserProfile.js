import { useEffect, useState } from "react";

const UserProfile = ({ user, isMyPage }) => {
  const [editModeOn, setEditModeOn] = useState(false);

  useEffect(() => {
    console.log(user);
  }, [isMyPage]);
  return (
    <div className="flex flex-row">
      <div>
        <div>
          <h3 className="text-[20px]">Stats</h3>
          <div className="flex flex-row border border-[#e3e6e8] rounded-[5px]">
            <div className="flex-1 p-[10px] w-[130px]">
              <div className="text-[20px]">{user.answers.length}</div>
              <div className="text-[15px]">answers</div>
            </div>
            <div className="border-l flex-1 p-[10px] w-[130px]">
              <div className="text-[20px]">{user.questions.length}</div>
              <div className="text-[15px]">questions</div>
            </div>
          </div>
        </div>

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
  );
};

export default UserProfile;
