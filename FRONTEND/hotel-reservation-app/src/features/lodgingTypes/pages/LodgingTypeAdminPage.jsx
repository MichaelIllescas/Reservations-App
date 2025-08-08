import { useEffect, useState } from "react";
import AdminNavbar from "../../admin/components/AdminNavbar";
import {
  createLodgingType,
  getLodgingTypes,
  updateLodgingType,
  deleteLodgingType,
} from "../../lodgings/services/lodgingTypeService";

export default function LodgingTypeAdminPage() {
  const [name, setName] = useState("");
  const [icon, setIcon] = useState("");
  const [message, setMessage] = useState("");
  const [types, setTypes] = useState([]);
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchTypes();
  }, []);

  const fetchTypes = async () => {
    const { data } = await getLodgingTypes();
    setTypes(data.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await updateLodgingType(editingId, { name, icon });
        setMessage("Tipo actualizado");
      } else {
        await createLodgingType({ name, icon });
        setMessage("Tipo creado con éxito");
      }
      setName("");
      setIcon("");
      setEditingId(null);
      fetchTypes();
    } catch {
      setMessage("Error al guardar el tipo");
    }
  };

  const handleEdit = (type) => {
    setName(type.name);
    setIcon(type.icon || "");
    setEditingId(type.id);
  };

  const handleDelete = async (id) => {
    await deleteLodgingType(id);
    fetchTypes();
  };

  const handleCancel = () => {
    setName("");
    setIcon("");
    setEditingId(null);
  };

  return (
    <div>
      <AdminNavbar />
      <div className="container mt-4">
        <div className="row">
          <div className="col-md-4 mb-4">
            <div className="card">
              <div className="card-body">
                <h2 className="h5 mb-3">
                  {editingId ? "Editar tipo" : "Agregar tipo"}
                </h2>
                {message && <div className="alert alert-info">{message}</div>}
                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Nombre</label>
                    <input
                      className="form-control"
                      value={name}
                      onChange={(e) => setName(e.target.value)}
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Ícono</label>
                    <input
                      className="form-control"
                      value={icon}
                      onChange={(e) => setIcon(e.target.value)}
                    />
                  </div>
                  <button type="submit" className="btn btn-primary">
                    {editingId ? "Actualizar" : "Guardar"}
                  </button>
                  {editingId && (
                    <button
                      type="button"
                      className="btn btn-secondary ms-2"
                      onClick={handleCancel}
                    >
                      Cancelar
                    </button>
                  )}
                </form>
              </div>
            </div>
          </div>
          <div className="col-md-8">
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Ícono</th>
                  <th className="text-end">Acciones</th>
                </tr>
              </thead>
              <tbody>
                {types.length > 0 ? (
                  types.map((t) => (
                    <tr key={t.id}>
                      <td>{t.name}</td>
                      <td>{t.icon}</td>
                      <td className="text-end">
                        <button
                          className="btn btn-sm btn-outline-primary me-2"
                          onClick={() => handleEdit(t)}
                        >
                          Editar
                        </button>
                        <button
                          className="btn btn-sm btn-outline-danger"
                          onClick={() => handleDelete(t.id)}
                        >
                          Eliminar
                        </button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="3" className="text-center">
                      No hay tipos
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}
