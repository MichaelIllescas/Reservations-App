import { NavLink } from "react-router-dom";
import { useLogout } from "../../features/auth/hooks/useLogout";
import { Modal, Button } from "react-bootstrap";
import { useAuth } from "../../Context/AuthContext";
import { User } from "lucide-react";
import { useState } from "react";
import styles from '../Header/styles/navbar.module.css';

const UserProfileDropDown = () => {
  const { handleLogout, isLoading } = useLogout();
  const [showLogoutModal, setShowLogoutModal] = useState(false);
  const { user, setUser  } = useAuth();


  const handleConfirmLogout = () => {
    setShowLogoutModal(false);
    setUser(null);
  
    handleLogout();
    
  };

  return (
    <>
      <div className="dropdown ms-auto">
        <button
          className={`btn  ${styles.dropdownButton} d-flex align-items-center`}
          type="button"
          id="dropdownUser"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          <div className={styles.dropdownAvatar}>
            {user.firstName[0]}
          </div>
          
        </button>

        <ul
          className="dropdown-menu dropdown-menu-end"
          aria-labelledby="dropdownUser"
        >
          <li>
            <NavLink className="dropdown-item" to="/acount">
              Cuenta
            </NavLink>
          </li>
          <li>
            <hr className="dropdown-divider" />
          </li>
          <li>
            <NavLink className="dropdown-item" to="/reservations-history">
              Mis reservas
            </NavLink>
          </li>
                      <hr className="dropdown-divider" />

           {user.role === 'ADMIN' && ( <li>
            <NavLink className="dropdown-item" to="/admindashboard">
              Panel de Administración
            </NavLink>
          </li>)}


          <li>
            <hr className="dropdown-divider" />
          </li>
          <li>
            <button
              className="dropdown-item"
              onClick={() => setShowLogoutModal(true)}
            >
              Cerrar Sesión
            </button>
          </li>
        </ul>
      </div>

      <Modal
        show={showLogoutModal}
        onHide={() => setShowLogoutModal(false)}
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title>Confirmar cierre de sesión</Modal.Title>
        </Modal.Header>
        <Modal.Body>¿Estás seguro de que deseas cerrar sesión?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowLogoutModal(false)}>
            Cancelar
          </Button>
          <Button
            variant="danger"
            onClick={handleConfirmLogout}
            disabled={isLoading}
          >
            {isLoading ? "Cerrando..." : "Cerrar Sesión"}
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default UserProfileDropDown;
