import { useState } from "react";
import { Button } from "@mui/material";
import CommentBody from "./CommentBody.js";
import InputPrimary from "./../../input/inputPrimary";
import { BufferMd5, BufferBorder2 } from "../../buffer/Buffer";
import { fetchQuestionComment } from "../../../utils/api/api";
import { isCookieExist } from "../../../utils/cookie.js";
import { useNavigate } from "react-router-dom";

const CommentCell = ({ comments, questionId }) => {
  const [content, setContent] = useState("");
  const [isEditing, setIsEditing] = useState(false); // input 숨기기
  const navigate = useNavigate();

  const userId = 2;

  const onChangeContent = (e) => {
    e.preventDefault();
    setContent(e.target.value);
  };
  const commentToggle = () => {
    if (isCookieExist) {
      setIsEditing(!isEditing);
    } else {
      navigate("/login");
    }
  };

  const onClickQuestionCommentSubmit = async () => {
    console.log("value", { content, questionId, userId });
    console.log("isCookieExist", isCookieExist);
    if (content.length < 10) {
      console.log("Minimum 10 characters.");
    } else {
      await fetchQuestionComment({ content, questionId, userId }).then(
        (questionId) => {
          console.log(questionId);
          location.reload();
        }
      );
    }
  };

  return (
    <div className="comment">
      <BufferBorder2></BufferBorder2>
      <div className="commentMap">
        {comments && comments.length > 0
          ? comments.map((anItem) => (
              <CommentBody key={anItem.commentId} item={anItem} />
            ))
          : ""}
      </div>
      <BufferMd5 />

      <div className="flex flex-row ">
        <button className="text-[12px] text-[#515252]" onClick={commentToggle}>
          Add a comment
        </button>
        {isEditing ? (
          <div className="flex flex-row w-full">
            <InputPrimary
              className="flex w-max h-3"
              placeholder="Add a comment"
              value={content}
              onChange={onChangeContent}
            ></InputPrimary>
            <BufferMd5 />
            <Button
              className="flex flex-row w-5"
              sx={{ fontSize: 12 }}
              size="small"
              onClick={onClickQuestionCommentSubmit}
            >
              Send!
            </Button>
          </div>
        ) : (
          ""
        )}
      </div>
    </div>
  );
};
export default CommentCell;
