import React, { useState, useEffect } from "react";
import AddressForm from "../../../Components/Address/AddressForm";

export default function ResponsibleForm({ value, onChange, shouldReset }) {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [documentNumber, setDocumentNumber] = useState("");
  const [address, setAddress] = useState(null);

  // Reset total cuando shouldReset se activa
  useEffect(() => {
    if (shouldReset) {
      setFullName("");
      setEmail("");
      setPhone("");
      setDocumentNumber("");
      setAddress(null);
    }
  }, [shouldReset]);

  // Restaurar desde value (opcional, por si querés cargar datos)
  useEffect(() => {
    if (value) {
      setFullName(value.fullName || "");
      setEmail(value.email || "");
      setPhone(value.phone || "");
      setDocumentNumber(value.documentNumber || "");
      setAddress(value.address || null);
    }
  }, [value]);

  // Sincronizar con el padre
  useEffect(() => {
    if (onChange && fullName && email && phone && documentNumber && address) {
      onChange({
        fullName,
        email,
        phone,
        documentNumber,
        address,
      });
    } else if (onChange) {
      onChange(null);
    }
  }, [fullName, email, phone, documentNumber, address]);

  return (
    <div className="responsible-form mt-4">
      <h5>Datos del responsable</h5>

      <div className="row g-3">
        <div className="col-md-6">
          <label>
            Nombre completo
            <input
              type="text"
              className="form-control"
              placeholder="Ej: Juan Carlos Gómez"
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
            />
          </label>
        </div>

        <div className="col-md-6">
          <label>
            Email
            <input
              type="email"
              className="form-control"
              placeholder="Ej: correo@email.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </label>
        </div>

        <div className="col-md-6">
          <label>
            Teléfono
            <input
              type="text"
              className="form-control"
              placeholder="Ej: 2923400000"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
            />
          </label>
        </div>

        <div className="col-md-6">
          <label>
            Documento
            <input
              type="text"
              className="form-control"
              placeholder="Ej: 30111222"
              value={documentNumber}
              onChange={(e) => setDocumentNumber(e.target.value)}
            />
          </label>
        </div>
      </div>

      <div className="mt-4">
        <AddressForm
          shouldReset={shouldReset}
          onAddressChange={setAddress}
          value={value?.address}
        />
      </div>
    </div>
  );
}
