import { useState } from "react";
import { createUser } from "../services/userService";
import { showSuccessAlert, showErrorAlert } from "../../../Components/Alerts/alerts";

export const useCreateUser = () => {
  const [isLoading, setIsLoading] = useState(false);

  const handleCreateUser = async (formData, resetForm) => {
    try {
      setIsLoading(true);
      const { firstName, lastName, email, password, phone } = formData;
      const payload = { firstName, lastName, email, password, phone };
      await createUser(payload);
      showSuccessAlert("Usuario registrado correctamente");
      resetForm();
    } catch (error) {
      showErrorAlert(error.response?.data?.error || "Error al registrar usuario");
    } finally {
      setIsLoading(false);
    }
  };

  return { handleCreateUser, isLoading };
};

