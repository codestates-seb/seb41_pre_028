import { useParams } from "react-router-dom";

const UserPage = () => {
  const { userId } = useParams();
  return <main className="content">userId가 {userId}인 사람의 페이지</main>;
};

export default UserPage;
