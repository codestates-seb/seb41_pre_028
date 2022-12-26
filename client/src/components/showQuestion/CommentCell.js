import InputPrimary from "../input/inputPrimary.jsx";
import { useState } from "react";

const CommentCell = () => {
  const [value, setValue] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    value;
  };
  return (
    <div className="add-comment">
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
    </div>
  );
};
export default CommentCell;
