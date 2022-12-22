import { useState } from "react";
import MDEditor from "@uiw/react-md-editor";
import Buffer from "../buffer/buffer";

const Editor = () => {
  const [value, setValue] = useState("");
  return (
    <div className="container">
      <MDEditor
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
      <Buffer />
      <div> {value}</div>
    </div>
  );
};

export default Editor;
