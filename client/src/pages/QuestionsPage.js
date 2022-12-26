import styled from "styled-components";
import { media } from "../utils/style-utils";
import { PrimaryLink } from "../components/StyledLink";
import QuestionList from "../components/question/QuestionList";

const ContentWrapper = styled.div`
  display: flex;
  padding: 24px;

  ${media.tablet`
      flex-direction: column;
  `}

  > main {
    width: calc(100% - 300px - 24px);
    margin-right: 24px;
    ${media.tablet`
      width: 100%;
    `}
  }

  > aside {
    background-color: yellow;
    width: 300px;
    ${media.tablet`
      width: 100%;
    `};
  }
`;

const QuestionsPage = () => {
  return (
    <ContentWrapper className="content">
      <main>
        <div className="flex flex-row items-center justify-between mb-[12px]">
          <h1 className="text-title-size">All Questions</h1>
          <PrimaryLink to={"/createQuestion"}>Ask Question</PrimaryLink>
        </div>
        <QuestionList></QuestionList>
      </main>
      <aside>광고광고</aside>
    </ContentWrapper>
  );
};

export default QuestionsPage;
