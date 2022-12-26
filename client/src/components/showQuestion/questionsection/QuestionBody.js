import VoteCell from "../VoteCell";
import { Link } from "react-router-dom";
import { BufferMd5 } from "../../buffer/Buffer.jsx";
import CommentCell from "../CommentCell.js";
import styled from "styled-components";

const QuestionStats = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  margin: 12px;
  font-size: 13px;
  gap: 6px;
  text-align: right;
`;

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
      <div className="flex flex-row items-center space-between ml-auto">
        <QuestionStats>
          <div>
            <Link to={`/questions/${question.id}`} className="text-[#6a737C]">
              Share
            </Link>
          </div>
          <div>
            <Link to={`/questions/create`} className="text-[#6a737C]">
              Edit
            </Link>
          </div>
          <div>
            <Link to={`/questions/${question.id}`} className="text-[#6a737C]">
              Follow
            </Link>
          </div>
        </QuestionStats>

        <Link to={`/users/${question.author.id}`}>
          <div className="flex items-center justify-center">
            <img
              className="block w-[16px] h-[16px] rounded-[3px]"
              src={question.author.avatar_img}
              alt={`${question.author.name}'s avatar`}
            />
          </div>
        </Link>
        <div className="flex flex-col">
          <div className="text-[12px] text-[#6a637c]">
            asked at {question.askedAt}
          </div>
          <div className="h-full items-center">
            <div className="flex items-center text-[12px] text-[#0063bf]">
              <Link to={`/users/${question.author.id}`}>
                {question.author.name}
              </Link>
            </div>
          </div>
        </div>
      </div>
      {/* comment */}
      <CommentCell />
    </div>
  );
};

export default QuestionBody;
