import { useEffect, useMemo, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { getQuestionList } from "../../utils/api/api";
import Question from "./Question";
import FilterBar from "../FilterBar";
import Pagination from "../Pagination/Pagination";

import { questionFilterList as filterList } from "../../static/filterAndTabList";
import Pagination from "../Pagination/Pagination";
const QuestionList = ({ questionList, setQuestionList }) => {
  // UI를 담당
  const [curFilter, setCurFilter] = useState(0);
  // 실제 query
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    // Fix
    // 현재
    let title = filterList[curFilter].tab;
    if (searchParams.get("tab")) {
      title = searchParams.get("tab");
      setCurFilter(filterList.findIndex((el) => el.tab === title));
    }

    getQuestionList({ title })
      .then((res) => setQuestionList(res.data))
      .catch((err) => console.log(err));
  }, [searchParams]);

  const questionsLength = useMemo(() => questionList.length, [questionList]);

  return (
    <div className="sm:ml-[-24px]">
      <div className="sm:ml-[24px] mb-[12px] flex flex-row justify-between items-center">
        <div>{questionsLength} questions</div>
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
        {questionList.map((el) => (
          <Question key={el.id} question={el}></Question>
        ))}
      </div>
      <Pagination />
    </div>
  );
};

export default QuestionList;
