import { useSearchParams } from "react-router-dom";
const Pagination = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  return (
    <div>
      <button
        onClick={() => {
          searchParams.set("page", 1);
          setSearchParams(searchParams);
        }}
      >
        hihihihih
      </button>
    </div>
  );
};

export default Pagination;
