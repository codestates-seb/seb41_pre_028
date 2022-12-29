import UserActivity from "../components/user/UserActivity";
import UserProfile from "../components/user/UserProfile";

export const questionFilterList = [
  {
    id: 1,
    tab: "Newest",
    params: {
      sorted_by: "",
    },
  },
  {
    id: 2,
    tab: "Unanswerd",
    params: {
      sorted_by: "",
    },
  },
];

export const userPageTabList = [
  {
    id: 1,
    title: "profile",
    showContent: (id) => <UserProfile userId={id}></UserProfile>,
  },
  {
    id: 2,
    title: "activity",
    showContent: (id) => <UserActivity userId={id}></UserActivity>,
  },
];

export const userActivityTabList = [
  // get data에는 정보 요청하는 함수가 들어가야 한다.
  {
    id: 1,
    getData: "answerList 가져올거임!",
    title: "Answers",
  },
  {
    id: 2,
    getData: "questionList 가져올거임!",
    title: "Questions",
  },
];
