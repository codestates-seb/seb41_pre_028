import { useState } from "react";
import { fetchAnswer } from "../../../utils/api/api";
import { isCookieExist } from "../../../utils/cookie";
import Editor from "../../editor/Editor";
import { Button } from "@mui/material";

const AnswerForm = ({ questionId }) => {
  console.log("questionId", { questionId });

  const [content, setContent] = useState("");

  const userId = 2;

  const onClickSubmit = async () => {
    console.log("content", { content, questionId, userId });

    console.log("isCookieExist", isCookieExist);
    if (content.length < 10) {
      console.log("Minimum 10 characters.");
    } else {
      await fetchAnswer({ content, questionId, userId }).then((questionId) => {
        console.log(questionId);
        location.reload();
      });
    }
  };

  return (
    <div className="ml-10">
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
