import React from "react";
import "./Header.css";

function Header() {
  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-dark custom-navbar">
        <div className="container-fluid">
          <a href="/" className="navbar-brand d-flex align-items-center">
            <img src="src/assets/img/logo.png " className="logo-img" />
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
              <button className="btn btn-outline-light me-2">
                Crear cuenta
              </button>
              <button className="btn btn-outline-light">Iniciar sesión</button>
            </div>
          </div>
        </div>
      </nav>
    </header>
  );
}

export default Header;
