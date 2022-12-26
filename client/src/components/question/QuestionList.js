import { useEffect, useMemo, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { getQuestionList } from "../../utils/api/question";
import Question from "./Question";
import FilterBar from "../FilterBar";
import Pagination from "../pagination/Pagination";

const QuestionList = () => {
  const [questionList, setQuestionList] = useState([]);
  // UI를 담당
  const [curFilter, setCurFilter] = useState("Newest");
  // 실제 query
  const [searchParams, setSearchParams] = useSearchParams();

  const filterList = ["Newest", "Latest", "Test"];

  // useEffect(() => {
  //   getQuestionList()
  //     .then((res) => setQuestionList(res.data))
  //     .catch((err) => console.log(err));
  // }, []);

  useEffect(() => {
    let title = curFilter;

    if (searchParams.get("tab")) {
      title = searchParams.get("tab");
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
