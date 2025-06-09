import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../../Context/AuthContext';

export const useLogin = () => {
  const { loginUser } = useAuth();
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState('');

  const handleLogin = async (email, password) => {
    try {
      const user = await loginUser(email, password); // loginUser devuelve user

      if (user?.role === 'ADMIN') {
        navigate('/admindashboard');
      } else {
        navigate('/');
      }
    } catch (error) {
      const message = error.response?.data?.error || 'Error al iniciar sesión';
      setErrorMessage(message);
    }
  };

  return { handleLogin, errorMessage };
};
