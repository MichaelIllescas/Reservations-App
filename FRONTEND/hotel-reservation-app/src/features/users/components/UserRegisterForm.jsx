import { useState } from "react";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import useForm from "../../../hooks/useForm";
import { validateAdminUserForm } from "../validation/validateAdminUserForm";
import { useCreateUser } from "../hooks/useCreateUser";

export default function UserRegisterForm() {
  const { handleCreateUser, isLoading } = useCreateUser();
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const { formData, errors, handleChange, handleSubmit, resetForm } = useForm(
    {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
      phone: "",
    },
    validateAdminUserForm
  );

  return (
    <form
      onSubmit={handleSubmit((data) => handleCreateUser(data, resetForm))}
      className="p-4 border rounded card shadow"
      noValidate
    >
      <h3 className="mb-3 text-center">Registrar Usuario</h3>

      <div className="mb-3">
        <label>Nombre</label>
        <input
          type="text"
          className="form-control"
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
        />
        {errors.firstName && <div className="text-danger">{errors.firstName}</div>}
      </div>

      <div className="mb-3">
        <label>Apellido</label>
        <input
          type="text"
          className="form-control"
          name="lastName"
          value={formData.lastName}
          onChange={handleChange}
        />
        {errors.lastName && <div className="text-danger">{errors.lastName}</div>}
      </div>

      <div className="mb-3">
        <label>Email</label>
        <input
          type="email"
          className="form-control"
          name="email"
          value={formData.email}
          onChange={handleChange}
        />
        {errors.email && <div className="text-danger">{errors.email}</div>}
      </div>

      <div className="mb-3">
        <label>Contraseña</label>
        <div className="input-group">
          <input
            type={showPassword ? "text" : "password"}
            className="form-control"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
          <button
            type="button"
            className="btn icon-password bg-white"
            onClick={() => setShowPassword((prev) => !prev)}
            tabIndex={-1}
          >
            {showPassword ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>
        {errors.password && <div className="text-danger">{errors.password}</div>}
      </div>

      <div className="mb-3">
        <label>Confirmar Contraseña</label>
        <div className="input-group">
          <input
            type={showConfirmPassword ? "text" : "password"}
            className="form-control"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
          />
          <button
            type="button"
            className="btn icon-password bg-white"
            onClick={() => setShowConfirmPassword((prev) => !prev)}
            tabIndex={-1}
          >
            {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>
        {errors.confirmPassword && (
          <div className="text-danger">{errors.confirmPassword}</div>
        )}
      </div>

      <div className="mb-3">
        <label>Teléfono (opcional)</label>
        <input
          type="tel"
          className="form-control"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
        />
      </div>

      <button className="btn btn-primary w-100" disabled={isLoading}>
        {isLoading ? "Registrando..." : "Registrar"}
      </button>
    </form>
  );
}

