import { useEffect, useState } from "react";
import apiClient from "../../../services/apiClient"; // ajustá si cambia la ruta

export default function useLodgingList() {
  const [lodgings, setLodgings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchLodgings = async () => {
      try {
        const response = await apiClient.get("/lodgings");
        setLodgings(response.data.data); // ← accedemos a 'data' como array de alojamientos
      } catch (err) {
        console.error("Error al obtener alojamientos:", err);
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchLodgings();
  }, []);

  return { lodgings, loading, error };
}
