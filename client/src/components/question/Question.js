import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";
import { media } from "../../utils/style-utils";
import { getQuestionDetail } from "../../utils/api/api";
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

// "data": {
//   "questionId": 1,
//   "userId": 2,
//   "title": "question Title3",
//   "email": "test2@gmail.com",
//   "nickname": "test2",
//   "content": "테스트2",
//   "tag": "테스트2",
//   "answers": [
//       {
//           "answerId": 1,
//           "userId": 2,
//           "nickname": "test2",
//           "content": "answer2 content"
//       },
//       {
//           "answerId": 2,
//           "userId": 2,
//           "nickname": "test2",
//           "content": "answer2 content"
//       },
//       {
//           "answerId": 3,
//           "userId": 2,
//           "nickname": "test2",
//           "content": "answer3 content"
//       }
//   ],
//   "comments": [],
//   "createdAt": "2022-12-28T07:28:07.859992",
//   "modifiedAt": "2022-12-28T07:28:07.859992",
//   "createdBy": "amdin",
//   "modifiedBy": "amdin"
// }

const Question = ({ questionId }) => {
  const [question, setQuestion] = useState({
    answers: [],
  });
  const navigate = useNavigate();

  useEffect(() => {
    getQuestionDetail(questionId)
      .then((res) => setQuestion(res.data.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <QuestionWrapper>
      <div className="q-stats">
        <span>1 votes</span>
        <span>{question.answers.length} answers</span>
        <span className="text-[#6a737C]">0 views</span>
      </div>
      <div className="q-content">
        <h3 className="q-content--title">
          <Link to={`/questions/${questionId}`}>{question.title}</Link>
        </h3>
        <div className="q-content--body">{question.content}</div>
        <div className="q-content--meta">
          <div className="flex flex-row">
            <Tag
              onClick={() => navigate(`/search?value=%5B${question.tag}%5D`)}
            >
              {question.tag}
            </Tag>
            {/* {question.tag.map((tag, idx) => (
              <Tag key={idx}>{tag}</Tag>
            ))} */}
          </div>
          <div className="flex flex-row items-center justify-end ml-auto">
            <Link to={`/users/${question.userId}`}>
              <div className="flex items-center justify-center">
                <img
                  className="block w-[16px] h-[16px] rounded-[3px]"
                  src="https://www.gravatar.com/avatar/adef0b5893a6615076a5b41cbbcfc7bc?s=256&d=identicon&r=PG"
                  alt={`${question.nickname}'s avatar`}
                />
              </div>
            </Link>
            <div className="h-full items-center">
              <div className="flex items-center text-[12px] text-[#0063bf]">
                <Link to={`/users/${question.userId}`}>
                  {question.nickname}
                </Link>
              </div>
            </div>
            <div className="text-[12px] text-[#6a637c]">
              asked at {question.createdAt} 날짜 고치기
            </div>
          </div>
        </div>
      </div>
    </QuestionWrapper>
  );
};

export default Question;
