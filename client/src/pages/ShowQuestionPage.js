import styled from "styled-components";
import { media } from "../utils/style-utils";
import SideBar from "../components/sideBar/SideBar";
import QuestionHeader from "../components/showQuestion/questionsection/QuestionHeader";
import QuestionBody from "../components/showQuestion/questionsection/QuestionBody";
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

const ShowQuestionPage = () => {
  const question = {
    id: 2,
    title: "원심분리기",
    askedAt: "8 years, 4 months ago",
    modifiedAt: "Today",
    viewed: "23k",
    voted: 12,
    content:
      "I am using Paper-Button but I am facing issue that the button text always gets capitalized instead or normal case. I do not see any CSS or Javascript property being applied to make it upper cas How should I resolve this problem?",
    author: {
      id: 2,
      name: "bakjonghyo",
      avatar_img: "../../images/stackoverflow.png",
    },
  };

  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <div className="content">
          <QuestionHeader question={question}></QuestionHeader>
          <QuestionBody question={question}></QuestionBody>
        </div>
      </MainContainer>
    </PageContainer>
  );
};

export default ShowQuestionPage;
