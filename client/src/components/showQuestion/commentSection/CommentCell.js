import { useState } from "react";
import { Button } from "@mui/material";
import CommentBody from "./CommentBody.js";
import InputPrimary from "./../../input/inputPrimary";
import { BufferMd5 } from "../../buffer/Buffer";
import { fetchQuestionComment } from "../../../utils/api/api";
import { isCookieExist } from "../../../utils/cookie.js";

const CommentCell = ({ comments, questionId }) => {
  const [content, setContent] = useState("");

  const userId = 2;

  const onChangeContent = (e) => {
    e.preventDefault();
    setContent(e.target.value);
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
      {comments && comments.length > 0 ? (
        comments.map((anItem) => (
          <CommentBody key={anItem.commentId} item={anItem} />
        ))
      ) : (
        <div className="flex flex-row ">0 comment</div>
      )}
      <div className="flex flex-row ">
        <InputPrimary
          placeholder="Add a comment"
          value={content}
          onChange={onChangeContent}
        ></InputPrimary>
        <BufferMd5 />
        <Button
          variant="contained"
          sx={{ fontSize: 12 }}
          size="small"
          onClick={onClickQuestionCommentSubmit}
        >
          Send!
        </Button>
      </div>
    </div>
  );
};
export default CommentCell;
