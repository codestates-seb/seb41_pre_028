import { Route, Routes } from "react-router-dom";
import Header from "./components/header/Header";
import SignUpPage from "./pages/SignUpPage";
import LoginPage from "./pages/LoginPage";
import CreateQuestion from "./pages/CreateQuestion";
import MainPage from "./pages/MainPage";
import QuestionsPage from "./pages/QuestionsPage";
import UserPage from "./pages/UserPage";

function App() {
  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/" element={<MainPage />}></Route>
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/createQuestion" element={<CreateQuestion />} />
        <Route path="/questions" element={<QuestionsPage />}></Route>
        <Route path="/questions/:id" element={<QuestionsPage />}></Route>
        <Route path="/questions/:word" element={<QuestionsPage />}></Route>
        <Route path="/users" element={<UserPage />}></Route>
        <Route path="/users/:userId" element={<UserPage />}></Route>
        <Route path="/*" element={<MainPage />} />
      </Routes>
    </div>
  );
}

export default App;
