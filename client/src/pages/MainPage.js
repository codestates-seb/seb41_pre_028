import { PageContainer, MainContainer } from "../components/StyledContainer";
import SideBar from "../components/SideBar";

const MainPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar className="side-bar"></SideBar>
        <main className="content">Top Questions</main>
      </MainContainer>
    </PageContainer>
  );
};

export default MainPage;
