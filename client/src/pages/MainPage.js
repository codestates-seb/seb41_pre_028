import { PageContainer, MainContainer } from "../components/StyledContainer";
import SideBar from "../components/sideBar/SideBar";

const MainPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <main className="content">Top Questions</main>
      </MainContainer>
    </PageContainer>
  );
};

export default MainPage;
