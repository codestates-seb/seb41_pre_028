import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBookmark } from "@fortawesome/free-solid-svg-icons";

const VoteCell = ({ item }) => {
  const [isClickQUpVote, setIsClickQUpVote] = useState(false);
  const [isClickQDownVote, setIsClickQDownVote] = useState(false);
  const [VotedScore, setVotedScore] = useState(item);
  const [isClickQBookmark, setIsClickBookmark] = useState(true);

  const onClickQUpVote = () => {
    if (!isClickQUpVote && !isClickQDownVote) {
      setIsClickQUpVote(true);
      setVotedScore(VotedScore + 1);
    } else if (isClickQDownVote) {
      setIsClickQDownVote(false);
      setVotedScore(VotedScore + 1);
    } else {
      setIsClickQUpVote(false);
      setVotedScore(VotedScore - 1);
    }
  };
  const onClickQDownVote = () => {
    if (!isClickQDownVote && !isClickQUpVote) {
      setIsClickQDownVote(true);
      setVotedScore(VotedScore - 1);
    } else if (isClickQUpVote) {
      setIsClickQUpVote(false);
      setVotedScore(VotedScore - 1);
    } else {
      setIsClickQDownVote(false);
      setVotedScore(VotedScore + 1);
    }
  };

  const onClickQBookmark = () => {
    if (!isClickQBookmark) {
      setIsClickBookmark(true);
    } else {
      setIsClickBookmark(false);
    }
  };

  return (
    <div className="flex flex-col mr-8">
      <button
        className="flex justify-center"
        aria-label="Up vote"
        data-status="UP"
        onClick={() => onClickQUpVote()}
      >
        <svg
          aria-hidden="true"
          className="svg-icon iconArrowUpLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
          fill={isClickQUpVote ? "#f48225" : "#BABFC3"}
        >
          <path d="M2 25h32L18 9 2 25Z"></path>
        </svg>
      </button>
      <div className="flex justify-center"> {VotedScore}</div>
      <button
        className="flex justify-center"
        aria-label="Down vote"
        data-status="DOWN"
        onClick={() => onClickQDownVote()}
      >
        <svg
          aria-hidden="true"
          className="svg-icon iconArrowDownLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
          fill={isClickQDownVote ? "#f48225" : "#BABFC3"}
        >
          <path d="M2 11h32L18 27 2 11Z"></path>
        </svg>
      </button>
      {/* 폰트어썸 임포트에 문제가 있음 */}
      <button onClick={() => onClickQBookmark()}>
        {!isClickQBookmark ? (
          <FontAwesomeIcon icon={faBookmark} fill />
        ) : (
          <FontAwesomeIcon icon={faBookmark} border />
        )}
      </button>
    </div>
  );
};

export default VoteCell;
