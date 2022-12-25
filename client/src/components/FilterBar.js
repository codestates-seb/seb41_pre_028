import styled from "styled-components";
import { Link } from "react-router-dom";

const FilterNav = styled.nav`
  display: flex;
  align-items: center;

  > a {
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

  > a:nth-child(1) {
    border-radius: 3px 0px 0px 3px;
  }

  > a:nth-last-child(1) {
    border-radius: 0px 3px 3px 0px;
  }
`;

const FilterBar = ({ filterList, curFilter, setCurFilter }) => {
  return (
    <FilterNav>
      {filterList.map((el) => (
        <Link
          to={`?tab=${el.name}`}
          value={el.name}
          className={el.id === curFilter ? "focus" : ""}
          key={el.id}
          onClick={() => setCurFilter(el.id)}
        >
          {el.name}
        </Link>
      ))}
    </FilterNav>
  );
};

export default FilterBar;
