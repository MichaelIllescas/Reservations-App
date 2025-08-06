import { useEffect, useState } from "react";
import AdminNavbar from "../../admin/components/AdminNavbar";
import {
  createCategory,
  getCategories,
  updateCategory,
  deleteCategory,
} from "../services/categoryService";

export default function AddCategoryPage() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [message, setMessage] = useState("");
  const [categories, setCategories] = useState([]);
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    const { data } = await getCategories();
    setCategories(data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await updateCategory(editingId, { title, description });
        setMessage("Categoría actualizada");
      } else {
        await createCategory({ title, description });
        setMessage("Categoría creada con éxito");
      }
      setTitle("");
      setDescription("");
      setEditingId(null);
      fetchCategories();
    } catch {
      setMessage("Error al guardar la categoría");
    }
  };

  const handleEdit = (cat) => {
    setTitle(cat.title);
    setDescription(cat.description);
    setEditingId(cat.id);
  };

  const handleDelete = async (id) => {
    await deleteCategory(id);
    fetchCategories();
  };

  const handleCancel = () => {
    setTitle("");
    setDescription("");
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
                  {editingId ? "Editar categoría" : "Agregar categoría"}
                </h2>
                {message && <div className="alert alert-info">{message}</div>}
                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Título</label>
                    <input
                      className="form-control"
                      value={title}
                      onChange={(e) => setTitle(e.target.value)}
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Descripción</label>
                    <textarea
                      className="form-control"
                      value={description}
                      onChange={(e) => setDescription(e.target.value)}
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
                  <th>Título</th>
                  <th>Descripción</th>
                  <th className="text-end">Acciones</th>
                </tr>
              </thead>
              <tbody>
                {categories.length > 0 ? (
                  categories.map((cat) => (
                    <tr key={cat.id}>
                      <td>{cat.title}</td>
                      <td>{cat.description}</td>
                      <td className="text-end">
                        <button
                          className="btn btn-sm btn-outline-primary me-2"
                          onClick={() => handleEdit(cat)}
                        >
                          Editar
                        </button>
                        <button
                          className="btn btn-sm btn-outline-danger"
                          onClick={() => handleDelete(cat.id)}
                        >
                          Eliminar
                        </button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="3" className="text-center">
                      No hay categorías
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
