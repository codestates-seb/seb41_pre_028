import { Link } from "react-router-dom";
const UserCard = ({ first_name, avatar, employment, address, id }) => {
  return (
    <div>
      <div className="flex">
        <Link to={`/users/${id}`}>
          <img className="w-12 h-12" src={avatar} alt="" />
        </Link>
        <div className="flex flex-col ml-2">
          <Link className="text-sky-700	" to={`/users/${id}`}>
            {first_name}
          </Link>
          <div className="font-light text-xs">{address.state}</div>
        </div>
      </div>
      <div className="ml-14 font-light text-sm text-sky-700">
        {employment.key_skill}
      </div>
    </div>
  );
};
export default UserCard;
