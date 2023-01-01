import TagsList from "../components/tags/TagsList";
const TagsPage = () => {
  return (
    <main className="p-5 content">
      <h1 className="text-2xl font-medium mb-5">Tags</h1>
      <p className="flex mb-5">
        A tag is a keyword or label that categorizes your question with other,
        similar questions.
        <br />
        Using the right tags makes it easier for others to find and answer your
        question.
      </p>
      <TagsList />
    </main>
  );
};
export default TagsPage;
