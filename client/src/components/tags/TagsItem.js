import { Link } from "react-router-dom";

const TagsItem = () => {
  return (
    <div className="flex flex-col p-2 border-2 rounded">
      <div className="flex">
        <Link to={`/search?value=[javascript]`}>
          <div className="bg-[#e1ecf4] text-[#467ca2] text-xs rounded p-1 mb-2">
            JavaScript
          </div>
        </Link>
      </div>
      <div className="flex  text-sm mb-2 break-normal">
        For questions about programming in ECMAScript (JavaScript/JS) and its
        different dialects/implementations (except for ActionScript). Keep in...
      </div>
      <div className="flex justify-between p-1 text-[#868f98]">
        <div className="flex flex-col text-xs  max-[640px]:flex-row">
          <div>2462314</div>
          <div>questions</div>
        </div>

        <div>
          <div className="flex flex-col text-xs max-[640px]:flex-row">
            <span>495 asked today,</span>
            <span>3111 this week</span>
          </div>
        </div>
      </div>
    </div>
  );
};
export default TagsItem;
