import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home/Home";
import RegisterPage from "../features/auth/pages/RegisterPage";
import LoginPage from "../features/auth/pages/LoginPage";
import LodgingDetail from "../features/lodgings/pages/LodgingDetail";
import AdminDashboard from "../features/admin/pages/AdminDashboard";
import AddLodgingPage from "../features/lodgings/pages/AddLodgingPage";
import LodgingPageList from "../features/lodgings/pages/LodgingPageList";
import EditLodgingPage from "../features/lodgings/pages/EditLodgingPage";
import FeatureAdminPage from "../features/features/pages/FeatureAdminPage";
import AddCategoryPage from "../features/categories/pages/AddCategoryPage";
import { ProtectedRoute } from "./ProtectedRoute";
import { DeviceProvider } from "../Context/DeviceContext";
import ProtectedAdminWrapper from "../Components/Modals/ProtectedAdminWrapper";
import UsersList from "../features/users/pages/UsersList";
import CreateUserPage from "../features/users/pages/CreateUserPage";

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
          <Route path="/editlodging/:id" element={<EditLodgingPage />} />
          <Route path="/usersList" element={<UsersList />} />
          <Route path="/admin/users/new" element={<CreateUserPage />} />
          <Route path="/admin/features" element={<FeatureAdminPage />} />
          <Route path="/admin/categories/new" element={<AddCategoryPage />} />
        </Route>
      </Routes>
    </DeviceProvider>
  );
}

export default AppRouter;
