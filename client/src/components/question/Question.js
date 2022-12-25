import { Link } from "react-router-dom";
import styled from "styled-components";
import { media } from "../../utils/style-utils";
import Tag from "../StyledTag";

const QuestionWrapper = styled.div`
  display: flex;
  border-bottom: 1px solid #e3e6e8;
  padding: 16px;
  ${media.tablet`
    flex-direction: column;
  `}

  > .q-stats {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    margin-right: 16px;
    width: 108px;
    font-size: 13px;
    gap: 6px;
    text-align: right;

    ${media.tablet`
      flex-direction: row;
      width: 100%;
    `}
  }

  > .q-content {
    width: calc(100% - 108px);
    ${media.tablet`
      width: 100%;
    `}

    > .q-content--title {
      font-size: 17px;
      color: #0063bf;
    }

    > .q-content--body {
      font-size: 13px;
      margin-bottom: 8px;
    }

    > .q-content--meta {
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-wrap: wrap;
      column-gap: 6px;
      row-gap: 8px;
    }
  }
`;

const Question = ({ question }) => {
  return (
    <QuestionWrapper>
      <div className="q-stats">
        <span>{question.votes} votes</span>
        <span>{question.answers} answers</span>
        <span className="text-[#6a737C]">{question.views} views</span>
      </div>
      <div className="q-content">
        <h3 className="q-content--title">
          <Link to={`/questions/${question.id}`}>{question.title}</Link>
        </h3>
        <div className="q-content--body">{question.body}</div>
        <div className="q-content--meta">
          <div className="flex flex-row">
            {question.tag.map((tag, idx) => (
              <Tag key={idx}>{tag}</Tag>
            ))}
          </div>
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
              asked at {question.asked_at} 날짜 고치기
            </div>
          </div>
        </div>
      </div>
    </QuestionWrapper>
  );
};

export default Question;
