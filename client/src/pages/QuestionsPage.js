import SideBar from "../components/SideBar";
import MainContainer from "../components/MainContainer";
import QuestionList from "../components/question/QuestionList";

const QuestionsPage = () => {
  return (
    <MainContainer>
      <SideBar className="sideBar"></SideBar>
      <main className="content">
        <QuestionList></QuestionList>
      </main>
    </MainContainer>
  );
};

export default QuestionsPage;
