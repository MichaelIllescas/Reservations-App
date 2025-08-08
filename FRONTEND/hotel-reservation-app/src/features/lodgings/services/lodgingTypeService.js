// src/services/lodgingTypeService.js
import apiClient from "../../../services/apiClient";

export const getLodgingTypes = () => {
  return apiClient.get("/lodging-types");
};

export const createLodgingType = (data) => {
  return apiClient.post("/lodging-types", data);
};

export const updateLodgingType = (id, data) => {
  return apiClient.put(`/lodging-types/${id}`, data);
};

export const deleteLodgingType = (id) => {
  return apiClient.delete(`/lodging-types/${id}`);
};
