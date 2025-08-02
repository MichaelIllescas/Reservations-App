import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "../styles/lodgingDetail.css";
import BackArrowButton from "../../../Components/BackArrow/BackArrowButton";
import ProductImageGallery from "../components/ImageGallery";
import { FaBed } from "react-icons/fa";
import CommentList from "../components/CommentList";

function LodgingDetail() {
  const location = useLocation();
  const alojamiento = location.state?.alojamiento;
  const navigate = useNavigate();

  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);

  // Redirigir si no se pasó el alojamiento por props
  if (!alojamiento) {
    navigate("/", { replace: true });
    return null;
  }

  const fechasOcupadas =
    alojamiento.fechasOcupadas?.map((f) => new Date(f)) || [];

  return (
    <div className="row lodging-detail-container bg-white">
      {/* Imágenes */}
      <div className="col-md-6 mb-4 mb-md-0">
        <div className="heading-divider position-relative my-2">
          <h1 className="d-inline-block bg-white px-3 text-dark">
            {alojamiento.name}
          </h1>
        </div>
        <ProductImageGallery
          images={alojamiento.imageUrls?.map((img) =>
            img.startsWith("http") ? img : `http://localhost:8080${img}`
          )}
        />
      </div>

      {/* Detalles */}
      <div className="col-md-6 d-flex flex-column details">
        <hr className="hr" />
        <BackArrowButton />

        <div className="text-warning mb-3 valoration">
          <span>
            {"★".repeat(4)}
            {"☆".repeat(1)}
            <small className="text-muted ms-2">(0 opiniones)</small>
          </span>
        </div>

        <p className="text-dark">{alojamiento.description}</p>

        <p className="text-dark">
          <strong>Precio:</strong>{" "}
          {alojamiento.dailyPrice !== undefined
            ? `$${alojamiento.dailyPrice.toLocaleString()} por noche`
            : "Precio no disponible"}
        </p>

        <div className="mb-3 text-dark features">
          <strong>Detalles:</strong>
          <ul>
            {alojamiento.features?.map((f) => (
              <li key={f.id}>
                <FaBed className="me-2" />
                {f.name}
              </li>
            ))}
          </ul>
        </div>

        <div className="mb-3 text-dark">
          <h5>Seleccioná tu estadía</h5>
          <div className="d-flex gap-3 flex-wrap">
            <div>
              <label>Desde:</label>
              <DatePicker
                selected={startDate}
                onChange={(date) => setStartDate(date)}
                excludeDates={fechasOcupadas}
                minDate={new Date()}
                className="form-control"
                placeholderText="Fecha de ingreso"
              />
            </div>
            <div>
              <label>Hasta:</label>
              <DatePicker
                selected={endDate}
                onChange={(date) => setEndDate(date)}
                excludeDates={fechasOcupadas}
                minDate={startDate || new Date()}
                className="form-control"
                placeholderText="Fecha de salida"
              />
            </div>
          </div>
        </div>

        <button className="btn btn-success">Reservar</button>
      </div>

      <CommentList />
    </div>
  );
}

export default LodgingDetail;
