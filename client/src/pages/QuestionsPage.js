import { useState } from "react";
import styled from "styled-components";
import { media } from "../utils/style-utils";
import AskQuestionButton from "../components/AskQuestionButton";
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
    display: flex;
    flex-direction: column;
    width: 300px;
    ${media.tablet`
      width: 100%;
      flex-direction: row;
      flex-wrap: wrap;
    `};

    > div {
      width: 100%;
      height: min-content;
      ${media.tablet`
      width: 50%;
    `};
    }
  }
`;

const QuestionsPage = () => {
  const [questionList, setQuestionList] = useState([]);
  return (
    <ContentWrapper className="content">
      <main>
        <div className="flex flex-row items-center justify-between mb-[12px]">
          <h1 className="text-title-size">All Questions</h1>
          <AskQuestionButton />
        </div>
        <QuestionList
          questionList={questionList}
          setQuestionList={setQuestionList}
        ></QuestionList>
      </main>
      <aside>
        <div>
          <img
            className="block w-full h-[250px]"
            src="happynewyear.jpeg"
            alt={`new year 2023`}
          />
        </div>
        <div>
          <img
            className="block w-full h-[250px]"
            src="newyear.jpeg"
            alt={`new year 2023`}
          />
        </div>
      </aside>
    </ContentWrapper>
  );
};

export default QuestionsPage;
