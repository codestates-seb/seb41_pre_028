import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import SignUpPage from "./pages/SignUpPage";
import LoginPage from "./pages/LoginPage";
import CreateQuestion from "./pages/CreateQuestion";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <div className="fixed top-0 w-screen  bg-[#F8F9F9] flex justify-center">
          <Link to="/createQuestion"> Quick Question </Link>
          <Link to="/"> Home</Link>
          <Link to="/login"> Sign Up </Link>
          <Link to="/signup"> Sign In </Link>
        </div>
        <Routes>
          <Route path="/signup" element={<SignUpPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/createQuestion" element={<CreateQuestion />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
