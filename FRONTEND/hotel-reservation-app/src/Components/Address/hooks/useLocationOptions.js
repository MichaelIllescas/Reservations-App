// src/hooks/useLocationOptions.js
import { useEffect, useState } from "react";
import apiClient from "../../../services/apiClient";

export default function useLocationOptions() {
  const [countries, setCountries] = useState([]);
  const [provinces, setProvinces] = useState([]);
  const [cities, setCities] = useState([]);

  const [selectedCountryId, setSelectedCountryId] = useState("");
  const [selectedProvinceId, setSelectedProvinceId] = useState("");

  useEffect(() => {
    apiClient.get("/countries").then((res) => setCountries(res.data.data));
  }, []);

  useEffect(() => {
    if (selectedCountryId) {
      apiClient
        .get(`/provinces/by-country/${selectedCountryId}`)
        .then((res) => setProvinces(res.data.data));
    }
  }, [selectedCountryId]);

  useEffect(() => {
    if (selectedProvinceId) {
      apiClient
        .get(`/cities/by-province/${selectedProvinceId}`)
        .then((res) => setCities(res.data.data));
    }
  }, [selectedProvinceId]);

  return {
    countries,
    provinces,
    cities,
    selectedCountryId,
    selectedProvinceId,
    setSelectedCountryId,
    setSelectedProvinceId,
    setCountries,
    setProvinces,
    setCities,
  };
}
