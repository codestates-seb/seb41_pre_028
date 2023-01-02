import AnswerBody from "./AnswerBody";

function AnswerList({ answers }) {
  return (
    <div className="answers-group">
      {answers && answers.length > 0 ? (
        <h3 className="px-6 mb-5 pb-4 text-2xl border-b border-soGray-light">
          {answers.length} Answers
        </h3>
      ) : (
        <h3 className="px-6 mb-5 pb-4 text-2xl border-b border-soGray-light">
          0 Answers
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
