import { PrimaryButton } from "./StyledButton";
import { isCookieExist } from "../utils/cookie";
import { useNavigate } from "react-router-dom";

const AskQuestionButton = () => {
  const navigate = useNavigate();
  const handleClick = () => {
    if (isCookieExist) {
      navigate("/createQuestion");
    } else {
      navigate("/login");
    }
  };
  return <PrimaryButton onClick={handleClick}>Ask Question</PrimaryButton>;
};

export default AskQuestionButton;
