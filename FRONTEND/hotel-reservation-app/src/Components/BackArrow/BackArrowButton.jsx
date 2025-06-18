import { useNavigate } from "react-router-dom";
import { FaArrowLeft } from "react-icons/fa"; // Ícono de flecha
import "./BackArrowButton.css"; // Opcional para estilos

function BackArrowButton() {
  const navigate = useNavigate();

  const handleBack = () => {
    navigate(-1); // Navega a la vista anterior
  };

  return (
    <button onClick={handleBack} className="back-arrow-button">
      <FaArrowLeft />
      <span className="ms-0"></span>
    </button>
  );
}

export default BackArrowButton;
