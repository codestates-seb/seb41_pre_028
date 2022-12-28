import UserItem from "../components/users/UsersItem";
import FilterBar from "../components/FilterBar";
// import { useParams } from "react-router-dom";

const UsersPage = () => {
  // const { id } = useParams();
  // console.log(id);

  const filterList = [
    {
      id: 1,
      name: "Reputation",
    },
    {
      id: 2,
      name: "New users",
    },
    {
      id: 3,
      name: "Voters",
    },
    {
      id: 4,
      name: "Editors",
    },
    {
      id: 5,
      name: "Moderators",
    },
  ];
  return (
    <main className="content p-6">
      <h1 className="font-normal text-3xl  mb-4">Users</h1>

      <div className="flex justify-between mb-8 max-[800px]:flex-col">
        <input className="border-[#babfc3] rounded border-2 max-[800px]:w-72 max-[800px]:mb-3" />
        <FilterBar filterList={filterList} />
      </div>

      <UserItem />
    </main>
  );
};

export default UsersPage;
