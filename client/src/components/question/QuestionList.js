import { useEffect, useMemo, useState } from "react";
import { getQuestionList } from "../../utils/api/question";
import useQuery from "../../hooks/useQuery";
import Question from "./Question";
import FilterBar from "../FilterBar";

const QuestionList = () => {
  const [questionList, setQuestionList] = useState([]);
  const [curFilter, setCurFilter] = useState(1);
  const query = useQuery(); // URLSearchParams 객체 불러옴

  const filterList = [
    {
      id: 1,
      name: "Newest",
    },
    {
      id: 2,
      name: "Latest",
    },
    {
      id: 3,
      name: "Test",
    },
  ];

  useEffect(() => {
    console.log(query.get("tab"));

    getQuestionList()
      .then((res) => setQuestionList(res.data))
      .catch((err) => console.log(err));
  }, [curFilter]);

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
          ></FilterBar>
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
