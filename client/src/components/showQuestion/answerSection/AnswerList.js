import AnswerBody from "./AnswerBody";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

function AnswerList({ answerList }) {
  const [answers, setAnswers] = useState([]);
  const getData = async () => {
    await fetch(`/answers/${questionId}`)
      .then((response) => response.json())
      .then((data) => setAnswers(data))
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getData();
  }, []);

  // const getAnswerList = (params) => axios.get("/answers", { params });

  const { questionId } = useParams();
  return (
    <div className="answers-group">
      {answers && answerList.length > 0 ? (
        <h3 className="px-6 mb-5 pb-4 text-2xl border-b border-soGray-light">
          {answerList.length} Answer
        </h3>
      ) : (
        <h3 className="px-6 mb-5 pb-4 text-2xl border-b border-soGray-light">
          0 Answer
        </h3>
      )}

      {answerList && answerList.length > 0
        ? answerList.map((anItem) => (
            <AnswerBody key={anItem.userId} item={anItem} />
            // 고유키 AnswerID 가 필요함
          ))
        : ""}
    </div>
  );
}

export default AnswerList;
