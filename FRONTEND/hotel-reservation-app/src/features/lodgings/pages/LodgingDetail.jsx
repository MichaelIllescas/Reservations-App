import { useParams } from "react-router-dom";
import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "../styles/lodgingDetail.css";
import img1 from "../../../assets/img/Cabañas-El-Molino.webp";
import img2 from "../../../assets/img/Casa-Rural-Los-Álamos.webp";
import img3 from "../../../assets/img/Departamento-céntrico.webp";
import img4 from "../../../assets/img/hotel-la-laguna.webp";
import BackArrowButton from "../../../Components/BackArrow/BackArrowButton";
import ProductImageGallery from "../components/ImageGallery";
import { FaBed } from "react-icons/fa";
import CommentList from "../components/CommentList";

const mockLodging = {
  id: 12,
  nombre: "Cabaña El Sol",
  descripcion: "Una hermosa cabaña rodeada de naturaleza.",
  precioPorNoche: 19500,
  valoracion: 4,
  opiniones: 28,
  detalles: ["Wi-Fi", "Parrilla", "2 dormitorios", "Estacionamiento"],
  imagenes: [img1, img2, img3, img4, img4],
  fechasOcupadas: [
    new Date(2025, 5, 12),
    new Date(2025, 5, 15),
    new Date(2025, 5, 22),
  ],
};

function LodgingDetail() {
  const { id } = useParams();
  const alojamiento = mockLodging; // 🔁 en el futuro será traído por ID

  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };

  const isDateOccupied = (date) => {
    return alojamiento.fechasOcupadas.some(
      (occupiedDate) => date.toDateString() === occupiedDate.toDateString()
    );
  };

  return (
    <div className="row lodging-detail-container bg-white ">
      {/* Imágenes - izquierda */}
      <div className="col-md-6 mb-4 mb-md-0">
        <div className="heading-divider  position-relative my-2">
          <h1 className="d-inline-block bg-white px-3 text-dark">
            {alojamiento.nombre}
          </h1>
        </div>

        <ProductImageGallery images={alojamiento.imagenes} />
      </div>

      {/* Detalles - derecha */}
      <div className="col-md-6 d-flex flex-column details">
<hr className="hr"/>
        <BackArrowButton />
        <div className="text-warning mb-3 valoration  ">
          <span>
            {"★".repeat(alojamiento.valoracion)}
            {"☆".repeat(5 - alojamiento.valoracion)}
            <small className="text-muted ms-2">
              ({alojamiento.opiniones} opiniones)
            </small>
          </span>
        </div>

        <p className="text-dark">{alojamiento.descripcion}</p>

        <p className="text-dark">
          <strong>Precio:</strong> $
          {alojamiento.precioPorNoche.toLocaleString()} por noche
        </p>

        <div className="mb-3 text-dark features">
          <strong>Detalles:</strong>
          <ul>
            {alojamiento.detalles.map((d, i) => (
              <li key={i}>
                {" "}
                <FaBed className="me-2" />
                {d}
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
                excludeDates={alojamiento.fechasOcupadas}
                minDate={new Date()}
                className="form-control"
                placeholderText="Fecha de ingreso"
              />
            </div>
            <div className="text-dark">
              <label>Hasta:</label>
              <DatePicker
                selected={endDate}
                onChange={(date) => setEndDate(date)}
                excludeDates={alojamiento.fechasOcupadas}
                minDate={startDate || new Date()}
                className="form-control"
                placeholderText="Fecha de salida"
              />
            </div>
          </div>
        </div>
        <button className="btn btn-success ">Reservar</button>
      </div>
      <CommentList />
    </div>
  );
}

export default LodgingDetail;
