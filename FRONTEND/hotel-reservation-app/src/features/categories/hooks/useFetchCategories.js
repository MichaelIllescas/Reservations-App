import { useEffect, useState } from "react";
import { getCategories } from "../services/categoryService";

export default function useFetchCategories() {
  const [categories, setCategories] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await getCategories();
        setCategories(data);
      } catch {
        setMessage("Error al cargar las categorías");
      }
    };
    fetchData();
  }, []);

  return { categories, message };
}