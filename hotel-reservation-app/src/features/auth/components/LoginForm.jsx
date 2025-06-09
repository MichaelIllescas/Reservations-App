import { useState } from "react";
import { Link } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import "../styles/loginForm.css";

function LoginForm() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    recordar: false,
  });

  const [showPassword, setShowPassword] = useState(false);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({ ...formData, [name]: type === "checkbox" ? checked : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Login:", formData);
  };

  const togglePasswordVisibility = () => {
    setShowPassword(prev => !prev);
  };

  return (
    <div className="container">
      
    <form onSubmit={handleSubmit} className="p-4 border rounded loginForm">
      <h3 className="mb-3">Iniciar Sesión</h3>

      <div className="mb-3">
        <label>Email</label>
        <input
          type="email"
          className="form-control"
          name="email"
          onChange={handleChange}
          required
          />
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
            className="btn bg-white icon-password"
            onClick={togglePasswordVisibility}
            tabIndex={-1}
            >
            {showPassword ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>
      </div>

      <div className="form-check mb-2">
        <input
          type="checkbox"
          className="form-check-input"
          name="recordar"
          onChange={handleChange}
          />
        <label className="form-check-label">Recordarme</label>
      </div>

      <button className="btn btn-primary w-100">Ingresar</button>

      <div className="mb-3 text-center forgot-passwor-link">
        <Link to="/recuperar-clave" className="small forgot-passwor-link">
          ¿Olvidaste tu contraseña?
        </Link>
      </div>
    </form>
          </div>
  );
}

export default LoginForm;
