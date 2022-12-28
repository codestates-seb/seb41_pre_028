import { BufferMr5 } from "../../buffer/Buffer";
import { Button } from "@mui/material";
import Editor from "../../editor/Editor";

const AnswerForm = () => {
  return (
    <div>
      <Editor></Editor>

      <div className="flex flex-row">
        <Button variant="contained" sx={{ fontSize: 12 }} size="large">
          Post your question
        </Button>
        <BufferMr5 />
        <Button color="error" sx={{ fontSize: 12 }} size="large">
          Discard
        </Button>{" "}
      </div>
    </div>
  );
};

export default AnswerForm;
