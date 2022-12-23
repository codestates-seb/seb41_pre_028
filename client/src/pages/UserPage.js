import SideBar from "../components/sideBar/SideBar";
import { PageContainer, MainContainer } from "../components/StyledContainer";

const UserPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <main className="content">Top Questions</main>
      </MainContainer>
    </PageContainer>
  );
};

export default UserPage;
