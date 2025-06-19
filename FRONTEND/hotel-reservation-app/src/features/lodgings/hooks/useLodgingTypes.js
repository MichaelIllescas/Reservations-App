import { useEffect, useState } from "react";
import { getLodgingTypes } from "../services/lodgingTypeService"; 

export default function useLodgingTypes() {
  const [types, setTypes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchTypes = () => {
    setLoading(true);
    getLodgingTypes()
      .then((res) => {
        setTypes(res.data.data);
      })
      .catch(() => {
        setError("Error al cargar los tipos.");
      })
      .finally(() => {
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchTypes();
  }, []);

  return { types, loading, error, setTypes, fetchTypes };
}
