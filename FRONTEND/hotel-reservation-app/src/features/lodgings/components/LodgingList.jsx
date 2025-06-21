import useLodgingList from "../hooks/useLodgingList";
import ConfirmationModal from "../../../Components/Modals/ConfirmationModal";
import { useState } from "react";
import "../styles/lodgingsList.css";
import useDeleteLodging from "../hooks/useDeleteLodging";
const LodgingList = () => {
  const { lodgings, loading, error, reloadLodgings } = useLodgingList();
  const { modalOpen, openConfirm, closeConfirm, confirmDelete } =
    useDeleteLodging(reloadLodgings);

  if (loading) return <p>Cargando alojamientos...</p>;
  if (error) return <p>Error al cargar alojamientos.</p>;

  return (
    <div className="table-responsive">
      <table className="table table-bordered">
        <thead className="thead-light">
          <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Ubicación</th>
            <th>Tipo</th>
            <th>Precio</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {lodgings.map((a) => (
            <tr key={a.id}>
              <td>{a.id}</td>
              <td>{a.name}</td>
              <td className="text-muted">
                {a.address.street}, {a.address.number},{a.address?.city},{" "}
                {a.address?.province}, {a.address.country}
              </td>
              <td className="text-muted">{a.lodgingType}</td>
              <td className="text-muted">${a.dailyPrice}/noche</td>
              <td>
                <span className="text-primary font-weight-bold btn-edit">
                  Editar
                </span>{" "}
                |{" "}
                <span
                  onClick={() => openConfirm(a.id)}
                  className="text-danger font-weight-bold btn-delete"
                >
                  Eliminar
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <ConfirmationModal
        isOpen={modalOpen}
        title="Confirmar Eliminación"
        message="¿Estás seguro de que deseas eliminar este elemento?"
        onConfirm={confirmDelete}
        onClose={closeConfirm}
      />
    </div>
  );
};

export default LodgingList;
