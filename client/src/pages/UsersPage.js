import UserItem from "../components/users/UsersItem";
import FilterBar from "../components/FilterBar";

const UsersPage = () => {
  const filterList = [
    {
      id: 1,
      filter: "reputation",
      title: "Reputation",
    },
    {
      id: 2,
      filter: "newusers",
      title: "New users",
    },
    {
      id: 3,
      filter: "voters",
      title: "Voters",
    },
    {
      id: 4,
      filter: "editors",
      title: "Editors",
    },
    {
      id: 5,
      filter: "moderators",
      title: "Moderators",
    },
  ];

  return (
    <main className="content p-6">
      <h1 className="font-normal text-3xl  mb-4">Users</h1>

      <div className="flex justify-between mb-8 max-[800px]:flex-col">
        <input className="border-[#babfc3] rounded border-2 max-[800px]:w-72 max-[800px]:mb-3" />
        <FilterBar key={filterList.id} filterList={filterList} />
      </div>

      <UserItem />
    </main>
  );
};

export default UsersPage;
