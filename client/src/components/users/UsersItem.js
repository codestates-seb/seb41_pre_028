import UserCard from "./UserCard";
import { useState, useEffect } from "react";
const UserItem = () => {
  const [users, setUsers] = useState([]);
  const getUsers = async () => {
    await fetch("https://random-data-api.com/api/v2/users?size=36")
      .then((res) => res.json())
      .then((data) => setUsers(data));
  };
  console.log(users);
  useEffect(() => {
    getUsers();
  }, []);
  const renderUsersList = () => {
    return users.map((user) => {
      return <UserCard key={user.id} {...user} />;
    });
  };

  return (
    <div className="grid gap-5 grid-cols-4 max-[1240px]:grid-cols-3 max-[980px]:grid-cols-2 max-[640px]:grid-cols-1">
      {renderUsersList()}
    </div>
  );
};
export default UserItem;
