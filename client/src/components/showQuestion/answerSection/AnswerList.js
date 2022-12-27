import AnswerBody from "./AnswerBody";

function AnswerList({ answerList }) {
  return (
    <div className="answers-group">
      <h3 className="px-6 mb-5 pb-4 text-2xl border-b border-soGray-light">{`${answerList.length} Answers`}</h3>
      {answerList.map((anItem) => (
        <AnswerBody key={anItem.answerid} item={anItem} />
      ))}
    </div>
  );
}

export default AnswerList;
