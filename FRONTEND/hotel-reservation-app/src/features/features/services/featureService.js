import apiClient from "../../../services/apiClient";

export const getFeatures = () => apiClient.get("/features");
export const createFeature = (data) => apiClient.post("/features", data);
export const updateFeature = (id, data) => apiClient.put(`/features/${id}`, data);
export const deleteFeature = (id) => apiClient.delete(`/features/${id}`);

