import { Link } from "react-router-dom";
import LodgingList from "../components/LodgingList";
import AdminNavbar from "../../admin/components/AdminNavbar";
import "../styles/styleslodgingPageList.css";
import BackArrowButton from "../../../Components/BackArrow/BackArrowButton";

const LodgingPageList = () => {
  return (
    <>
      <AdminNavbar />
      <div className="container py-5 bg-white mt-2 container-list">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h1>Listado de Alojamientos</h1>
          <Link
            to={"/addlodging"}
            className="btn btn-outline-secondary btn-dark rounded-pill text-white"
          >
            Agregar Nuevo
          </Link>
        </div>

        <div className="mb-4">
          <div className="input-group">
            <span className="input-group-text bg-light border-1">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="20"
                height="20"
                fill="currentColor"
                viewBox="0 0 256 256"
              >
                <path d="M229.66,218.34l-50.07-50.06a88.11,88.11,0,1,0-11.31,11.31l50.06,50.07a8,8,0,0,0,11.32-11.32ZM40,112a72,72,0,1,1,72,72A72.08,72.08,0,0,1,40,112Z"></path>
              </svg>
            </span>
            <input
              type="text"
              className="form-control bg-light border-1"
              placeholder="Buscar alojamientos..."
            />
          </div>
        </div>

        <div className="d-flex gap-2 mb-4 flex-wrap">
          <span className="badge bg-light text-dark px-3 py-2">Ubicación</span>
          <span className="badge bg-light text-dark px-3 py-2">Tipo</span>
          <span className="badge bg-light text-dark px-3 py-2">Precio</span>
        </div>

        <LodgingList />
        <BackArrowButton />
      </div>
    </>
  );
};

export default LodgingPageList;
