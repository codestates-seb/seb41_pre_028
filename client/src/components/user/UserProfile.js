// import { useState } from "react";
// import UserEdit from "./UserEdit";
const UserProfile = ({ user }) => {
  // const [isEditMode, setIsEditMode] = useState(false);
  return (
    <div className="flex flex-row">
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
      {/* <div>
        <div className="flex flex-row">
          <h3 className="text-[20px]">Profile</h3>
          <div>{isMyPage}</div>
          {!isEditMode ? (
            <button onClick={() => setIsEditMode(!isEditMode)}>Edit</button>
          ) : null}
        </div>
        <UserEdit
          user={user}
          isEditMode={isEditMode}
          setIsEditMode={setIsEditMode}
        />
      </div> */}
    </div>
  );
};

export default UserProfile;
