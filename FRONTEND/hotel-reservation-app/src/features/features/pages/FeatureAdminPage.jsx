import { useEffect, useState } from "react";
import AdminNavbar from "../../admin/components/AdminNavbar";
import FeatureFormModal from "../components/FeatureFormModal";
import { getFeatures, createFeature, updateFeature, deleteFeature } from "../services/featureService";

export default function FeatureAdminPage() {
  const [features, setFeatures] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [editing, setEditing] = useState(null);

  const fetchData = () => {
    setLoading(true);
    getFeatures()
      .then((res) => setFeatures(res.data))
      .catch(() => setError("Error al cargar las características"))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleSave = async (data) => {
    try {
      if (editing) {
        await updateFeature(editing.id, data);
      } else {
        await createFeature(data);
      }
      setShowModal(false);
      setEditing(null);
      fetchData();
    } catch (e) {
      console.error(e);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("¿Eliminar característica?")) {
      await deleteFeature(id);
      fetchData();
    }
  };

  return (
    <div>
      <AdminNavbar />
      <div className="container mt-4">
        <h2>Características</h2>
        <button className="btn btn-primary mb-3" onClick={() => { setEditing(null); setShowModal(true); }}>
          Añadir nueva
        </button>
        {loading ? (
          <p>Cargando...</p>
        ) : error ? (
          <p className="text-danger">{error}</p>
        ) : (
          <table className="table">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Ícono</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {features.map((f) => (
                <tr key={f.id}>
                  <td>{f.name}</td>
                  <td>{f.icon}</td>
                  <td>
                    <button className="btn btn-sm btn-secondary me-2" onClick={() => { setEditing(f); setShowModal(true); }}>
                      Editar
                    </button>
                    <button className="btn btn-sm btn-danger" onClick={() => handleDelete(f.id)}>
                      Eliminar
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      {showModal && (
        <FeatureFormModal
          show={showModal}
          onClose={() => setShowModal(false)}
          onSave={handleSave}
          feature={editing}
        />
      )}
    </div>
  );
}

