import { useEffect, useState } from "react";
import { getQuestionList } from "../../api/question";

const QuestionList = () => {
  const [questionList, setQuestionList] = useState([]);

  useEffect(() => {
    getQuestionList()
      .then((res) => setQuestionList(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      {questionList.map((q) => (
        <div key={q.id}>
          <span>title: {q.title}</span>
          <span>votes: {q.votes}</span>
        </div>
      ))}
    </div>
  );
};

export default QuestionList;
