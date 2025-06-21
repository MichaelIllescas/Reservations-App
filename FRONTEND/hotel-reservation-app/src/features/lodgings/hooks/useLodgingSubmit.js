import { useState } from "react";
import apiClient from "../../../services/apiClient";
import { FEATURE_MAP } from "../../../Components/FeaturesCheks/FeaturesForm";
import {
  showErrorAlert
} from "../../../Components/Alerts/alerts";

export default function useLodgingSubmit() {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitError, setSubmitError] = useState(null);

  const submitLodging = async (formData, address, responsible, images) => {
    setIsSubmitting(true);
    setSubmitError(null);

    try {
      const payload = {
        name: formData.titulo,
        description: formData.descripcion,
        dailyPrice: parseFloat(formData.precio),
        capacity: parseInt(formData.capacidad),
        lodgingTypeId: parseInt(formData.tipo),
        features: formData.comodidades.map((f) => FEATURE_MAP[f]),
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

      const response = await apiClient.post("/lodgings", formDataToSend, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      return response.data;
    } catch (err) {
      showErrorAlert("Error", err.response.data.message);
      setSubmitError("No se pudo guardar el alojamiento.");
      throw err;
    } finally {
      setIsSubmitting(false);
    }
  };

  return {
    submitLodging,
    isSubmitting,
    submitError,
  };
}
