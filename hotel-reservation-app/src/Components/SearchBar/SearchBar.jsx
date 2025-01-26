import React from "react";
import "../SearchBar/SearchBar.css";

function SearchBar() {
  return (
    <div className="search-bar">
      {/* Campo de texto para buscar por nombre o ubicación */}
      <input
        type="text"
        placeholder="Buscar alojamiento..."
        className="search-input"
      />

      {/* Campo para seleccionar fecha de inicio */}
      <input
        type="date"
        className="search-date"
        placeholder="Fecha de entrada"
      />

      {/* Campo para seleccionar fecha de fin */}
      <input
        type="date"
        className="search-date"
        placeholder="Fecha de salida"
      />

      {/* Botón de búsqueda */}
      <button className="search-button">Buscar</button>
    </div>
  );
}

export default SearchBar;
