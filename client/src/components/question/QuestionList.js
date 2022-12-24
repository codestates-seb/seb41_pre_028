import { useEffect, useState } from "react";
import { getQuestionList } from "../../utils/api/question";
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
      <div className="flex flex-row justify-between">
        <div>23,350 questions</div>
        <div>
          <div>filter zone</div>
        </div>
      </div>
      <div className="border-t border-[#e3e6e8]">
        {questionList.map((el) => (
          <Question key={el.id} question={el}></Question>
        ))}
      </div>
    </div>
  );
};

export default QuestionList;
