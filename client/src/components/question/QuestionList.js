import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { getQuestionList } from "../../utils/api/api";
import Question from "./Question";
import FilterBar from "../FilterBar";
import Pagination from "../Pagination/Pagination";

import { questionFilterList as filterList } from "../../static/filterAndTabList";
const QuestionList = ({ questionList, setQuestionList }) => {
  const [totalQuestions, setTotalQuestions] = useState(0);
  // UI를 담당
  const [curFilter, setCurFilter] = useState(0);
  const [curPage, setCurPage] = useState(1);
  // 실제 query
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    let filter = filterList[curFilter].tab;
    // let params = filterList[curFilter].params;
    let page = curPage - 1;
    // 일단 size는 고정인 상태로 요청
    let size = 30;
    if (searchParams.get("tab")) {
      filter = searchParams.get("tab");
      setCurFilter(filterList.findIndex((el) => el.tab === filter));
    }

    if (searchParams.get("page")) {
      page = searchParams.get("page") - 1;
      setCurPage(searchParams.get("page"));
    }

    getQuestionList({ page, size })
      .then((res) => {
        setQuestionList(res.data.data);
        setTotalQuestions(res.data.pageInfo.totalElements);
      })
      .catch((err) => console.log(err));
  }, [searchParams]);

  // const questionsLength = useMemo(() => questionList.length, [questionList]);

  return (
    <div className="sm:ml-[-24px]">
      <div className="sm:ml-[24px] mb-[12px] flex flex-row justify-between items-center">
        <div>{totalQuestions} questions</div>
        <div>
          <FilterBar
            filterList={filterList}
            curFilter={curFilter}
            setCurFilter={setCurFilter}
            setSearchParams={setSearchParams}
          ></FilterBar>
        </div>
      </div>
      <div className="border-t border-[#e3e6e8]">
        {totalQuestions === 0 ? (
          <div className="flex items-center justify-center">
            질문이 비어있습니다.
          </div>
        ) : (
          questionList.map((el) => (
            <Question key={el.questionId} questionId={el.questionId}></Question>
          ))
        )}
      </div>
      <Pagination />
    </div>
  );
};

export default QuestionList;
