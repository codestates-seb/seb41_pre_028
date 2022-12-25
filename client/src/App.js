import { Route, Routes } from "react-router-dom";
import Header from "./components/header/Header";
import SignUpPage from "./pages/SignUpPage";
import LoginPage from "./pages/LoginPage";
import CreateQuestionPage from "./pages/CreateQuestionPage";
import MainPage from "./pages/MainPage";

function App() {
  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/createQuestion" element={<CreateQuestionPage />} />
        <Route path="*" element={<MainPage />} />
      </Routes>
    </div>
  );
}

export default App;
