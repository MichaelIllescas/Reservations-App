import { useEffect, useState } from "react";
import apiClient from "../../services/apiClient";
import "../SearchBar/SearchBar.css";

function SearchBar({ onResults }) {
  const [query, setQuery] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [suggestions, setSuggestions] = useState([]);

  useEffect(() => {
    const fetchSuggestions = async () => {
      if (query.length > 2) {
        try {
          const res = await apiClient.get("/lodgings/search", {
            params: { query },
          });
          setSuggestions(res.data.data.map((l) => l.name));
        } catch (err) {
          setSuggestions([]);
        }
      } else {
        setSuggestions([]);
      }
    };
    fetchSuggestions();
  }, [query]);

  const handleSuggestionClick = (text) => {
    setQuery(text);
    setSuggestions([]);
  };

  const handleSearch = async () => {
    try {
      const res = await apiClient.get("/lodgings/search", {
        params: { query, startDate, endDate },
      });
      onResults(res.data.data);
    } catch (err) {
      onResults([]);
    }
    setSuggestions([]);
  };

  return (
    <>
      <div className="search-bar container">
        {/* Campo de texto para buscar por nombre o ubicación */}
        <div className="search-field position-relative">
          <label htmlFor="type-housing">Alojamiento</label>
          <input
            id="type-housing"
            name="type-housing"
            type="text"
            placeholder="Buscar alojamiento..."
            className="search-input"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
          {suggestions.length > 0 && (
            <ul className="suggestions">
              {suggestions.map((s) => (
                <li key={s} onClick={() => handleSuggestionClick(s)}>
                  {s}
                </li>
              ))}
            </ul>
          )}
        </div>
        {/* Campo para seleccionar fecha de inicio */}
        <div className="search-field">
          <label htmlFor="in-date">Ingreso</label>
          <input
            id="in-date"
            name="in-date"
            type="date"
            className="search-date"
            placeholder="Fecha de entrada"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
          />
        </div>
        {/* Campo para seleccionar fecha de fin */}
        <div className="search-field">
          <label htmlFor="out-date">Salida</label>
          <input
            id="out-date"
            name="out-date"
            type="date"
            className="search-date"
            placeholder="Fecha de salida"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
          />
        </div>

        {/* Botón de búsqueda */}
        <div className="d-flex justify-content-center align-items-center">
          <button className="search-button" onClick={handleSearch}>
            Buscar
          </button>
        </div>
      </div>
    </>
  );
}

export default SearchBar;
