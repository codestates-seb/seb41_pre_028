import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";
import { media } from "../../utils/style-utils";
import { getQuestionDetail } from "../../utils/api/api";
import userImg from "../../static/identicon1.jpeg";
import Tag from "../StyledTag";
import elapsedTime from "../../utils/elapsedTime";

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

const Question = ({ questionId }) => {
  const [question, setQuestion] = useState({
    answers: [],
    tagList: [],
  });
  const navigate = useNavigate();

  useEffect(() => {
    getQuestionDetail(questionId)
      .then((res) => {
        setQuestion(res.data.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <QuestionWrapper>
      <div className="q-stats">
        <span>{question.vote} votes</span>
        <span>{question.answers.length} answers</span>
        <span className="text-[#6a737C]">3 views</span>
      </div>
      <div className="q-content">
        <h3 className="q-content--title">
          <Link to={`/questions/${questionId}`}>{question.title}</Link>
        </h3>
        <div className="q-content--body">{question.content}</div>
        <div className="q-content--meta">
          <div className="flex flex-row">
            {question.tagList.map((tag, idx) => (
              <Tag onClick={() => navigate(`/search?value=[${tag}]`)} key={idx}>
                {tag}
              </Tag>
            ))}
          </div>
          <div className="flex flex-row items-center justify-end ml-auto">
            <Link to={`/users/${question.userId}`}>
              <div className="flex items-center justify-center mr-[3px]">
                <img
                  className="block w-[16px] h-[16px] rounded-[3px]"
                  src={userImg}
                  alt={`${question.nickname}'s avatar`}
                />
              </div>
            </Link>
            <div className="h-full items-center">
              <div className="flex items-center text-[12px] text-[#0063bf]">
                <Link to={`/users/${question.userId}`}>
                  {question.nickname}
                </Link>
                <div className="ml-1 text-[#6a637c]">
                  asked at {elapsedTime(question.createdAt)}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </QuestionWrapper>
  );
};

export default Question;
