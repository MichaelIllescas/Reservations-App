import { useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import "../styles/registerForm.css";
import TerminosModal from "./TérminosModal";

function RegisterForm() {
  const [formData, setFormData] = useState({
    nombre: "",
    apellido: "",
    email: "",
    password: "",
    confirmPassword: "",
    telefono: "",
    aceptarTerminos: false,
  });

  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({ ...formData, [name]: type === "checkbox" ? checked : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Registrando:", formData);
  };

  return (
    <form onSubmit={handleSubmit} className="p-4 border rounded register-form">
      <h3 className="mb-3">Registro de Usuario</h3>

      <div className="mb-3">
        <label>Nombre</label>
        <input type="text" className="form-control" name="nombre" onChange={handleChange} required />
      </div>

      <div className="mb-3">
        <label>Apellido</label>
        <input type="text" className="form-control" name="apellido" onChange={handleChange} required />
      </div>

      <div className="mb-3">
        <label>Email</label>
        <input type="email" className="form-control" name="email" onChange={handleChange} required />
      </div>

      <div className="mb-3">
        <label>Contraseña</label>
        <div className="input-group">
          <input
            type={showPassword ? "text" : "password"}
            className="form-control"
            name="password"
            onChange={handleChange}
            required
          />
          <button
            type="button"
            className="btn icon-password bg-white"
            onClick={() => setShowPassword(prev => !prev)}
            tabIndex={-1}
          >
            {showPassword ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>
      </div>

      <div className="mb-3">
        <label>Confirmar Contraseña</label>
        <div className="input-group">
          <input
            type={showConfirmPassword ? "text" : "password"}
            className="form-control"
            name="confirmPassword"
            onChange={handleChange}
            required
          />
          <button
            type="button"
            className="btn icon-password bg-white"
            onClick={() => setShowConfirmPassword(prev => !prev)}
            tabIndex={-1}
          >
            {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>
      </div>

      <div className="mb-3">
        <label>Teléfono (opcional)</label>
        <input type="tel" className="form-control" name="telefono" onChange={handleChange} />
      </div>

      <div className="form-check mb-3">
          <input
            type="checkbox"
            className="form-check-input"
            name="aceptarTerminos"
            required
          />
          <label className="form-check-label">
            Acepto los{" "}
            <span
              className=""
              role="button"
              style={{ textDecoration: "underline", cursor: "pointer" }}
              onClick={() => setShowModal(true)}
            >
              términos y condiciones
            </span>
          </label>
        </div>

       
   

      <TerminosModal show={showModal} handleClose={() => setShowModal(false)} />
      <button className="btn btn-primary w-100">Registrarse</button>
    </form>
  );
}

export default RegisterForm;
