import { Link } from "react-router-dom";

const CommentBody = ({ item }) => {
  return (
    <div className="m-2  flex flex-row space-between">
      {/* content */}
      <h4 className="text-[12px] text-[#131414]">{item.content}</h4>

      {/* profile */}
      <div className="flex flex-row h-full items-center">
        <div className="flex items-center text-[12px] text-[#0063bf]">
          <Link to={`/users/${item.userId}`}>{item.createdBy}</Link>
        </div>
        <div className="text-[12px] text-[#6a637c]">
          created at {item.createdAt}
        </div>
      </div>
    </div>
  );
};

export default CommentBody;
