import VoteCell from "../VoteCell";
import { Link } from "react-router-dom";
import { BufferMd5 } from "../../buffer/Buffer";
import CommentCell from "../commentSection/CommentCell";
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

const QuestionBody = ({ item }) => {
  return (
    <div className="flex flex-col w-full">
      {/* screen 크기에 맞추기 */}
      <div className="m-4  flex flex-row w-full">
        <VoteCell item={item.userId} />
        <div className="flex flex-row w-full"> {item.content}</div>
      </div>
      <BufferMd5 />
      {/* tag */}
      {/* profile */}
      <div className="flex flex-row items-center space-between ml-auto">
        <QuestionStats>
          <div>
            <Link
              to={`/questions/${item.questionId}`}
              className="text-[#6a737C]"
            >
              Share
            </Link>
          </div>
          <div>
            <Link to={`/questions/create`} className="text-[#6a737C]">
              Edit
            </Link>
          </div>
          <div>
            <Link
              to={`/questions/${item.questionId}`}
              className="text-[#6a737C]"
            >
              Follow
            </Link>
          </div>
        </QuestionStats>

        <Link to={`/users/${item.userId}`}>
          <div className="flex items-center justify-center">
            <img
              className="block w-[32px] h-[32px] rounded-[3px]"
              src="https://www.gravatar.com/avatar/adef0b5893a6615076a5b41cbbcfc7bc?s=256&d=identicon&r=PG"
              alt={`${item.nickname}'s avatar`}
            />
          </div>
        </Link>
        <div className="flex flex-col">
          <div className="text-[12px] text-[#6a637c]">
            asked at {item.createdAt}
          </div>
          <div className="h-full items-center">
            <div className="flex items-center text-[12px] text-[#0063bf]">
              <Link to={`/users/${item.userId}`}>{item.createdBy}</Link>
            </div>
          </div>
        </div>
      </div>
      {/* comment */}
      <CommentCell comments={item.comments} questionId={item.questionId} />
    </div>
  );
};

export default QuestionBody;
