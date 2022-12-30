import { useEffect, useMemo, useState } from "react";
import range from "../../utils/range";
import styled from "styled-components";
import { useSearchParams } from "react-router-dom";

const PageButton = styled.button`
  margin-right: 5px;
  padding: 7px 10px;
  line-height: 0.9;
  border-radius: 3px;
  border: 1px solid #e3e6e8;
  &.focus {
    border: 1px solid #f48224;
    background-color: #f48224;
    color: white;
    &:hover {
      background-color: #f48224;
    }
  }

  &:hover {
    background-color: #c8cccf;
  }
`;

const Pagination = ({ totalPages }) => {
  const [curPage, setCurPage] = useState(1);
  const pageArr = useMemo(() => {
    // p
    if (totalPages <= 5) {
      return range(2, totalPages);
    }
    if (curPage >= totalPages - 2) {
      return range(totalPages - 4, totalPages);
    } else if (curPage >= 5) {
      return range(curPage - 2, curPage + 3);
    } else {
      return range(2, 6);
    }
  }, [curPage, totalPages]);
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    if (searchParams.has("page")) {
      setCurPage(Number(searchParams.get("page")));
    } else {
      setCurPage(1);
    }
  }, [searchParams]);

  // const handleClick = (e) => {};

  return (
    <div className="m-[24px]">
      <PageButton
        onClick={() => {
          setCurPage(curPage - 1);
          searchParams.set("page", curPage - 1);
          setSearchParams(searchParams);
        }}
        className={curPage === 1 ? "hidden" : ""}
      >
        prev
      </PageButton>
      <PageButton
        className={1 === curPage ? "focus" : ""}
        onClick={() => {
          setCurPage(1);
          searchParams.set("page", 1);
          setSearchParams(searchParams);
        }}
      >
        1
      </PageButton>
      <PageButton
        className={`summary ${curPage < 5 || totalPages <= 5 ? "hidden" : ""}`}
      >
        ...
      </PageButton>
      {pageArr.map((el) => {
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
      <PageButton
        className={`summary ${
          curPage >= totalPages - 2 || totalPages <= 5 ? "hidden" : ""
        }`}
      >
        ...
      </PageButton>
      <PageButton
        className={`${curPage === totalPages ? "focus" : ""} ${
          totalPages <= 1 ? "hidden" : ""
        }`}
        onClick={() => {
          setCurPage(totalPages);
          searchParams.set("page", totalPages);
          setSearchParams(searchParams);
        }}
      >
        {totalPages}
      </PageButton>
      <PageButton
        onClick={() => {
          setCurPage(curPage + 1);
          searchParams.set("page", curPage + 1);
          setSearchParams(searchParams);
        }}
        className={curPage === totalPages || totalPages <= 1 ? "hidden" : ""}
      >
        next
      </PageButton>
    </div>
  );
};

export default Pagination;
