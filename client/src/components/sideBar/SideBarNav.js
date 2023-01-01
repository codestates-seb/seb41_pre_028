import { Link, useLocation } from "react-router-dom";
const SideBarNav = () => {
  const location = useLocation();

  return (
    <nav className="mt-5 text-sm">
      <Link to={"/"} className="flex p-2 ">
        Home
      </Link>
      <ul className="flex flex-col ">
        <li>
          <ul>
            <li className="mb-3 mt-3">PUBLIC</li>

            <li>
              <Link
                to={"/questions"}
                className={
                  location.pathname === "/questions"
                    ? "flex bg-stone-200 font-bold border-r-amber-500	 border-r-4 py-1"
                    : "flex py-1"
                }
              >
                <svg width="18px" height="18px" className="mr-1">
                  <path d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8ZM8 15.32a6.46 6.46 0 0 1-4.3-2.74 6.46 6.46 0 0 1-.93-5.01L7 11.68v.8c0 .88.12 1.32 1 1.32v1.52Zm5.72-2c-.2-.66-1-1.32-1.72-1.32h-1v-2c0-.44-.56-1-1-1H6V7h1c.44 0 1-.56 1-1V5h2c.88 0 1.4-.72 1.4-1.6v-.33a6.45 6.45 0 0 1 3.83 4.51 6.45 6.45 0 0 1-1.51 5.73v.01Z"></path>
                </svg>
                <span>Question</span>
              </Link>
            </li>

            <li>
              <Link
                to={"/tags"}
                className={
                  location.pathname === "/tags"
                    ? "flex bg-stone-200 font-bold border-r-orange-400 border-r-4 py-1"
                    : "flex py-1"
                }
              >
                <div className="ml-5">Tags</div>
              </Link>
            </li>

            <li>
              <Link
                to={"/users"}
                className={
                  location.pathname === "/users"
                    ? "flex bg-stone-200 font-bold border-r-orange-400 border-r-4 py-1"
                    : "flex py-1"
                }
              >
                <div className="ml-5">Users</div>
              </Link>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
  );
};

export default SideBarNav;
