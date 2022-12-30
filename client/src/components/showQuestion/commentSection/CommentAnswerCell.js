import { useState, useEffect } from "react";
import { Button } from "@mui/material";
import CommentBody from "./CommentBody.js";
import InputPrimary from "./../../input/inputPrimary";
import { BufferMd5 } from "../../buffer/Buffer";
import { fetchAnswerComment } from "../../../utils/api/api.js";
import { isCookieExist } from "../../../utils/cookie.js";

const CommentAnswerCell = ({ answerId }) => {
  const [comments, setComments] = useState("");
  const [content, setContent] = useState("");

  const getData = async () => {
    await fetch(`/answers/${answerId}`)
      .then((response) => response.json())
      .then((res) => setComments(res.data.comments))
      .catch((error) => console.log(error));
  };
  useEffect(() => {
    getData();
  }, []);

  const userId = 2;

  const onChangeContent = (e) => {
    e.preventDefault();
    setContent(e.target.value);
  };

  const onClickAnswerCommentSubmit = async () => {
    console.log("{ content, answerId, userId }", { content, answerId, userId });
    console.log("isCookieExist", isCookieExist);
    if (content.length < 10) {
      console.log("Minimum 10 characters.");
    } else {
      await fetchAnswerComment({ content, answerId, userId }).then(
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
          onClick={onClickAnswerCommentSubmit}
        >
          Send!
        </Button>
      </div>
    </div>
  );
};
export default CommentAnswerCell;
