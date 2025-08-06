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
  const [confirmMessage, setConfirmMessage] = useState("");

  const openConfirm = (lodgingId, lodgingName) => {
    setSelectedLodgingId(lodgingId);
    setConfirmMessage(
      `¿Estás seguro de que deseas eliminar ${lodgingName}?`
    );
    setModalOpen(true);
  };

  const closeConfirm = () => {
    setSelectedLodgingId(null);
    setConfirmMessage("");
    setModalOpen(false);
  };

  const confirmDelete = async () => {
    try {
      await apiClient.delete(`/lodgings/delete/${selectedLodgingId}`);
      showSuccessAlert(
        "Eliminado",
        "El alojamiento fue eliminado correctamente."
      );
      onSuccess?.();
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
    confirmMessage,
  };
}
