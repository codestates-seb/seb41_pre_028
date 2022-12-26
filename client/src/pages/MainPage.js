import { Route, Routes } from "react-router-dom";
import SideBar from "../components/sideBar/SideBar";
import QuestionsPage from "./QuestionsPage";
import UsersPage from "./UsersPage";
import UserPage from "./UserPage";
import QuestionDetail from "./QuestionDetail";
import styled from "styled-components";
import { media } from "../utils/style-utils";

export const PageContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
`;

export const MainContainer = styled.div`
  padding-top: var(--h-header);
  display: flex;
  max-width: 1264px;
  width: 100%;
  > .side-bar {
    width: 164px;

    ${media.mobile`
        display: none;
    `}
  }
  > .content {
    width: calc(100% - 164px);
    max-width: 1100px;
    ${media.mobile`
        width: 100%;
    `}
  }
`;

const MainPage = () => {
  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <Routes>
          <Route path="/questions" element={<QuestionsPage />}></Route>
          <Route path="/questions/:qId" element={<QuestionDetail />}></Route>
          <Route path="/users" element={<UsersPage />}></Route>
          <Route path="/users/:userId/*" element={<UserPage />}></Route>
          <Route path="/*" element={<QuestionsPage />}></Route>
        </Routes>
      </MainContainer>
    </PageContainer>
  );
};

export default MainPage;
