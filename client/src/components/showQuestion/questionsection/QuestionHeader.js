import styled from "styled-components";
import AskQuestionButton from "../../AskQuestionButton";
import elapsedTime from "../../../utils/elapsedTime";
import { Link } from "react-router-dom";
import { BufferBorder1 } from "../../buffer/Buffer";

const QuestionStats = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  margin: 12px;
  font-size: 13px;
  gap: 6px;
  text-align: right;
`;

const QuestionHeader = ({ question }) => {
  return (
    <div>
      <div className="mx-3 flex flex-row items-end justify-between">
        <h1 className="p-0 mt-4 mb-1 font-medium text-3xl">
          <Link to={`/questions`}>{question.title}</Link>
        </h1>
        <AskQuestionButton />
      </div>
      <QuestionStats>
        <div>
          <span className="text-[#6a737C]">Asked at</span>
          <span> {elapsedTime(question.createdAt)}</span>
        </div>
        <div>
          <span className="text-[#6a737C]">Modified </span>
          <span>{question.updatedAt}</span>
        </div>
        <div>
          <span className="text-[#6a737C]">viewed </span>
          <span> {question.userId} times</span>
        </div>
      </QuestionStats>
      <BufferBorder1 />
    </div>
  );
};

export default QuestionHeader;
