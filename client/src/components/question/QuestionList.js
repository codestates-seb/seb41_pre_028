import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { getQuestionList } from "../../utils/api/api";
import Question from "./Question";
import FilterBar from "../FilterBar";
import Pagination from "../pagination/Pagination";
import { questionFilterList as filterList } from "../../static/filterAndTabList";
import paramsToObject from "../../utils/paramsToObject";

const QuestionList = ({ questionList, setQuestionList }) => {
  const [totalQuestions, setTotalQuestions] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  // 실제 query
  const [searchParams] = useSearchParams();

  useEffect(() => {
    if (!searchParams.has("filter")) {
      searchParams.append("filter", filterList[0].filter);
    }
    if (!searchParams.has("page")) {
      searchParams.append("page", 1);
    }
    if (!searchParams.has("size")) {
      searchParams.append("size", 3);
    }

    getQuestionList(paramsToObject(searchParams.entries()))
      .then((res) => {
        setQuestionList(res.data.data);
        setTotalQuestions(res.data.pageInfo.totalElements);
        setTotalPages(res.data.pageInfo.totalPages);
      })
      .catch((err) => console.log(err));
  }, [searchParams]);

  return (
    <div className="sm:ml-[-24px]">
      <div className="sm:ml-[24px] mb-[12px] flex flex-row justify-between items-center">
        <div>{totalQuestions} questions</div>
        <div>
          <FilterBar filterList={filterList}></FilterBar>
        </div>
      </div>
      <div className="border-t border-[#e3e6e8]">
        {totalQuestions === 0 ? (
          <div className="flex items-center justify-center mt-[10px]">
            No Questions
          </div>
        ) : (
          questionList.map((el) => (
            <Question key={el.questionId} questionId={el.questionId}></Question>
          ))
        )}
      </div>
      <Pagination totalPages={totalPages} />
    </div>
  );
};

export default QuestionList;
