import styled from "styled-components";
import { Link } from "react-router-dom";

const QuestionWrapper = styled.div``;

const Question = ({ question }) => {
  return (
    <QuestionWrapper>
      <div>
        <span>{question.votes} votes</span>
        <span>{question.answers} answers</span>
        <span>{question.views} views</span>
      </div>
      <Link to={`/question/${question.id}`}>{question.title}</Link>
      <div>{question.body}</div>
      <div>
        <div>
          {question.tag.map((tag, idx) => (
            <span key={idx}>{tag}</span>
          ))}
        </div>
        <div>{question.author}</div>
      </div>
    </QuestionWrapper>
  );
};

export default Question;
