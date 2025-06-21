import { useEffect, useState, useCallback } from "react";
import apiClient from "../../../services/apiClient";

export default function useLodgingList() {
  const [lodgings, setLodgings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchLodgings = useCallback(async () => {
    setLoading(true);
    try {
      const response = await apiClient.get("/lodgings/getLodgings");
      setLodgings(response.data.data);
    } catch (err) {
      console.error("Error al obtener alojamientos:", err);
      setError(err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchLodgings();
  }, [fetchLodgings]);

  return {
    lodgings,
    loading,
    error,
    reloadLodgings: fetchLodgings, // ← ¡importante!
  };
}
