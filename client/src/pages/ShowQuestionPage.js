import styled from "styled-components";
import { media } from "../utils/style-utils";
import SideBar from "../components/sideBar/SideBar";
import QuestionHeader from "../components/showQuestion/questionsection/QuestionHeader";
import QuestionBody from "../components/showQuestion/questionsection/QuestionBody";
import AnswerBody from "../components/showQuestion/answerSection/AnswerBody";
import AnswerForm from "../components/showQuestion/answerSection/AnswerForm";
import Footer from "../components/footer/footer";
import { BufferMd5 } from "../components/buffer/Buffer.jsx";

export const PageContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
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
    title: "원심분리기12",
    askedAt: "8 years, 4 months ago",
    modifiedAt: "Today",
    viewed: "23k",
    voted: 12,
    content:
      "I am using Paper-Button but I am facing issue that the button text always gets capitalized instead or normal case. I do not see any CSS or Javascript property being applied to make it upper cas How should I resolve this problem?",
    author: {
      id: 1,
      name: "bakjonghyo",
      avatar_img: "../images/identicon1.jpeg",
    },
    answer: {
      id: 1,
      askedAt: "8 years, 4 months ago",
      modifiedAt: "Today",
      viewed: "23k",
      voted: 12,
      content:
        "InspiredInspiredInspired by the the CSS style above here is the inline styling for localized Button text transformation Inspired by the the CSS style above here is the inline styling for localized Button text transformation - As was mentioned in the comments above, the material design spec for buttons specifies that the text should be uppercase, but you can easily override its CSS propertyInspiredInspiredInspired by the the CSS style above here is the inline styling for localized Button text transformation Inspired by the the CSS style above here is the inline styling for localized Button text transformation - As was mentioned in the comments above, the material design spec for buttons specifies that the text should be uppercase, but you can easily override its CSS property",
      author: {
        id: 2,
        name: "bakjonghyo",
        avatar_img: "../images/identicon1.jpeg",
      },
    },
    comment: {
      id: 1,
      askedAt: "8 years, 4 months ago",
      modifiedAt: "Today",
      viewed: "23k",
      voted: 12,
      content:
        "InspiredInspiredInspired by the the CSS style above here is the inline styling for localized Button text transformation Inspired by the the CSS style above here is the inline styling for localized Button text transformation - As was mentioned in the comments above, the material design spec for buttons specifies that the text should be uppercase, but you can easily override its CSS propertyInspiredInspiredInspired by the the CSS style above here is the inline styling for localized Button text transformation Inspired by the the CSS style above here is the inline styling for localized Button text transformation - As was mentioned in the comments above, the material design spec for buttons specifies that the text should be uppercase, but you can easily override its CSS property",
      author: {
        id: 2,
        name: "bakjonghyo",
        avatar_img: "../images/identicon1.jpeg",
      },
    },
  };

  return (
    <PageContainer>
      <MainContainer>
        <SideBar></SideBar>
        <div className="flex flex-col">
          <div className="question">
            <QuestionHeader question={question}></QuestionHeader>
            <QuestionBody question={question}></QuestionBody>
          </div>
          <div className="answer">
            <div>
              <h1 className="m-4 text-2xl">4 Answers</h1>
            </div>
            <AnswerBody question={question.answer} />
            <div>
              <h1 className="m-4 text-2xl">Your Answers</h1>
            </div>
            <AnswerForm></AnswerForm>
          </div>
        </div>
      </MainContainer>
      <BufferMd5 />
      <Footer />
    </PageContainer>
  );
};

export default ShowQuestionPage;
