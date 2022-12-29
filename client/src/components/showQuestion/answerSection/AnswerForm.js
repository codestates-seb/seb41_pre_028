import { Button } from "@mui/material";
import { useState } from "react";
import { fetchQuestion } from "../../../utils/api/api";
import { useNavigate } from "react-router-dom";
import { isCookieExist } from "../../../utils/cookie";
import Editor from "../../editor/Editor";

const AnswerForm = ({ answerlist }) => {
  const navigate = useNavigate();

  const [answer, setAnswer] = useState("");

  const onChangeAnswer = (e) => {
    e.preventDefault();
    setAnswer(e.target.value);
  };

  const onClickSubmit = async () => {
    console.log("answer", { answer });
    console.log("answerlist", { answerlist });

    console.log("isCookieExist", isCookieExist);
    if (answer.length < 10) {
      console.log("Minimum 10 characters.");
    } else {
      await fetchQuestion({ answer }).then((questionId) => {
        console.log(questionId);
        navigate(`/questions/${questionId}`);
      });
    }
  };

  return (
    <div>
      <Editor value={answer} onChange={onChangeAnswer} />

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
