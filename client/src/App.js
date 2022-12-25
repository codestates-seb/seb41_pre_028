import { Route, Routes } from "react-router-dom";
import Header from "./components/header/Header";
import SignUpPage from "./pages/SignUpPage";
import LoginPage from "./pages/LoginPage";
import MainPage from "./pages/MainPage";
import QuestionsPage from "./pages/QuestionsPage";
import UserPage from "./pages/UserPage";
import CreateQuestionPage from "./pages/CreateQuestionPage";
import ShowQuestionPage from "./pages/ShowQuestionPage";

function App() {
  return (
    <div className="App">
      <Header />
      <div className="w-screen flex justify-center">
        <Routes>
          <Route path="/" element={<MainPage />}></Route>
          <Route path="/signup" element={<SignUpPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/createQuestion" element={<CreateQuestionPage />} />
          <Route path="/showQuestion" element={<ShowQuestionPage />} />
          <Route path="/questions" element={<QuestionsPage />}></Route>
          <Route path="/users" element={<UserPage />}></Route>
          <Route path="/users/:id" element={<UserPage />}></Route>
          <Route path="/*" element={<MainPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
