import { Route, Routes } from "react-router-dom";
import styled from "styled-components";
import { media } from "../utils/style-utils";
import SideBar from "../components/sideBar/SideBar";
import QuestionsPage from "./QuestionsPage";
import UsersPage from "./UsersPage";
import UserPage from "./UserPage";
import ShowQuestionPage from "./ShowQuestionPage";
import SearchQuestionPage from "./SearchQuestionPage";
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
          <Route
            path="/questions/:questionId"
            element={<ShowQuestionPage />}
          ></Route>
          <Route path="/users" element={<UsersPage />}></Route>
          <Route path="/users/:userId/*" element={<UserPage />}></Route>
          <Route path="/search" element={<SearchQuestionPage />}></Route>
          <Route path="/" element={<QuestionsPage />}></Route>
        </Routes>
      </MainContainer>
    </PageContainer>
  );
};

export default MainPage;
