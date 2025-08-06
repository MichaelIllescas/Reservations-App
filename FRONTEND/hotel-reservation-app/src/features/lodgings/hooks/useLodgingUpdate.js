import { useState } from "react";
import apiClient from "../../../services/apiClient";
import { showErrorAlert } from "../../../Components/Alerts/alerts";

export default function useLodgingUpdate(lodgingId) {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitError, setSubmitError] = useState(null);

  const updateLodging = async (formData, address, responsible, images) => {
    setIsSubmitting(true);
    setSubmitError(null);
    try {
      const payload = {
        name: formData.titulo,
        description: formData.descripcion,
        dailyPrice: parseFloat(formData.precio),
        capacity: parseInt(formData.capacidad),
        lodgingTypeId: parseInt(formData.tipo),
        featureIds: formData.comodidades,
        address,
        responsible,
      };

      const jsonBlob = new Blob([JSON.stringify(payload)], {
        type: "application/json",
      });

      const formDataToSend = new FormData();
      formDataToSend.append("lodging", jsonBlob);

      if (images && images.length > 0) {
        images.forEach((img) => {
          formDataToSend.append("images", img);
        });
      }

      const response = await apiClient.put(`/lodgings/${lodgingId}`, formDataToSend, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      return response.data;
    } catch (err) {
      showErrorAlert("Error", err.response?.data?.message || "No se pudo actualizar el alojamiento.");
      setSubmitError("No se pudo actualizar el alojamiento.");
      throw err;
    } finally {
      setIsSubmitting(false);
    }
  };

  return {
    updateLodging,
    isSubmitting,
    submitError,
  };
}

