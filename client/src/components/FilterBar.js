import styled from "styled-components";

const FilterNav = styled.nav`
  display: flex;
  align-items: center;

  > button {
    padding: 10px;
    background-color: gray;
  }

  .focus {
    background-color: red;
  }

  > button:nth-child(1) {
    border-radius: 3px 0px 0px 3px;
  }

  > button:nth-last-child(1) {
    border-radius: 0px 3px 3px 0px;
  }
`;

const FilterBar = ({ filterList, curFilter, setCurFilter }) => {
  return (
    <FilterNav>
      {filterList.map((el) => (
        <button
          className={el.id === curFilter ? "focus" : ""}
          key={el.id}
          onClick={() => setCurFilter(el.id)}
        >
          {el.name}
        </button>
      ))}
    </FilterNav>
  );
};

export default FilterBar;

{
  /* <div className="flex items-center justify-between border-gray-200 bg-white px-4 py-3 sm:px-6">
      <div className="hidden sm:flex sm:flex-1 sm:items-center sm:justify-between">
        <div>
          <nav
            className="isolate inline-flex -space-x-px rounded-md shadow-sm"
            aria-label="Pagination"
          >
            <a
              href="/createQuestion"
              className="relative inline-flex items-center rounded-l-md border border-gray-300 bg-white px-2 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50 focus:z-20"
            >
              <span className="sr-only">Previous</span>
              Prev{" "}
            </a>
            <a
              href="/createQuestion"
              aria-current="page"
              className="relative z-10 inline-flex items-center border border-orange-500 px-4 py-2 text-sm font-medium text-white focus:z-20 bg-orange-400"
            >
              1
            </a>
            <a
              href="/createQuestion"
              className="relative inline-flex items-center border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50 focus:z-20"
            >
              2
            </a>
            <a
              href="/createQuestion"
              className="relative hidden items-center border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50 focus:z-20 md:inline-flex"
            >
              3
            </a>
            <span className="relative inline-flex items-center border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700">
              ...
            </span>
            <a
              href="/createQuestion"
              className="relative hidden items-center border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50 focus:z-20 md:inline-flex"
            >
              8
            </a>
            <a
              href="createQuestion"
              className="relative inline-flex items-center border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50 focus:z-20"
            >
              9
            </a>
            <a
              href="/createQuestion"
              className="relative inline-flex items-center border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50 focus:z-20"
            >
              10
            </a>
            <a
              href="/createQuestion"
              className="relative inline-flex items-center rounded-r-md border border-gray-300 bg-white px-2 py-2 text-sm font-medium text-gray-500 hover:bg-gray-50 focus:z-20"
            >
              <span className="sr-only">Next</span>
              Next{" "}
            </a>
          </nav>
        </div>
      </div>
    </div> */
}
