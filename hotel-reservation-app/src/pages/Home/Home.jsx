import React from "react";
import "../Home/Home.css";
import SearchBar from "../../Components/SearchBar/SearchBar";

function Home() {
  return (
    <main className="main-content">
      {/* Buscador */}
      <section className="search-section">
        <h1 className=" my-3 ">Busca tu alojamiento en Trenque Lauquen</h1>
        <SearchBar />
      </section>

      {/* Categorías */}
      <section className="categories-section">
        <h2>Categorías de alojamiento</h2>
        <div className="categories-list">
          <div className="category-item">Hoteles</div>
          <div className="category-item">Departamentos</div>
          <div className="category-item">Cabañas</div>
          <div className="category-item">Casas rurales</div>
        </div>
      </section>

      {/* Recomendaciones */}
      <section className="recommendations-section">
        <h2 className="text-center my-4">Recomendaciones destacadas</h2>
        <div className="container">
          <div className="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
            {/* Card 1 */}
            <div className="col">
              <div className="card h-100">
                <img
                  src="/src/assets/img/hotel-la-laguna.webp"
                  className="card-img-top"
                  alt="Hotel La Laguna"
                />
                <div className="card-body">
                  <h5 className="card-title">Hotel La Laguna</h5>
                  <p className="card-text">
                    Un lugar cómodo y tranquilo, ideal para escapadas en Trenque
                    Lauquen.
                  </p>
                </div>
                <div className="card-footer">
                  <button className="btn btn-primary w-100">Reservar</button>
                </div>
              </div>
            </div>

            {/* Card 2 */}
            <div className="col">
              <div className="card h-100">
                <img
                  src="/src/assets/img/Cabañas-El-Molino.webp"
                  className="card-img-top"
                  alt="Cabañas El Molino"
                />
                <div className="card-body">
                  <h5 className="card-title">Cabañas El Molino</h5>
                  <p className="card-text">
                    Rodeadas de naturaleza, nuestras cabañas ofrecen descanso y
                    aventura.
                  </p>
                </div>
                <div className="card-footer">
                  <button className="btn btn-primary w-100">Reservar</button>
                </div>
              </div>
            </div>

            {/* Card 3 */}
            <div className="col">
              <div className="card h-100">
                <img
                  src="/src/assets/img/Casa-Rural-Los-Álamos.webp"
                  className="card-img-top"
                  alt="Casa Rural Los Álamos"
                />
                <div className="card-body">
                  <h5 className="card-title">Casa Rural Los Álamos</h5>
                  <p className="card-text">
                    Disfruta de la vida rural con las comodidades modernas en
                    Los Álamos.
                  </p>
                </div>
                <div className="card-footer">
                  <button className="btn btn-primary w-100">Reservar</button>
                </div>
              </div>
            </div>

            {/* Card 4 */}
            <div className="col">
              <div className="card h-100">
                <img
                  src="/src/assets/img/Departamento-céntrico.webp"
                  className="card-img-top"
                  alt="Departamento céntrico"
                />
                <div className="card-body">
                  <h5 className="card-title">Departamento céntrico</h5>
                  <p className="card-text">
                    Perfecto para quienes buscan comodidad en el corazón de
                    Trenque Lauquen.
                  </p>
                </div>
                <div className="card-footer">
                  <button className="btn btn-primary w-100">Reservar</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}

export default Home;
