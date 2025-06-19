// src/hooks/useFeatures.js
import { useEffect, useState } from "react";
import apiClient from '../../../services/apiClient'; 

export default function useFeatures() {
  const [features, setFeatures] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchFeatures = async () => {
      try {
        const response = await apiClient.get("/features"); 
        setFeatures(response.data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchFeatures();
  }, []);

  return { features, loading, error };
}
