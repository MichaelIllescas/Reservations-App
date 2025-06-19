import { useEffect, useState } from "react";
import apiClient from "../../../services/apiClient"; // Ajustá la ruta si es distinta

export default function useAllLodgings() {
  const [lodgings, setLodgings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchLodgings = async () => {
      try {
        const response = await apiClient.get("/lodgings");
        setLodgings(response.data.data);
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
