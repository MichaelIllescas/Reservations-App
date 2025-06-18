import { useAuth } from "../../Context/AuthContext";
import "./styles/Header.css";
import MainNavbar from "./MainNavbar";
import GuestNavbar from "./GuestNavbar";

function Header() {
  const { user } = useAuth();

  return (
    <header>
      {user ? (
       <MainNavbar/>
      ) : (
       <GuestNavbar/>
      )}
    </header>
  );
}

export default Header;
