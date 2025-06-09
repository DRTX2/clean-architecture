import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import UserManager from "./pages/UserManager";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/users" element={<UserManager />} />
      </Routes> 
    </BrowserRouter>
  );
}

export default App;
