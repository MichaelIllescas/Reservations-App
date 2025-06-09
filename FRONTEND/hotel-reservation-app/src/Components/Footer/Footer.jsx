import styles from "./Footer.module.css";
import logo from "../../assets/img/logo.png"

function Footer() {
  return (
    <footer className={`${styles.footer} mt-0 py-4`}>
      <div className="container d-flex flex-column flex-md-row justify-content-between align-items-center">
        <div className="text-center text-md-start mb-2 mb-md-0">
          <img src={logo} alt="Logo" className={styles.logo} />
          <span className="ms-2">&copy; {new Date().getFullYear()} Reservas Trenque</span>
        </div>
        <div className="text-center text-md-end">
          <a href="#" className="text-decoration-none text-light me-3">Términos</a>
          <a href="#" className="text-decoration-none text-light">Privacidad</a>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
