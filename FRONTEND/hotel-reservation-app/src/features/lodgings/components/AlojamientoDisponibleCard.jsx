import { FaBed, FaMapMarkerAlt } from "react-icons/fa";
import "../styles/AlojamientoDisponibleCard.css";
import { Link } from "react-router-dom";

function AlojamientoDisponibleCard({ alojamiento }) {
  return (
    <div className="card alojamiento-disponible-card h-100">
      <div className="row g-0 h-100">
        {/* Info izquierda */}
        <div className="col-md-6 d-flex flex-column p-3 justify-content-between">
          <div>
            <h5 className="card-title fw-bold">{alojamiento.name}</h5>

            <div className="text-warning mb-2">
              {"★".repeat(4)} {/* placeholder si no tenés puntuación real */}
              {"☆".repeat(1)}
              <small className="text-muted ms-2">(0 opiniones)</small>
            </div>

            <ul className="list-unstyled text-muted small">
              {alojamiento.features.map((detalle, i) => (
                <li key={i}>
                  <FaBed className="me-2" />
                  {detalle.replaceAll("_", " ")}
                </li>
              ))}
            </ul>
          </div>
          <div>
            <p className="fw-bold text-primary mb-2">
              ${alojamiento.dailyPrice.toLocaleString()}
              <small>/noche</small>
            </p>

            <Link
              to="/lodgingDetail"
              state={{ alojamiento }} // ← sin modificar nada
              className="btn btn-outline-primary link-details"
            >
              Ver detalles
            </Link>
          </div>
        </div>

        {/* Imagen derecha */}
        <div className="col-md-6 h-100">
          <img
            src={
              alojamiento.imageUrls?.[0]
                ? `http://localhost:8080${alojamiento.imageUrls[0]}`
                : "https://via.placeholder.com/300x200"
            }
            className="img-fluid h-100  rounded-end "
            alt={`Imagen de ${alojamiento.name}`}
          />
        </div>
      </div>
    </div>
  );
}


export default AlojamientoDisponibleCard;
