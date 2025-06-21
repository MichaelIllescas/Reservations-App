import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home/Home";
import RegisterPage from "../features/auth/pages/RegisterPage";
import LoginPage from "../features/auth/pages/LoginPage";
import LodgingDetail from "../features/lodgings/pages/LodgingDetail";
import AdminDashboard from "../features/admin/pages/AdminDashboard";
import AddLodgingPage from "../features/lodgings/pages/AddLodgingPage";
import LodgingPageList from "../features/lodgings/pages/LodgingPageList";
import { ProtectedRoute } from "./ProtectedRoute";
import { DeviceProvider } from "../Context/DeviceContext";
import ProtectedAdminWrapper from "../Components/Modals/ProtectedAdminWrapper";

function AppRouter() {
  return (
    <DeviceProvider>
      <Routes>
        {/* Públicas */}
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/lodgingDetail" element={<LodgingDetail />} />

        {/* Admin protegida */}
        <Route
          element={
            <ProtectedAdminWrapper>
              <ProtectedRoute />
            </ProtectedAdminWrapper>
          }
        >
          <Route path="/admindashboard" element={<AdminDashboard />} />
          <Route path="/addlodging" element={<AddLodgingPage />} />
          <Route path="/lodgingList" element={<LodgingPageList />} />
        </Route>
      </Routes>
    </DeviceProvider>
  );
}

export default AppRouter;
