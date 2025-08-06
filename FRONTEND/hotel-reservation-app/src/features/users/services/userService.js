import apiClient from "../../../services/apiClient";

export const createUser = async (data) => {
  const response = await apiClient.post("/users/register", data);
  return response.data;
};

