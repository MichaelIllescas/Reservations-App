import { useAuth } from "../../Context/AuthContext";
import "./styles/Header.css";
import { Link } from "react-router-dom";
import UserProfileDropDown from "./UserProfileDropDown";

function Header() {
  const { user } = useAuth();

  return (
    <header>
      {user ? (
        <nav className="navbar navbar-expand-lg navbar-dark custom-navbar">
          <div className="container-fluid">
            <a href="/" className="navbar-brand d-flex align-items-center">
              <img src="src/assets/img/logo.png" className="logo-img" alt="Logo" />
              <span>Alojamientos Trenque Lauquen</span>
            </a>

            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarNav">
              <div className="ms-auto d-flex">
                <UserProfileDropDown />
              </div>
            </div>
          </div>
        </nav>
      ) : (
        <nav className="navbar navbar-expand-lg navbar-dark custom-navbar">
          <div className="container-fluid">
            <a href="/" className="navbar-brand d-flex align-items-center">
              <img src="src/assets/img/logo.png" className="logo-img" alt="Logo" />
              <span>Alojamientos Trenque Lauquen</span>
            </a>

            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarNav">
              <div className="ms-auto d-flex">
                <Link to={"/register"} className="btn btn-outline-light me-2">
                  Crear cuenta
                </Link>
                <Link to={"/login"} className="btn btn-outline-light">
                  Iniciar sesión
                </Link>
              </div>
            </div>
          </div>
        </nav>
      )}
    </header>
  );
}

export default Header;
