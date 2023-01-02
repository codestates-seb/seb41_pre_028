import { useEffect, useState } from "react";
import { patchUserProfile } from "../../utils/api/api";

const UserEdit = ({ user, isEditMode, setIsEditMode }) => {
  const [userInfo, setUserInfo] = useState({
    email: "",
    nickname: "",
  });

  useEffect(() => {
    setUserInfo({
      ...userInfo,
      email: user.email,
      nickname: user.nickname,
    });
  }, [user]);

  const handleChange = (e) => {
    setUserInfo({
      ...userInfo,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = () => {
    patchUserProfile(userInfo);
  };
  return (
    <div>
      <div>
        {isEditMode ? (
          <div className="flex flex-col">
            <div>
              <input
                type="text"
                name="nickname"
                defaultValue={userInfo.nickname}
                onChange={(e) => handleChange(e)}
                className="w-100"
              ></input>
            </div>
            <input
              type="text"
              name="email"
              defaultValue={userInfo.email}
              onChange={(e) => handleChange(e)}
              className="w-100 bg-stone-200"
            ></input>
            <div>
              <button onClick={handleSubmit}>완료</button>
              <button onClick={() => setIsEditMode(false)}>취소</button>
            </div>
          </div>
        ) : (
          <div>
            <div>{user.nickname}</div>
            <div>{user.email}</div>
          </div>
        )}
      </div>
    </div>
  );
};

export default UserEdit;
