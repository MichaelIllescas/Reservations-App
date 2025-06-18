import "../SearchBar/SearchBar.css";

function SearchBar() {
  return (
    <>
      <div className="search-bar container">
        {/* Campo de texto para buscar por nombre o ubicación */}
        <div>
          <label htmlFor="type-housing">Alojamiento</label>
          <input
            id="type-housing"
            name="type-housing"
            type="text"
            placeholder="Buscar alojamiento..."
            className="search-input"
          />
        </div>
        {/* Campo para seleccionar fecha de inicio */}
        <div>
          <label htmlFor="in-date">Ingreso</label>
          <input
            id="in-date"
            name="in-date"
            type="date"
            className="search-date"
            placeholder="Fecha de entrada"
          />
        </div>
        {/* Campo para seleccionar fecha de fin */}
        <div >
          <label htmlFor="out-date">Salida</label>
          <input
            id="out-date"
            name="out-date"
            type="date"
            className="search-date"
            placeholder="Fecha de salida"
          />
        </div>

        {/* Botón de búsqueda */}
        <div className="d-flex justify-content-center align-items-center">
          <button className="search-button">Buscar</button>
        </div>
      </div>
    </>
  );
}

export default SearchBar;
