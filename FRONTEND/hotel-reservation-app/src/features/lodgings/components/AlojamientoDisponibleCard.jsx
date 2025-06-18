import { FaBed, FaMapMarkerAlt } from "react-icons/fa";
import "../styles/AlojamientoDisponibleCard.css";
import { Link } from "react-router-dom";

function AlojamientoDisponibleCard({ alojamiento }) {
  return (
    <div className="card mb-3 alojamiento-disponible-card">
      <div className="row g-0">
        {/* Información a la izquierda */}
        <div className="col-md-6 d-flex flex-column p-3">
          <h5 className="card-title fw-bold">{alojamiento.nombre}</h5>

          <div className="text-warning mb-2">
            {"★".repeat(alojamiento.valoracion)}{"☆".repeat(5 - alojamiento.valoracion)}
            <small className="text-muted ms-2">({alojamiento.opiniones} opiniones)</small>
          </div>


          <ul className="list-unstyled text-muted small">
            {alojamiento.detalles.map((detalle, i) => (
                <li key={i}>
                <FaBed className="me-2" />
                {detalle}
              </li>
            ))}
          </ul>

        <p className="fw-bold text-primary mb-2">${alojamiento.precioPorNoche.toLocaleString()}<small>/noche</small></p>
          <Link to={"/lodgingDetail"} className="btn btn-outline-primary mt-auto link-details">Ver detalles</Link>
        </div>

        {/* Imagen a la derecha */}
        <div className="col-md-6">
          <img
            src={alojamiento.imagen || "https://via.placeholder.com/300x200"}
            className="img-fluid h-100 object-fit-cover rounded-end"
            alt={`Imagen de ${alojamiento.nombre}`}
          />
        </div>
      </div>
    </div>
  );
}

export default AlojamientoDisponibleCard;
