import QuestionHeader from "../components/showQuestion/questionsection/QuestionHeader";
import QuestionBody from "../components/showQuestion/questionsection/QuestionBody";
import AnswerList from "../components/showQuestion/answerSection/AnswerList";
import AnswerForm from "../components/showQuestion/answerSection/AnswerForm";
import { BufferMd5 } from "../components/buffer/Buffer.jsx";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";

const ShowQuestionPage = () => {
  const [question, setQuestion] = useState([]);
  const getData = async () => {
    await fetch(`/questions/${questionId}`)
      .then((response) => response.json())
      .then((data) => setQuestion(data))
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    getData();
  }, []);

  const { questionId } = useParams();

  return (
    <div className="content">
      <div className="flex flex-col">
        <div className="question">
          <QuestionHeader question={question}></QuestionHeader>
          <QuestionBody item={question}></QuestionBody>
        </div>
        <BufferMd5 />

        <div className="answer">
          <AnswerList></AnswerList>
          <div>
            <h1 className="m-4 text-2xl">Your Answers</h1>
          </div>
          <AnswerForm />
        </div>
      </div>
      <BufferMd5 />
    </div>
  );
};

export default ShowQuestionPage;
