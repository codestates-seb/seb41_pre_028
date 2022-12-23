import { useEffect, useState } from "react";
import { getQuestionList } from "../../api/question";
import { PrimaryLink } from "../StyledLink";
import Question from "./Question";

const QuestionList = () => {
  const [questionList, setQuestionList] = useState([]);

  useEffect(() => {
    getQuestionList()
      .then((res) => setQuestionList(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <div>
        <div className="flex flex-row items-center justify-between">
          <h1 className="text-title-size">All Questions</h1>
          <PrimaryLink to={"/createQuestion"}>Ask Question</PrimaryLink>
        </div>
        <div className="flex flex-row">
          <div>23,350 questions</div>
          <div>
            <div>filter zone</div>
          </div>
        </div>
        <div>
          {questionList.map((el) => (
            <Question key={el.id} question={el}></Question>
          ))}
        </div>
      </div>
      <div></div>
    </div>
  );
};

export default QuestionList;
