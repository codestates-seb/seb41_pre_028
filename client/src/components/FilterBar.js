import styled from "styled-components";

const FilterNav = styled.nav`
  display: flex;
  align-items: center;

  > button {
    font-size: 12px;
    padding: 8px 9px;
    border: 1px solid #9fa6ad;
    color: #6a737c;
    margin-left: -1px;
  }

  .focus {
    border: 1px solid #838c95;
    background-color: #e3e6e8;
    color: #3b4045;
  }

  > button:nth-child(1) {
    border-radius: 3px 0px 0px 3px;
  }

  > button:nth-last-child(1) {
    border-radius: 0px 3px 3px 0px;
  }
`;

const FilterBar = ({
  filterList,
  curFilter,
  setCurFilter,
  setSearchParams,
}) => {
  return (
    <FilterNav>
      {filterList.map((el, idx) => (
        <button
          value={el.tab}
          className={idx === curFilter ? "focus" : ""}
          key={el.id}
          onClick={() => {
            setCurFilter(idx);
            setSearchParams({ tab: el.tab });
          }}
        >
          {el.tab}
        </button>
      ))}
    </FilterNav>
  );
};
export default FilterBar;
