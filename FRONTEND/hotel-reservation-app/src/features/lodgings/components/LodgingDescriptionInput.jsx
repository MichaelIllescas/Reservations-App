import React from "react";

export default function LodgingDescriptionInput({ value, onChange, error }) {
  return (
    <label>
      Descripción detallada
      <textarea
        name="descripcion"
        value={value}
        onChange={onChange}
        placeholder="Describe tu alojamiento con detalles"
        className="form-input"
      />
      {error && <p className="error">{error}</p>}
    </label>
  );
}
