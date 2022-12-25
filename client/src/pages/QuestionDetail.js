import { useParams } from "react-router-dom";

const QuestionDetail = () => {
  const { qId } = useParams();
  return <div>질문 넘버: {qId}의 페이지</div>;
};

export default QuestionDetail;
