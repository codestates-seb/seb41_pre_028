import { useState } from "react";
const InputPrimary = ({ placeholder }) => {
  const [input, setInput] = useState("");
  const onChangeTitle = (e) => {
    setInput(e.target.value);
  };

  return (
    <div>
      <input
        id="title"
        name="title"
        type="text"
        value={input}
        maxLength="300"
        placeholder={placeholder}
        className="w-full px-4 py-2 border rounded border-soGray-light"
        data-min-length="15"
        data-max-length="150"
        onChange={onChangeTitle}
      />
    </div>
  );
};

export default InputPrimary;
