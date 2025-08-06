export const validateEditUserForm = (values) => {
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

  return errors;
};

