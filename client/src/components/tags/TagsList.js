import TagsItem from "./TagsItem";
const TagsList = () => {
  return (
    <div className="grid gap-5 grid-cols-4 max-[1240px]:grid-cols-3 max-[980px]:grid-cols-2 max-[640px]:grid-cols-1">
      <TagsItem />
      <TagsItem />
      <TagsItem />
      <TagsItem />
    </div>
  );
};
export default TagsList;
