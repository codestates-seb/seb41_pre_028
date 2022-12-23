import SideBar from "../components/SideBar";
import { PageContainer, MainContainer } from "../components/StyledContainer";

const UserPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar className="side-bar"></SideBar>
        <main className="content">Top Questions</main>
      </MainContainer>
    </PageContainer>
  );
};

export default UserPage;
