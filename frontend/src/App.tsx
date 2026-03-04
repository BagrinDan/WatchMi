import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Auth/Login/Login";
import Register from "./Auth/Register/Register"
import Index from "./Main/index";


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

export default App;
