import { useState } from "react";
import "./home.css";
import SearchBar from "../../Components/SearchBar/SearchBar";
import AlojamientoCard from "../../features/lodgings/components/AlojamientoCard";
import AlojamientoDisponibleCard from "../../features/lodgings/components/AlojamientoDisponibleCard";
import useAllLodgings from "../../features/lodgings/hooks/useAllLodgings";
import img from "../../assets/img/Cabañas-El-Molino.webp";

// Recomendaciones destacadas (estáticas por ahora)
const recomendaciones = [
  {
    id: 1,
    nombre: "Hotel La Laguna",
    valoracion: 4,
    opiniones: 45,
    precioPorNoche: 22000,
    detalles: ["Habitación doble", "Baño privado", "Wi-Fi", "Desayuno incluido"],
  },
  {
    id: 2,
    nombre: "Cabañas El Molino",
    valoracion: 5,
    opiniones: 62,
    precioPorNoche: 18500,
    detalles: ["Para 4 personas", "Parrilla", "Aire acondicionado", "Cocina equipada"],
  },
  {
    id: 3,
    nombre: "Casa Rural Los Álamos",
    valoracion: 4,
    opiniones: 31,
    precioPorNoche: 16000,
    detalles: ["2 dormitorios", "Chimenea", "Jardín privado", "Cocina completa"],
  },
  {
    id: 4,
    nombre: "Departamento céntrico",
    valoracion: 4,
    opiniones: 58,
    precioPorNoche: 20000,
    detalles: ["1 dormitorio", "Aire acondicionado", "Cocina", "Ubicación céntrica"],
  },
];

const alojamientosPorPagina = 10;

function Home() {
  const { lodgings, loading, error } = useAllLodgings();
  const [paginaActual, setPaginaActual] = useState(1);

  const indexInicio = (paginaActual - 1) * alojamientosPorPagina;
  const indexFin = indexInicio + alojamientosPorPagina;
  const alojamientosPaginados = lodgings.slice(indexInicio, indexFin);
  const totalPaginas = Math.ceil(lodgings.length / alojamientosPorPagina);

  const cambiarPagina = (pagina) => {
    if (pagina >= 1 && pagina <= totalPaginas) {
      setPaginaActual(pagina);
    }
  };

  if (loading) return <p className="text-center mt-4">Cargando alojamientos...</p>;
  if (error) return <p className="text-center text-danger mt-4">Error al cargar alojamientos.</p>;

  return (
    <main className="main-content container">
      {/* Buscador */}
      <section className="search-section">
        <h1 className="my-2 highlighted-title">Busca tu alojamiento en Trenque Lauquen</h1>
        <SearchBar />
      </section>

      {/* Categorías y Recomendaciones */}
      <div className="d-flex flex-column flex-md-row">
        <section className="categories-section me-md-5">
          <h2 className="highlighted-title">Categorías</h2>
          <div className="categories-list">
            <div className="category-item">Hoteles</div>
            <div className="category-item">Departamentos</div>
            <div className="category-item">Cabañas</div>
            <div className="category-item">Casas rurales</div>
          </div>
        </section>

        <section className="recommendations-section flex-grow-1">
          <h2 className="text-center highlighted-title">Recomendaciones destacadas</h2>
          <div className="container">
            <div className="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
              {recomendaciones.map((alojamiento) => (
                <div className="col" key={alojamiento.id}>
                  <AlojamientoCard alojamiento={alojamiento} />
                </div>
              ))}
            </div>
          </div>
        </section>
      </div>

      {/* Alojamientos Disponibles */}
      <section className="available-section mt-5">
        <div className="container">
          <h2 className="text-center mb-4 highlighted-title">Alojamientos disponibles</h2>

          {/* Paginación superior */}
          <div className="d-flex justify-content-center mt-4 flex-wrap gap-2 mb-3">
            <button
              className="btn btn-sm btn-outline-secondary bg-dark"
              onClick={() => cambiarPagina(paginaActual - 1)}
              disabled={paginaActual === 1}
            >
              ←
            </button>
            {Array.from({ length: totalPaginas }, (_, i) => (
              <button
                key={i + 1}
                onClick={() => cambiarPagina(i + 1)}
                className={`btn btn-sm ${
                  paginaActual === i + 1 ? "btn-primary" : "btn-outline-primary"
                }`}
              >
                {i + 1}
              </button>
            ))}
            <button
              className="btn btn-sm btn-outline-secondary bg-dark"
              onClick={() => cambiarPagina(paginaActual + 1)}
              disabled={paginaActual === totalPaginas}
            >
              →
            </button>
          </div>

          {/* Cards */}
          <div className="row row-cols-1 row-cols-md-2 row-cols-lg-2 g-4">
            {alojamientosPaginados.map((alojamiento) => (
              <div className="col" key={alojamiento.id}>
                <AlojamientoDisponibleCard alojamiento={alojamiento} />
                
              </div>
            ))}
          </div>

          {/* Paginación inferior */}
          <div className="d-flex justify-content-center mt-2 flex-wrap gap-2">
            <button
              className="btn btn-sm btn-outline-secondary bg-dark"
              onClick={() => cambiarPagina(paginaActual - 1)}
              disabled={paginaActual === 1}
            >
              ←
            </button>
            {Array.from({ length: totalPaginas }, (_, i) => (
              <button
                key={i + 1}
                onClick={() => cambiarPagina(i + 1)}
                className={`btn btn-sm ${
                  paginaActual === i + 1 ? "btn-primary" : "btn-outline-primary"
                }`}
              >
                {i + 1}
              </button>
            ))}
            <button
              className="btn btn-sm btn-outline-secondary bg-dark"
              onClick={() => cambiarPagina(paginaActual + 1)}
              disabled={paginaActual === totalPaginas}
            >
              →
            </button>
          </div>
        </div>
      </section>
    </main>
  );
}

export default Home;
