import { useState } from "react";
import { registerUser } from "../services/registerUser";

export const useRegisterUser = () => {
  const [isLoading, setIsLoading] = useState(false);

  const handleRegister = async (formData, resetForm) => {
    try {
      setIsLoading(true);

      // ✅ Extraer solo los campos válidos para el backend
      const { firstName, lastName, email, password, phone } = formData;
      const payload = { firstName, lastName, email, password, phone };

      await registerUser(payload);
      alert("Registro exitoso");
       resetForm();
    } catch (error) {
      alert("Error al registrar usuario");
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };

  return { handleRegister, isLoading };
};
