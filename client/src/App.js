import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Footer from "./components/footer/footer";
import CreateQuestion from "./pages/CreateQuestion";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <h1>헬로우 월드!</h1>
        <Link to="/createQuestion"> Quick Question </Link>
        <Routes>
          <Route path="/createQuestion" element={<CreateQuestion />} />
        </Routes>
      </BrowserRouter>

      <Footer />
    </div>
  );
}

export default App;
