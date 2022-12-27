import styled from "styled-components";
import { media } from "../utils/style-utils";
import SideBar from "../components/sideBar/SideBar";
import QuestionHeader from "../components/showQuestion/questionsection/QuestionHeader";
import QuestionBody from "../components/showQuestion/questionsection/QuestionBody";
import AnswerList from "../components/showQuestion/answerSection/AnswerList";
import AnswerForm from "../components/showQuestion/answerSection/AnswerForm";
import Footer from "../components/footer/footer";
import { BufferMd5 } from "../components/buffer/Buffer.jsx";
import question from "../components/showQuestion/question";

export const PageContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

export const MainContainer = styled.div`
  padding-top: var(--h-header);
  display: flex;
  max-width: 1264px;
  width: 100%;
  > .side-bar {
    width: 164px;

    ${media.mobile`
        display: none;
    `}
  }
  > .content {
    width: calc(100% - 164px);
    max-width: 1100px;
    ${media.mobile`
        width: 100%;
    `}
  }
`;

const ShowQuestionPage = () => {
  // const [question, setQuestion] = useState([]);
  // const getData = async () => {
  //   await fetch("https://koreanjson.com/posts/1")
  //     .then((response) => response.json())
  //     .then((data) => setQuestion(data))
  //     .catch((error) => console.log(error));
  // };

  // useEffect(() => {
  //   getData();
  // }, []);

  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <div className="flex flex-col">
          <div className="question">
            <QuestionHeader question={question}></QuestionHeader>
            <QuestionBody item={question}></QuestionBody>
          </div>
          <BufferMd5 />

          <div className="answer">
            <AnswerList answerList={question.answers}></AnswerList>
            <div>
              <h1 className="m-4 text-2xl">Your Answers</h1>
            </div>
            <AnswerForm />
          </div>
        </div>
      </MainContainer>
      <BufferMd5 />
      <Footer />
    </PageContainer>
  );
};

export default ShowQuestionPage;
