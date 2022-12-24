import { useParams } from "react-router-dom";
import SideBar from "../components/sideBar/SideBar";
import { PageContainer, MainContainer } from "../components/StyledContainer";

const UserPage = () => {
  const { userId } = useParams();
  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <main className="content">userId가 {userId}인 사람의 페이지</main>
      </MainContainer>
    </PageContainer>
  );
};

export default UserPage;
