// src/services/lodgingTypeService.js
import apiClient from "../../../services/apiClient";

export const getLodgingTypes = () => {
  return apiClient.get("/lodging-types");
};

export const createLodgingType = (data) => {
  return apiClient.post("/lodging-types", data);
};
