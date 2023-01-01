import { useState, useEffect } from "react";
import { Button } from "@mui/material";
import CommentBody from "./CommentBody.js";
import InputPrimary from "./../../input/inputPrimary";
import { BufferMd5, BufferBorder2 } from "../../buffer/Buffer";
import { fetchAnswerComment } from "../../../utils/api/api";
import { isCookieExist } from "../../../utils/cookie.js";
import { useNavigate } from "react-router-dom";
import axios from "../../../utils/api/axios";

const CommentAnswerCell = ({ answerId }) => {
  const [comments, setComments] = useState("");
  const [content, setContent] = useState("");
  const [isEditing, setIsEditing] = useState(false); // input 숨기기
  const navigate = useNavigate();

  const getData = async () => {
    await axios(`/answers/${answerId}`)
      .then((res) => {
        setComments(res.data.data.comments);
      })
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

  const commentToggle = () => {
    if (isCookieExist) {
      setIsEditing(!isEditing);
    } else {
      navigate("/login");
    }
  };

  const onClickAnswerCommentSubmit = async () => {
    if (content.length < 10) {
      alert("Minimum 10 characters.");
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
      <BufferBorder2 />

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
              className="flex w-max"
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
        ) : (
          ""
        )}
      </div>
    </div>
  );
};
export default CommentAnswerCell;
