import SideBar from "../components/SideBar";
import MainContainer from "../components/MainContainer";

const QuestionsPage = () => {
  return (
    <MainContainer>
      <SideBar className="sideBar"></SideBar>
      <main className="content">Questions</main>
    </MainContainer>
  );
};

export default QuestionsPage;
