import TagsItem from "./TagsItem";
const TagsList = () => {
  const rendering = () => {
    let arr = [];
    for (let i = 0; i < 12; i++) {
      arr.push(<TagsItem key={i} />);
    }
    return arr;
  };
  return (
    <div className="grid gap-3 grid-cols-4 max-[1240px]:grid-cols-3 max-[980px]:grid-cols-2 max-[640px]:grid-cols-1">
      {rendering()}
    </div>
  );
};
export default TagsList;
