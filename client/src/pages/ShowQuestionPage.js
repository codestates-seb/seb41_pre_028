import { MainContainer } from "../components/StyledContainer";
import SideBar from "../components/sideBar/SideBar";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";

const ShowQuestionPage = () => {
  const navigate = useNavigate();

  const onClickAskQuestion = () => {
    navigate("createQuestion");
  };
  return (
    <MainContainer>
      <SideBar></SideBar>
      <div className="m-5 p-2 flex flex-row bg-[#f1f2f3] w-screen">
        <div>
          <div className="flex justify-between mb-4 question-title">
            <h2 className="row-auto pr-2 font-medium leading-tight break-words text-xxl">
              title
            </h2>
            <div className="flex justify-end basis-52">
              <Button
                variant="contained"
                sx={{ fontSize: 12 }}
                size="large"
                onClick={onClickAskQuestion}
              >
                Ask Question
              </Button>{" "}
            </div>
          </div>{" "}
        </div>
      </div>
    </MainContainer>
  );
};

export default ShowQuestionPage;
