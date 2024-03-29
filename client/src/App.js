import { Route, Routes } from "react-router-dom";
import Header from "./components/header/Header";
import SignUpPage from "./pages/SignUpPage";
import LoginPage from "./pages/LoginPage";
import CreateQuestionPage from "./pages/CreateQuestionPage";
import EditQuestionPage from "./pages/EditQuestionPage";
import MainPage from "./pages/MainPage";
import Footer from "./components/footer/footer";

function App() {
  return (
    <div className="App ">
      <Header />
      <div className="flex-1 min-h-screen ">
        <Routes>
          <Route path="/signup" element={<SignUpPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/createQuestion" element={<CreateQuestionPage />} />
          <Route
            path="/questions/:questionId/edit"
            element={<EditQuestionPage />}
          ></Route>
          <Route path="*" element={<MainPage />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}

export default App;
