import apiClient from "../../../Services/apiClient";

export const registerUser = async (data) => {
  const response = await apiClient.post("/users/register", data);
  return response.data;
};
