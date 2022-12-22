import { Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import SideBar from "./components/SideBar";
import MainPage from "./pages/MainPage";
import QuestionsPage from "./pages/QuestionsPage";
import UserPage from "./pages/UserPage";
import styled from "styled-components";

const Container = styled.div`
  padding-top: var(--h-header);
  display: flex;
  max-width: 1264px;
  width: 100%;
  justify-content: space-between;

  > .side-bar {
    width: 164px;
  }
  > .content {
    width: calc(100% - 164px);
    max-width: 1100px;
    background-color: red;
  }
`;

function App() {
  return (
    <div className="App flex justify-center">
      <Header />
      <Container>
        <div className="side-bar">
          <SideBar />
        </div>
        <div className="content">
          <Routes>
            <Route path="/" element={<MainPage />}></Route>
            <Route path="/questions" element={<QuestionsPage />}></Route>
            <Route path="/users" element={<UserPage />}></Route>
            <Route path="/users/:id" element={<UserPage />}></Route>
            <Route path="/*" element={<MainPage />}></Route>
          </Routes>
        </div>
      </Container>
    </div>
  );
}

export default App;
