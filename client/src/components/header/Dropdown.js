const Dropdown = ({ visibilty, children }) => {
  return (
    <article className="fixed mt-[221px] ml-28  bg-white w-40">
      {visibilty && children}
    </article>
  );
};
export default Dropdown;
