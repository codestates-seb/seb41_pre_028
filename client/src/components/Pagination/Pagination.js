import { useEffect, useMemo, useState } from "react";
import range from "../../utils/range";
import styled from "styled-components";
import { useSearchParams } from "react-router-dom";

const PageBar = styled.div`
  .focus {
    background-color: red;
  }
`;
const PageButton = styled.button`
  background-color: aliceblue;
  margin-right: 10px;
  .focus {
    background-color: red;
    color: red;
  }
`;

const Pagination = ({ totalPages }) => {
  const totalPageArr = useMemo(() => range(1, totalPages + 1), [totalPages]);
  const [curPage, setCurPage] = useState(1);
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    if (searchParams.has("page")) {
      setCurPage(Number(searchParams.get("page")));
    }
  }, [searchParams]);

  return (
    <PageBar>
      <button></button>
      {totalPageArr.map((el) => {
        return (
          <PageButton
            key={el}
            value={el}
            className={el === curPage ? "focus" : ""}
            onClick={() => {
              setCurPage(el);
              searchParams.set("page", el);
              setSearchParams(searchParams);
            }}
          >
            {el}
          </PageButton>
        );
      })}
    </PageBar>
  );
};

export default Pagination;
