import { useState, useEffect } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { searchQuestionsByValue } from "../utils/api/api";
import paramsToObject from "../utils/paramsToObject";
import AskQuestionButton from "../components/AskQuestionButton";
import styled from "styled-components";
import { media } from "../utils/style-utils";
import Question from "../components/question/Question";
import Pagination from "../components/pagination/Pagination";

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

const SearchQuestionPage = () => {
  const [questionList, setQuestionList] = useState([]);
  const [totalQuestions, setTotalQuestions] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  useEffect(() => {
    if (!searchParams.has("value")) {
      navigate("/questions");
    }
    if (!searchParams.has("page")) {
      searchParams.append("page", 1);
    }
    if (!searchParams.has("size")) {
      searchParams.append("size", 10);
    }

    searchQuestionsByValue(paramsToObject(searchParams.entries()))
      .then((res) => {
        setQuestionList(res.data.data);
        setTotalQuestions(res.data.pageInfo.totalElements);
        setTotalPages(res.data.pageInfo.totalPages);
      })
      .catch((err) => console.log(err));
  }, [searchParams]);

  return (
    <ContentWrapper className="content">
      <main>
        <div className="flex flex-row items-center justify-between mb-[12px]">
          <h1 className="text-title-size">Search Result</h1>
          <AskQuestionButton />
        </div>
        <div className="sm:ml-[-24px]">
          <div className="sm:ml-[24px] mb-[12px] flex flex-row justify-between items-center">
            <div>{totalQuestions} questions</div>
          </div>
          <div className="border-t border-[#e3e6e8]">
            {totalQuestions === 0 ? (
              <div className="flex items-center justify-center mt-[10px]">
                No Questions
              </div>
            ) : (
              questionList.map((el) => (
                <Question
                  key={el.questionId}
                  questionId={el.questionId}
                ></Question>
              ))
            )}
          </div>
          <Pagination totalPages={totalPages} />
        </div>
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

export default SearchQuestionPage;
