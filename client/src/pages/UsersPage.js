import { PageContainer, MainContainer } from "../components/StyledContainer";
import SideBar from "../components/sideBar/SideBar";
const UsersPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <main className="content">Users 페이지</main>
      </MainContainer>
    </PageContainer>
  );
};

export default UsersPage;
