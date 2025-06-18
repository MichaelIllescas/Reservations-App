import UserProfileDropDown from "./UserProfileDropDown";
import './styles/navbar.module.css';

const MainNavbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark custom-navbar">
      <div className="container-fluid">
        <a href="/" className="navbar-brand d-flex align-items-center">
          <img src="src/assets/img/logo.png" className="logo-img" alt="Logo" />
          <span>Alojamientos Trenque Lauquen</span>
        </a>

        <button
          className="navbar-toggler px-2 py-2 mx-2 "
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
  );
};

export default MainNavbar;
