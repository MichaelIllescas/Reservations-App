import { useEffect, useState } from "react";
import {
  createCategory,
  getCategories,
  updateCategory,
  deleteCategory,
} from "../services/categoryService";

export default function useCategories() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [message, setMessage] = useState("");
  const [categories, setCategories] = useState([]);
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    fetchCategories();
    // eslint-disable-next-line
  }, []);

  const fetchCategories = async () => {
    try {
      const { data } = await getCategories();
      setCategories(data);
    } catch {
      setMessage("Error al cargar las categorías");
    }
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
    try {
      await deleteCategory(id);
      fetchCategories();
    } catch {
      setMessage("Error al eliminar la categoría");
    }
  };

  const handleCancel = () => {
    setTitle("");
    setDescription("");
    setEditingId(null);
  };

  return {
    title,
    setTitle,
    description,
    setDescription,
    message,
    setMessage,
    categories,
    editingId,
    handleSubmit,
    handleEdit,
    handleDelete,
    handleCancel,
  };
}