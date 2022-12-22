import { useState } from "react";
import MDEditor from "@uiw/react-md-editor";
import { BufferMd5 } from "../buffer/Buffer.jsx";

const Editor = () => {
  const [value, setValue] = useState("");
  return (
    <div className="container">
      <MDEditor
        data-color-mode="light"
        value={value}
        onChange={setValue}
        preview="edit"
        components={{
          toolbar: (command, disabled, executeCommand) => {
            if (command.keyCommand === "code") {
              return (
                <button
                  aria-label="Insert code"
                  disabled={disabled}
                  onClick={(evn) => {
                    evn.stopPropagation();
                    executeCommand(command, command.groupName);
                  }}
                >
                  Code
                </button>
              );
            }
          },
        }}
      />
      <BufferMd5 />
      <div> {value}</div>
    </div>
  );
};

export default Editor;
