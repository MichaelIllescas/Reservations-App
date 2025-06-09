import { useParams } from "react-router-dom";
import { useState } from "react";
import Slider from "react-slick";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./lodgingDetail.css";
import img1 from '../../../assets/img/Cabañas-El-Molino.webp'
import img2 from '../../../assets/img/Casa-Rural-Los-Álamos.webp'
import img3 from '../../../assets/img/Departamento-céntrico.webp'
import img4 from '../../../assets/img/hotel-la-laguna.webp'

const mockLodging = {
  id: 12,
  nombre: "Cabaña El Sol",
  descripcion: "Una hermosa cabaña rodeada de naturaleza.",
  precioPorNoche: 19500,
  valoracion: 4,
  opiniones: 28,
  detalles: ["Wi-Fi", "Parrilla", "2 dormitorios", "Estacionamiento"],
  imagenes: [
      img1,
      img2,
      img3,
      img4
  ],
  fechasOcupadas: [new Date(2025, 5, 12), new Date(2025, 5, 15), new Date(2025, 5, 22)]
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
   <div className="row lodging-detail-container">
  {/* Imágenes - izquierda */}
  <div className="col-md-6 mb-4 mb-md-0">
    <Slider {...settings}>
      {alojamiento.imagenes.map((img, i) => (
        <div key={i}>
          <img src={img} alt={`imagen-${i}`} className="img-fluid rounded slider-image" />
        </div>
      ))}
    </Slider>
  </div>

  {/* Detalles - derecha */}
  <div className="col-md-6 d-flex flex-column details">
    <h2 className="mb-3">{alojamiento.nombre}</h2>

    <p className="text-white">{alojamiento.descripcion}</p>

    <p><strong>Precio:</strong> ${alojamiento.precioPorNoche.toLocaleString()} por noche</p>

    <div className="mb-3">
      <strong>Detalles:</strong>
      <ul>
        {alojamiento.detalles.map((d, i) => (
          <li key={i}>{d}</li>
        ))}
      </ul>
    </div>

    <div className="mb-3">
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
        <div>
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
</div>

  );
}

export default LodgingDetail;
