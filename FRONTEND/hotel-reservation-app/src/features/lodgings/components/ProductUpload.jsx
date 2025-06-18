import { useState } from "react";
import "../styles/productUpload.css";
import AddressForm from "../../../Components/Address/AddressForm";
import FeaturesForm from "../../../Components/FeaturesCheks/FeaturesForm";
import ImageUpload from "../../../Components/UploadImages/ImageUpload";

const COMODIDADES = [
  "AIRE_ACONDICIONADO",
  "CALEFACCION",
  "WIFI",
  "TV",
  "PISCINA",
  "COCHERA",
  "COCINA",
  "BALCON",
  "ACCESIBLE",
  "APTO_MASCOTAS",
  "ROPA_DE_CAMA",
  "DESAYUNO_INCLUIDO",
];

export default function ProductUpload() {
  const [form, setForm] = useState({
    titulo: "",
    descripcion: "",
    ubicacion: "",
    tipo: "",
    capacidad: "",
    comodidades: [],
    precio: "",
  });

  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleComodidadChange = (comodidad) => {
    setForm((prev) => {
      const alreadySelected = prev.comodidades.includes(comodidad);
      const updated = alreadySelected
        ? prev.comodidades.filter((c) => c !== comodidad)
        : [...prev.comodidades, comodidad];
      return { ...prev, comodidades: updated };
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Datos del alojamiento:", form);
  };

  return (
    <div className="  bg-white p-5 mt-2  product-upload-container ">
      <h1>Agregar un nuevo alojamiento</h1>

      <form onSubmit={handleSubmit} className="product-upload-form">
        <label>
          Título del alojamiento
          <input
            name="titulo"
            value={form.titulo}
            onChange={handleChange}
            placeholder="Ej: Casa de campo con piscina"
            className="form-input"
          />
        </label>

        <label>
          Descripción detallada
          <textarea
            name="descripcion"
            value={form.descripcion}
            onChange={handleChange}
            placeholder="Describe tu alojamiento con detalles"
            className="form-input"
          ></textarea>
        </label>

        <AddressForm />

        <label>
          Tipo de alojamiento
          <select
            name="tipo"
            value={form.tipo}
            onChange={handleChange}
            className="form-input"
          >
            <option value="">Selecciona el tipo</option>
            <option value="hotel">Hotel</option>
            <option value="departamento">Departamento</option>
            <option value="cabaña">Cabaña</option>
          </select>
        </label>

        <label>
          Capacidad (número de huéspedes)
          <input
            name="capacidad"
            value={form.capacidad}
            onChange={handleChange}
            placeholder="Ej: 4"
            className="form-input"
          />
        </label>

        <FeaturesForm
          selectedFeatures={form.comodidades}
          setSelectedFeatures={(features) =>
            setForm({ ...form, comodidades: features })
          }
        />

        <label>
          Precio por noche
          <input
            name="precio"
            value={form.precio}
            onChange={handleChange}
            placeholder="Ej: 100 USD"
            className="form-input"
          />
        </label>

        <ImageUpload
          onSelectFiles={(files) =>
            console.log("Archivos seleccionados:", files)
          }
        />

        <div className="form-submit">
          <button type="submit" className="btn-submit">
            Guardar y publicar
          </button>
        </div>
      </form>
    </div>
  );
}
