import AnswerBody from "./AnswerBody";

function AnswerList({ answers }) {
  // const [answers, setAnswers] = useState([]);
  // const getData = async () => {
  //   await fetch(`/answers/${params}`)
  //     .then((response) => response.json())
  //     .then((data) => setAnswers(data))
  //     .catch((error) => console.log(error));
  // };

  // useEffect(() => {
  //   getData();
  // }, []);

  // const getAnswerList = (params) => axios.get("/answers", { params });

  return (
    <div className="answers-group">
      {answers && answers.length > 0 ? (
        <h3 className="px-6 mb-5 pb-4 text-2xl border-b border-soGray-light">
          {answers.length} Answer
        </h3>
      ) : (
        <h3 className="px-6 mb-5 pb-4 text-2xl border-b border-soGray-light">
          0 Answer
        </h3>
      )}

      {answers && answers.length > 0
        ? answers.map((anItem) => (
            <AnswerBody key={anItem.answerId} item={anItem} />
            // 고유키 AnswerID 가 필요함
          ))
        : ""}
    </div>
  );
}

export default AnswerList;
