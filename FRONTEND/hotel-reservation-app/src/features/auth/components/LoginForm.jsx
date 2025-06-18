import { useEffect, useState } from "react";
import useForm from "../../../hooks/useForm";
import { validateLoginForm } from "../Validation/validateLoginForm";
import { useLogin } from "../hooks/useLogin";
import logo from "../../../assets/img/logo.png";
import { Link } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import '../styles/loginForm.css';

const LoginForm = () => {
  const [rememberMe, setRememberMe] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

  const { formData, errors, handleChange, handleSubmit } = useForm(
    {
      email: "",
      password: "",
    },
    validateLoginForm
  );

  const { handleLogin, errorMessage } = useLogin();

  useEffect(() => {
    const savedEmail = localStorage.getItem("rememberedEmail");
    if (savedEmail) {
      handleChange({ target: { name: "email", value: savedEmail } });
      setRememberMe(true);
    }
  }, []);

  const onSubmit = async (formData) => {
    if (rememberMe) {
      localStorage.setItem("rememberedEmail", formData.email);
    } else {
      localStorage.removeItem("rememberedEmail");
    }

    await handleLogin(formData.email, formData.password);
  };

  return (
    <div className="card shadow-lg border-0 p-4">
      <div className="text-center mb-4 ">
        <img src={logo} alt="Logo" className="img-fluid logo-img-login" />
      </div>

      <form onSubmit={handleSubmit(onSubmit)} noValidate>
        {/* Email */}
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            className={`form-control ${errors.email ? "is-invalid" : ""}`}
            value={formData.email}
            onChange={handleChange}
            placeholder="Ingrese su email"
          />
          {errors.email && <div className="invalid-feedback">{errors.email}</div>}
        </div>

        {/* Contraseña */}
        <div className="mb-3">
          <label htmlFor="password" className="form-label">Contraseña</label>
          <div className="input-group">
            <input
              type={showPassword ? "text" : "password"}
              id="password"
              name="password"
              className={`form-control ${errors.password ? "is-invalid" : ""}`}
              value={formData.password}
              onChange={handleChange}
              placeholder="Ingrese su contraseña"
            />
            <button
              type="button"
              className="btn icon-password "
              onClick={() => setShowPassword(prev => !prev)}
              tabIndex={-1}
            >
              {showPassword ? <FaEyeSlash /> : <FaEye />}
            </button>
          </div>
          {errors.password && <div className="invalid-feedback">{errors.password}</div>}
        </div>

        {/* Recuérdame */}
        <div className="form-check mb-3">
          <input
            className="form-check-input"
            type="checkbox"
            id="rememberMe"
            checked={rememberMe}
            onChange={() => setRememberMe(!rememberMe)}
          />
          <label className="form-check-label" htmlFor="rememberMe">
            Recuérdame
          </label>
        </div>

        {/* Botón de envío */}
        <div className="d-grid">
          <button type="submit" className="btn btn-primary">
            Iniciar sesión
          </button>
        </div>
      </form>

      {/* Mensaje de error */}
      {errorMessage && (
        <div className="alert alert-danger mt-3 text-center">
          {errorMessage}
        </div>
      )}

      {/* Link a recuperar contraseña */}
      <div className="mt-3 text-center">
        <Link className="enlace" to={"/forgotPassword"}>
          ¿Olvidaste tu contraseña?
        </Link>
      </div>
    </div>
  );
};

export default LoginForm;
