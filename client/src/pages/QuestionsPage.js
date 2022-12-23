import SideBar from "../components/SideBar";
import { PageContainer, MainContainer } from "../components/StyledContainer";

const QuestionsPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <main className="content">Top Questions</main>
      </MainContainer>
    </PageContainer>
  );
};

export default QuestionsPage;
