// src/components/ProtectedAdminWrapper.jsx
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useDevice } from "../../Context/DeviceContext";

export default function ProtectedAdminWrapper({ children }) {
  const { isMobile } = useDevice();
  const navigate = useNavigate();

  useEffect(() => {
    if (isMobile) {
      Swal.fire({
        title: "Acceso restringido",
        text: "Esta sección solo está disponible desde un dispositivo de escritorio.",
        icon: "warning",
        confirmButtonText: "Volver al inicio",
        allowOutsideClick: false,
        allowEscapeKey: false,
      }).then(() => {
        navigate("/");
      });
    }
  }, [isMobile, navigate]); // ← cada vez que cambia `isMobile`, se vuelve a ejecutar

  if (isMobile) return null;

  return children;
}