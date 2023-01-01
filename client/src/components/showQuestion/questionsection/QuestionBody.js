import VoteCell from "../VoteCell";
import { Link, useNavigate } from "react-router-dom";
import { BufferMd5 } from "../../buffer/Buffer";
import CommentCell from "../commentSection/CommentCell";
import styled from "styled-components";
import { isCookieExist } from "../../../utils/cookie";
import axios from "axios";
import Tag from "../../StyledTag";
import { useState, useEffect } from "react";
import { getQuestionDetail, getMyProfile } from "../../../utils/api/api";
import elapsedTime from "../../../utils/elapsedTime";
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
  // const userId = item.userId;
  // console.log("userId", userId);
  const [userId, setUserId] = useState(false);
  const [question, setQuestion] = useState({
    tagList: [],
  });

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

  getQuestionDetail(item.questionId)
    .then((res) => {
      setQuestion(res.data.data);
    })
    .catch((err) => console.log(err));

  const onClickDelete = () => {
    axios
      .delete(`/questions/${item.questionId}`, {
        headers: {
          Authorization: isCookieExist,
        },
      })
      .then((res) => {
        console.log(res);
        navigate("/");
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="flex flex-col w-full">
      {/* screen 크기에 맞추기 */}
      <div className="m-4  flex flex-row w-full">
        <VoteCell item={item.userId} />
        <div className="flex flex-col w-full">
          <div className="flex flex-row w-full"> {item.content}</div>
          <BufferMd5 />
          {/* tag */}
          <div className="flex flex-row">
            {question.tagList.map((tag, idx) => (
              <Tag onClick={() => navigate(`/search?value=[${tag}]`)} key={idx}>
                {tag}
              </Tag>
            ))}
          </div>
          {/* profile */}
          <div className="flex flex-row w-max space-between ml-auto">
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
                  <Link
                    to={`/questions/${item.questionId}/edit`}
                    state={{
                      questionId: item.questionId,
                      oldTitle: item.title,
                      oldTags: item.tags,
                      oldContent: item.content,
                    }}
                    className="text-[#6a737C]"
                  >
                    Edit
                  </Link>
                </div>
                <div>
                  <button className="text-[#6a737C]" onClick={onClickDelete}>
                    Delete
                  </button>
                </div>
              </QuestionStats>
            )}
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
          <CommentCell comments={item.comments} questionId={item.questionId} />
        </div>
      </div>
      <BufferMd5 />
    </div>
  );
};

export default QuestionBody;
