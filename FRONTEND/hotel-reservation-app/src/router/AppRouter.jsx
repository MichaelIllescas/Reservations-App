import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home/Home";
import RegisterPage from "../features/auth/pages/RegisterPage";
import LoginPage from "../features/auth/pages/LoginPage";
import LodgingDetail from "../features/lodgings/pages/LodgingDetail";
import AdminDashboard from "../features/admin/pages/AdminDashboard";
import AddLodgingPage from "../features/lodgings/pages/AddLodgingPage";
import { ProtectedRoute } from "../../src/router/ProtectedRoute";
import LodgingPageList from "../features/lodgings/pages/LodgingPageList";
function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/lodgingDetail" element={<LodgingDetail />} />
      <Route path="/admindashboard" element={<ProtectedRoute><AdminDashboard /></ProtectedRoute> } />
      <Route path="/addlodging" element={<ProtectedRoute> <AddLodgingPage/></ProtectedRoute>} />
            <Route path="/lodgingList" element={<ProtectedRoute> <LodgingPageList/></ProtectedRoute>} />

    </Routes>
  );
}

export default AppRouter;
