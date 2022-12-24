import { useEffect, useMemo, useState } from "react";
import { getQuestionList } from "../../utils/api/question";
import Question from "./Question";
import FilterBar from "../FilterBar";

const QuestionList = () => {
  const [questionList, setQuestionList] = useState([]);
  const [curFilter, setCurFilter] = useState(1);

  const filterList = [
    {
      id: 1,
      name: "newest",
    },
    {
      id: 2,
      name: "latest",
    },
    {
      id: 3,
      name: "test",
    },
  ];

  useEffect(() => {
    console.log(curFilter);
    getQuestionList()
      .then((res) => setQuestionList(res.data))
      .catch((err) => console.log(err));
  }, [curFilter]);

  const questionsLength = useMemo(() => questionList.length, [questionList]);

  return (
    <div className="sm:ml-[-24px]">
      <div className="sm:ml-[24px] flex flex-row justify-between">
        <div>{questionsLength} questions</div>
        <div>
          <div>
            <FilterBar
              filterList={filterList}
              curFilter={curFilter}
              setCurFilter={setCurFilter}
            ></FilterBar>
          </div>
        </div>
      </div>
      <div className="border-t border-[#e3e6e8]">
        {questionList.map((el) => (
          <Question key={el.id} question={el}></Question>
        ))}
      </div>
    </div>
  );
};

export default QuestionList;
