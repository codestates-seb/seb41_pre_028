import { Link } from "react-router-dom";

const SideBarNav = () => {
  return (
    <nav>
      <Link to={"/"}>Home</Link>
      <ul className="flex flex-col">
        <a href="/questions">Questions</a>
        <Link to={"/users"}>User</Link>
      </ul>
    </nav>
  );
};

export default SideBarNav;
