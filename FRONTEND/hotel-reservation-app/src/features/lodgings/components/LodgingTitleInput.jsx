import React from "react";

export default function LodgingTitleInput({ value, onChange, error }) {
  return (
    <label>
      Título del alojamiento
      <input
        name="titulo"
        value={value}
        onChange={onChange}
        placeholder="Ej: Casa de campo con piscina"
        className="form-input"
      />
      {error && <p className="error">{error}</p>}
    </label>
  );
}
