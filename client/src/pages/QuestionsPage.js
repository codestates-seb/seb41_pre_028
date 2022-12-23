import SideBar from "../components/sideBar/SideBar";
import QuestionList from "../components/question/QuestionList";
import { PageContainer, MainContainer } from "../components/StyledContainer";

const QuestionsPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <main className="content">
          <QuestionList></QuestionList>
        </main>
      </MainContainer>
    </PageContainer>
  );
};

export default QuestionsPage;
