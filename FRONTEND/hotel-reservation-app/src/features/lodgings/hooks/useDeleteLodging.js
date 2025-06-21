// src/hooks/useDeleteLodging.js
import { useState } from "react";
import apiClient from "../../../services/apiClient";
import {
  showSuccessAlert,
  showErrorAlert,
} from "../../../Components/Alerts/alerts";

export default function useDeleteLodging(onSuccess) {
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedLodgingId, setSelectedLodgingId] = useState(null);

  const openConfirm = (lodgingId) => {
    setSelectedLodgingId(lodgingId);
    setModalOpen(true);
  };

  const closeConfirm = () => {
    setSelectedLodgingId(null);
    setModalOpen(false);
  };

  const confirmDelete = async () => {
    try {
      await apiClient.delete(`/lodgings/delete/${selectedLodgingId}`);
      showSuccessAlert(
        "Eliminado",
        "El alojamiento fue eliminado correctamente."
      );
      onSuccess?.(); // recarga lista si querés
    } catch (err) {
      const message =
        err?.response?.data?.message || "No se pudo eliminar el alojamiento.";
      showErrorAlert("Error", message);
    } finally {
      closeConfirm();
    }
  };

  return {
    modalOpen,
    openConfirm,
    closeConfirm,
    confirmDelete,
  };
}
