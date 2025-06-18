import "../Address/addressForm.css";
export default function DireccionForm({ direccion, setDireccion }) {
  const handleChange = (e) => {
    const { name, value } = e.target;
    setDireccion({ ...direccion, [name]: value });
  };

  return (
    <fieldset className="direccion-form">
      <label>
        Calle
        <input
          name="calle"
          placeholder="Ej. Sarminto"
          value={direccion?.calle || ""}
          onChange={handleChange}
          className="form-input"
        />
      </label>

      <label>
        Número
        <input
          name="numero"
          placeholder="Ej. 1031"
          value={direccion?.numero || ""}
          onChange={handleChange}
          className="form-input"
        />
      </label>

      <label>
        Ciudad
        <input
          name="ciudad"
          placeholder="Ej. Trenque Lauquen"
          value={direccion?.ciudad || ""}
          onChange={handleChange}
          className="form-input"
        />
      </label>

      <label>
        Código postal
        <input
          name="codigoPostal"
          placeholder="Ej. 6400"
          value={direccion?.codigoPostal || ""}
          onChange={handleChange}
          className="form-input"
        />
      </label>

      <label>
        Provincia
        <input
          name="provincia"
          placeholder="Ej. Buenos Aires"
          value={direccion?.provincia || ""}
          onChange={handleChange}
          className="form-input"
        />
      </label>
    </fieldset>
  );
}
