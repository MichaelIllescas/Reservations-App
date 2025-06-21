import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../Context/AuthContext";
import FullScreenLoader from "../Components/Loading/FullScreenLoader";


export const ProtectedRoute = ({ children }) => {
  const { user, loading } = useAuth();

  if (loading) return <FullScreenLoader/>;

  return user ? <Outlet />: <Navigate to="/login" replace />;
};
