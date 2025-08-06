import { useState } from "react";
import AdminNavbar from "../../admin/components/AdminNavbar";
import { createCategory } from "../services/categoryService";

export default function AddCategoryPage() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [imageUrl, setImageUrl] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createCategory({ title, description, imageUrl });
      setMessage("Categoría creada con éxito");
      setTitle("");
      setDescription("");
      setImageUrl("");
    } catch (err) {
      setMessage("Error al crear la categoría");
    }
  };

  return (
    <div>
      <AdminNavbar />
      <div className="container mt-4">
        <h2>Agregar categoría</h2>
        {message && <p>{message}</p>}
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
          <div className="mb-3">
            <label className="form-label">URL de imagen</label>
            <input
              className="form-control"
              value={imageUrl}
              onChange={(e) => setImageUrl(e.target.value)}
            />
          </div>
          <button type="submit" className="btn btn-primary">
            Guardar
          </button>
        </form>
      </div>
    </div>
  );
}
