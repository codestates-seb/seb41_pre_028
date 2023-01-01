import { useState } from "react";
import { fetchAnswer } from "../../../utils/api/api";
import Editor from "../../editor/Editor";
import { Button } from "@mui/material";

const AnswerForm = ({ questionId }) => {
  const [content, setContent] = useState("");

  const userId = 2;

  const onClickSubmit = async () => {
    if (content.length < 10) {
      alert("Minimum 10 characters.");
    } else {
      await fetchAnswer({ content, questionId, userId }).then((questionId) => {
        console.log(questionId);
        location.reload();
      });
    }
  };

  return (
    <div>
      <Editor value={content} onChange={setContent} />

      <div className="flex flex-row">
        <Button
          variant="contained"
          sx={{ fontSize: 12 }}
          size="large"
          onClick={onClickSubmit}
        >
          Post your Answer
        </Button>
      </div>
    </div>
  );
};

export default AnswerForm;
