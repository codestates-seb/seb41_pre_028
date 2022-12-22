import SideBar from "../components/SideBar";
import MainContainer from "../components/MainContainer";

const UserPage = () => {
  return (
    <MainContainer>
      <SideBar className="sideBar"></SideBar>
      <main className="content">Users</main>
    </MainContainer>
  );
};

export default UserPage;
