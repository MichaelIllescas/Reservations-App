import { Routes, Route } from "react-router-dom";
import Home from "../features/lodgings/pages/Home";
import RegisterPage from "../features/auth/pages/RegisterPage";
import LoginPage from "../features/auth/pages/LoginPage";
import LodgingDetail from "../features/lodgings/pages/LodgingDetail";
function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/lodgingDetail" element={<LodgingDetail />} />

      


    </Routes>
  );
}

export default AppRouter;
