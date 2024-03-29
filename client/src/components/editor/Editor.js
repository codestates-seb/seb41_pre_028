import MDEditor from "@uiw/react-md-editor";
import { BufferMd5 } from "../buffer/Buffer";

const Editor = ({ value, onChange }) => {
  return (
    <div className="flex flex-col z-0 container">
      {/* 크기를 최대로 조정해야함 */}
      <MDEditor
        className="flex min-w-max"
        data-color-mode="light"
        value={value}
        onChange={onChange}
        preview="edit"
      />
      <BufferMd5 />
      <div> {value}</div>
    </div>
  );
};

export default Editor;
