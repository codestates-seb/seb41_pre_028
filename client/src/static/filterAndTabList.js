import UserActivity from "../components/user/UserActivity";
import UserProfile from "../components/user/UserProfile";

export const questionFilterList = [
  {
    id: 1,
    filter: "newest",
    title: "Newest",
  },
  {
    id: 2,
    filter: "unanswerd",
    title: "Unanswerd",
  },
];

export const userPageTabList = [
  {
    id: 1,
    title: "Profile",
    showContent: ({ user, isMyPage }) => (
      <UserProfile user={user} isMyPage={isMyPage}></UserProfile>
    ),
  },
  {
    id: 2,
    title: "Activity",
    showContent: ({ user }) => <UserActivity user={user}></UserActivity>,
  },
];

export const userActivityTabList = [
  // get data에는 정보 요청하는 함수가 들어가야 한다.
  {
    id: 1,
    getData: (user) => {
      return user.answers.map((el) => {
        return {
          id: el.answerId,
          questionId: el.questionId,
          title: el.content,
        };
      });
    },
    title: "Answers",
    zeroContent: "There are no answers left by",
  },
  {
    id: 2,
    getData: (user) => {
      return user.questions.map((el) => {
        return {
          id: el.questionId,
          questionId: el.questionId,
          title: el.title,
        };
      });
    },
    title: "Questions",
    zeroContent: "There are no answers left by",
  },
];
