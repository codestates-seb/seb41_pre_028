import { useState } from "react";
import MDEditor from "@uiw/react-md-editor";
import { BufferMd5 } from "../buffer/Buffer.jsx";

const Editor = () => {
  const [value, setValue] = useState("");
  return (
    <div className="z-0 container">
      <MDEditor
        data-color-mode="light"
        value={value}
        onChange={setValue}
        preview="edit"
      />
      <BufferMd5 />
      <div> {value}</div>
    </div>
  );
};

export default Editor;
