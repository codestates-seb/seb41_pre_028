import { useState } from "react";
import { Button } from "@mui/material";
import CommentBody from "./CommentBody.js";
import InputPrimary from "./../../input/inputPrimary";
import { BufferMd5 } from "../../buffer/Buffer";

const CommentCell = ({ comments }) => {
  const [value, setValue] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    value;
  };
  return (
    <div className="comment">
      {comments && comments.length > 0
        ? comments.map((anItem) => (
            <CommentBody key={anItem.commentId} item={anItem} />
          ))
        : ""}
      <div className="flex flex-row ">
        <InputPrimary
          placeholder="Add a comment"
          onSubmit={(e) => handleSubmit(e)}
        >
          <div>
            <input
              className="title-input s-input"
              type="text"
              name="body"
              value={value}
              onChange={setValue}
              id="title"
              placeholder="Leave a comment"
            />
          </div>
        </InputPrimary>
        <BufferMd5 />
        <Button
          variant="contained"
          sx={{ fontSize: 12 }}
          size="small"
          onClick={() => setValue(value + 1)}
        >
          Send!
        </Button>
      </div>
    </div>
  );
};
export default CommentCell;
