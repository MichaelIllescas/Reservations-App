import { useNavigate } from "react-router-dom";
import { FaEye, FaBed } from "react-icons/fa";
import "../../../Components/SearchBar/SearchBar.css";
import '../styles/alojamientoCard.css'

export default function AlojamientoCard({ alojamiento }) {
  const navigate = useNavigate();

  return (
    <div
      className="card h-100 shadow-sm p-3 d-flex flex-column small"
      style={{ minHeight: "260px", minWidth:"200px"}}
    >
      {/* Estrellas */}
      <div className="mb-1 text-warning valoration text-samll justify-content-center ">
        <span>

        {"★".repeat(alojamiento.valoracion)}
        {"☆".repeat(5 - alojamiento.valoracion)}
        <br />
        <small className="text-muted ms-2 ">
          ({alojamiento.opiniones} opiniones)
        </small>
        </span>
      </div>

      <h5 className="fw-bold title-card">{alojamiento.nombre}</h5>

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
          ${alojamiento.precioPorNoche}<small>/noche</small>
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
