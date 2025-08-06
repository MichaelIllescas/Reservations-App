import { NavLink } from 'react-router-dom';
import { useState } from 'react';
import { FaChevronDown, FaChevronUp } from 'react-icons/fa';

export default function AdminNavbar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleNavbar = () => setIsOpen(!isOpen);

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4">
      <NavLink className="navbar-brand" to="/admindashboard">Panel Admin</NavLink>

      <button
        className="navbar-toggler"
        type="button"
        onClick={toggleNavbar}
        aria-label="Toggle navigation"
      >
        {isOpen ? <FaChevronUp color="white" /> : <FaChevronDown color="white" />}
      </button>

      <div className={`collapse navbar-collapse ${isOpen ? 'show' : ''}`} id="adminNavbar">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">

          {/* Productos Dropdown */}
          <li className="nav-item dropdown">
            <a className="nav-link dropdown-toggle" href="#" id="productosDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Productos
            </a>
            <ul className="dropdown-menu" aria-labelledby="productosDropdown">
              <li><NavLink className="dropdown-item" to="/lodgingList">Listar Productos</NavLink></li>
              <li><NavLink className="dropdown-item" to="/addlodging">Agregar Producto</NavLink></li>
              <li><NavLink className="dropdown-item" to="/admin/features">Administrar características</NavLink></li>
              <li><NavLink className="dropdown-item" to="/admin/categories/new">Agregar categoría</NavLink></li>
            </ul>
          </li>

          {/* Usuarios Dropdown */}
          <li className="nav-item dropdown">
            <a className="nav-link dropdown-toggle" href="#" id="usuariosDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Usuarios
            </a>
            <ul className="dropdown-menu" aria-labelledby="usuariosDropdown">
              <li><NavLink className="dropdown-item" to="/usersList">Listar Usuarios</NavLink></li>
              <li><NavLink className="dropdown-item" to="/admin/users/new">Registrar Usuario</NavLink></li>
            </ul>
          </li>

          {/* Reservaciones Dropdown */}
          <li className="nav-item dropdown">
            <a className="nav-link dropdown-toggle" href="#" id="reservasDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Reservaciones
            </a>
            <ul className="dropdown-menu" aria-labelledby="reservasDropdown">
              <li><NavLink className="dropdown-item" to="/admin/reservations">Listar Reservas</NavLink></li>
              <li><NavLink className="dropdown-item" to="/admin/reservations/calendar">Calendario</NavLink></li>
            </ul>
          </li>

        </ul>
      </div>
    </nav>
  );
}
