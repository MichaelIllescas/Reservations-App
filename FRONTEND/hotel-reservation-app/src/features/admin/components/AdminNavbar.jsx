import { NavLink } from 'react-router-dom';

export default function AdminNavbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4">
      <NavLink className="navbar-brand" to="/admin">Panel Admin</NavLink>

      <div className="collapse navbar-collapse">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <NavLink className="nav-link" to="/admin/products">Productos</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/admin/users">Usuarios</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/admin/reservations">Reservaciones</NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}
