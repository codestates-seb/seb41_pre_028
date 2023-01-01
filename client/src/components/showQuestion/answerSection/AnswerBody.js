import VoteCell from "../VoteCell";
import { Link, useNavigate } from "react-router-dom";
import { BufferMd5 } from "../../buffer/Buffer";
import styled from "styled-components";
import { useState, useEffect } from "react";
import CommentAnswerCell from "../commentSection/CommentAnswerCell";
import { isCookieExist } from "../../../utils/cookie";
import { getMyProfile } from "../../../utils/api/api";
import elapsedTime from "../../../utils/elapsedTime";
import axios from "axios";
const QuestionStats = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  margin: 12px;
  font-size: 13px;
  gap: 6px;
  text-align: right;
`;

const AnswerBody = ({ item }) => {
  const [userId, setUserId] = useState(false);
  const [editModeOn, setEditModeOn] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    const SetupUserPage = () => {
      if (isCookieExist) {
        getMyProfile()
          .then((res) => {
            setUserId(res.data.userId);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    };

    SetupUserPage();
  }, [userId]);

  const onClickDeleteAnswers = () => {
    axios
      .delete(`/answers/${item.answerId}`, {
        headers: {
          Authorization: isCookieExist,
        },
      })
      .then((res) => {
        console.log(res);
        navigate("/questions");
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="flex flex-col">
      <div className="m-4  flex flex-row w-full">
        <VoteCell item={item.userId} />
        <div className="flex flex-col w-full">
          <div className="flex flex-row w-full">{item.content}</div>
          <BufferMd5></BufferMd5>
          {/* profile */}
          <div className="flex flex-row items-center space-between ml-auto">
            {!(userId === item.userId) ? (
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
                  <Link
                    to={`/questions/${item.questionId}`}
                    className="text-[#6a737C]"
                  >
                    Follow
                  </Link>
                </div>
              </QuestionStats>
            ) : (
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
                  <button onClick={() => setEditModeOn(!editModeOn)}>
                    Edit
                  </button>
                </div>
                <div>
                  <button
                    className="text-[#6a737C]"
                    onClick={onClickDeleteAnswers}
                  >
                    Delete
                  </button>
                </div>
              </QuestionStats>
            )}

            {/* profile */}
            <div className="flex flex-row items-center  ml-auto">
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
                  asked at {elapsedTime(item.createdAt)}
                </div>
                <div className="h-full items-center">
                  <div className="flex items-center text-[12px] text-[#0063bf]">
                    <Link to={`/users/${item.userId}`}>{item.nickname}</Link>
                  </div>
                </div>
              </div>
            </div>
          </div>
          {/* comment */}
          <CommentAnswerCell answerId={item.answerId} />
        </div>
      </div>
    </div>
  );
};

export default AnswerBody;
