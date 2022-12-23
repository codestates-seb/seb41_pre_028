import { Link } from "react-router-dom";

const SideBar = () => {
  return (
    <nav>
      <Link to={"/"}>Home</Link>
      <ul className="flex flex-col">
        <Link to={"/questions"}>Questions</Link>
        <Link to={"/users"}>User</Link>
      </ul>
    </nav>
  );
};

export default SideBar;
