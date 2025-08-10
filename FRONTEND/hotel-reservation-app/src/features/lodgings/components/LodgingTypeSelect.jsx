import { useState } from "react";
import useLodgingTypes from "../hooks/useLodgingTypes";
import AddLodgingTypeModal from "./AddLodgingTypeModal";

export default function LodgingTypeSelect({ value, onChange, error }) {
  const { types, loading, error: loadError, setTypes } = useLodgingTypes();
  const [showModal, setShowModal] = useState(false);

  const handleCreated = (newType) => {
    setTypes((prev) => [...prev, newType]);
    onChange(String(newType.id));
  };

  return (
    <label className="mt-3">
      Tipo de alojamiento
      <div className="d-flex align-items-center gap-2">
        <select
          className="form-input"
          value={value}
          onChange={(e) => onChange(e.target.value)}
        >
          <option value="">Selecciona el tipo</option>
          {loading ? (
            <option disabled>Cargando...</option>
          ) : loadError ? (
            <option disabled>{loadError}</option>
          ) : (
            types.map((type) => (
              <option key={type.id} value={type.id}>
                {type.name}
              </option>
            ))
          )}
        </select>
        <button
          type="button"
          className="btn btn-outline-primary btn-sm"
          onClick={() => setShowModal(true)}
          style={{ whiteSpace: "nowrap" }}
        >
          + Agregar
        </button>
      </div>
      {error && <p className="error">{error}</p>}
      <AddLodgingTypeModal
        show={showModal}
        onClose={() => setShowModal(false)}
        onCreated={handleCreated}
      />
    </label>
  );
}
