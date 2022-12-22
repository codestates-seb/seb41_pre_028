import { useState } from "react";

const InputPrimary = ({ placeholder }) => {
  const [title, setTitle] = useState("");
  const onChangeTitle = (e) => {
    setTitle(e.target.value);
  };

  return (
    <div>
      <input
        id="title"
        name="title"
        type="text"
        value={title}
        maxLength="300"
        placeholder={placeholder}
        className="w-full px-4 py-2 border rounded border-soGray-light"
        data-min-length="15"
        data-max-length="150"
        onChange={onChangeTitle}
      />
      <div className="flex flex-column items-center">{title}</div>
    </div>
  );
};

export default InputPrimary;
