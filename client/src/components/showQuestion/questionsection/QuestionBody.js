import VoteCell from "../VoteCell";
import { Link } from "react-router-dom";
import { BufferMd5 } from "../../buffer/Buffer.jsx";
import CommentCell from "../CommentCell.js";

const QuestionBody = ({ question }) => {
  return (
    <div className="flex flex-col">
      <div className="m-4 flex flex-row">
        <VoteCell item={question.voted} />
        <div> {question.content}</div>
      </div>
      <BufferMd5 />
      {/* tag */}

      {/* profile */}
      <div className="flex flex-row items-center justify-end ml-auto">
        <Link to={`/users/${question.author.id}`}>
          <div className="flex items-center justify-center">
            <img
              className="block w-[16px] h-[16px] rounded-[3px]"
              src={question.author.avatar_img}
              alt={`${question.author.name}'s avatar`}
            />
          </div>
        </Link>
        <div className="h-full items-center">
          <div className="flex items-center text-[12px] text-[#0063bf]">
            <Link to={`/users/${question.author.id}`}>
              {question.author.name}
            </Link>
          </div>
        </div>
        <div className="text-[12px] text-[#6a637c]">
          asked at {question.askedAt}
        </div>
      </div>
      {/* comment */}
      <CommentCell />
    </div>
  );
};

export default QuestionBody;
