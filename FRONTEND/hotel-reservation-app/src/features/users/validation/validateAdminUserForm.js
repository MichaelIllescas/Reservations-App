export const validateAdminUserForm = (values) => {
  const errors = {};

  if (!values.firstName || !values.firstName.trim()) {
    errors.firstName = "El nombre es obligatorio";
  }

  if (!values.lastName || !values.lastName.trim()) {
    errors.lastName = "El apellido es obligatorio";
  }

  if (!values.email || !values.email.trim()) {
    errors.email = "El email es obligatorio";
  } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(values.email)) {
    errors.email = "Email inválido";
  }

  if (!values.password) {
    errors.password = "La contraseña es obligatoria";
  } else if (values.password.length < 6) {
    errors.password = "Mínimo 6 caracteres";
  }

  if (!values.confirmPassword) {
    errors.confirmPassword = "Confirmación requerida";
  } else if (values.password !== values.confirmPassword) {
    errors.confirmPassword = "Las contraseñas no coinciden";
  }

  return errors;
};

