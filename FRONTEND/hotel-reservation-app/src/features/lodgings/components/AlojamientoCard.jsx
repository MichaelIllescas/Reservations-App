import { useNavigate } from "react-router-dom";
import { FaEye, FaBed } from "react-icons/fa";
import "../../../Components/SearchBar/SearchBar.css";
import '../components/alojamientoCard.css'

export default function AlojamientoCard({ alojamiento }) {
  const navigate = useNavigate();

  return (
    <div
      className="card h-100 shadow-sm p-3 d-flex flex-column small"
      style={{ minHeight: "260px" }}
    >
      {/* Estrellas */}
      <div className="mb-1 text-warning">
        {"★".repeat(alojamiento.valoracion)}
        {"☆".repeat(5 - alojamiento.valoracion)}
        <small className="text-muted ms-2">
          ({alojamiento.opiniones} opiniones)
        </small>
      </div>

      <h5 className="fw-bold">{alojamiento.nombre}</h5>

      {/* Detalles en lista */}
      <ul className="list-unstyled text-muted mb-1 text-start">
        {alojamiento.detalles.map((detalle, i) => (
          <li key={i}>
            <FaBed className="me-2" />
            {detalle}
          </li>
        ))}
      </ul>

      <div className="div-price">
        {/* Precio */}
        <p className="text-primary price fw-bold mb-1">
          ${alojamiento.precioPorNoche} por noche
        </p>

        {/* Botón de detalles */}
        <button
          className="btn btn-outline-primary mt-auto"
          onClick={() => navigate(`/detalle/${alojamiento.id}`)}
        >
          <FaEye className="me-2" />
          Ver detalles
        </button>
      </div>
    </div>
  );
}
