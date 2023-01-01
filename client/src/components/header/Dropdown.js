const Dropdown = ({ visibilty, children }) => {
  return (
    <article className="fixed mt-[212px] ml-48  bg-white w-60 drop-shadow-lg rounded-b-lg  ">
      {visibilty && children}
    </article>
  );
};
export default Dropdown;
