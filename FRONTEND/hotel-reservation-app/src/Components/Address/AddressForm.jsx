import { useState, useEffect } from "react";
import useLocationOptions from "./hooks/useLocationOptions";
import AddLocationModal from "./AddLocationModal";

export default function AddressForm({ onAddressChange,shouldReset  }) {
  const {
    countries,
    provinces,
    cities,
    selectedCountryId,
    selectedProvinceId,
    setSelectedCountryId,
    setSelectedProvinceId,
    setCountries,
    setProvinces,
    setCities
  } = useLocationOptions();

  const [street, setStreet] = useState("");
  const [number, setNumber] = useState("");
  const [selectedCityId, setSelectedCityId] = useState("");
  const [modalType, setModalType] = useState(null);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    if (onAddressChange && street && number && selectedCityId) {
      onAddressChange({
        street,
        number,
        cityId: parseInt(selectedCityId),
      });
    }
  }, [street, number, selectedCityId]);

  const openModal = (type) => {
    setModalType(type);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
  };

  useEffect(() => {
  if (shouldReset) {
    setStreet("");
    setNumber("");
    setSelectedCityId("");
    setSelectedProvinceId("");
    setSelectedCountryId("");
  }
}, [shouldReset]);
  return (
    <div className="row g-3">
  {/* País */}
  <div className="col-md-6">
    <label>
      País
      <div className="d-flex align-items-center gap-2">
        <select
          className="form-control form-select"
          value={selectedCountryId}
          onChange={(e) => {
            setSelectedCountryId(e.target.value);
            setSelectedProvinceId("");
          }}
        >
          <option value="">Selecciona un país</option>
          {countries.map((c) => (
            <option key={c.id} value={c.id}>{c.name}</option>
          ))}
        </select>
        <button
          type="button"
          className="btn btn-outline-primary btn-sm "
          onClick={() => openModal("country")}
        >
          +Agregar
        </button>
      </div>
    </label>
  </div>

  {/* Provincia */}
  {selectedCountryId && (
    <div className="col-md-6">
      <label>
        Provincia
        <div className="d-flex align-items-center gap-2">
          <select
            className="form-control form-select"
            value={selectedProvinceId}
            onChange={(e) => setSelectedProvinceId(e.target.value)}
          >
            <option value="">Selecciona una provincia</option>
            {provinces.map((p) => (
              <option key={p.id} value={p.id}>{p.name}</option>
            ))}
          </select>
          <button
            type="button"
            className="btn btn-outline-primary btn-sm"
            onClick={() => openModal("province")}
          >
            +Agregar
          </button>
        </div>
      </label>
    </div>
  )}

  {/* Ciudad */}
  {selectedProvinceId && (
    <div className="col-md-6">
      <label>
        Ciudad
        <div className="d-flex align-items-center gap-2">
          <select
            className="form-control form-select"
            value={selectedCityId}
            onChange={(e) => setSelectedCityId(e.target.value)}
          >
            <option value="">Selecciona una ciudad</option>
            {cities.map((c) => (
              <option key={c.id} value={c.id}>{c.name}</option>
            ))}
          </select>
          <button
            type="button"
            className="btn btn-outline-primary btn-sm"
            onClick={() => openModal("city")}
          >
            +Agregar
          </button>
        </div>
      </label>
    </div>
  )}

  {/* Calle */}
  <div className="col-md-6">
    <label>
      Calle
      <input
        value={street}
        onChange={(e) => setStreet(e.target.value)}
        className="form-control"
        placeholder="Ej: Mitre"
      />
    </label>
  </div>

  {/* Número */}
  <div className="col-md-6">
    <label>
      Número
      <input
        value={number}
        onChange={(e) => setNumber(e.target.value)}
        className="form-control"
        placeholder="Ej: 123"
      />
    </label>
  </div>


   <AddLocationModal
        show={showModal}
        onClose={closeModal}
        type={modalType}
        parentId={
          modalType === "province"
            ? parseInt(selectedCountryId)
            : modalType === "city"
            ? parseInt(selectedProvinceId)
            : undefined
        }
        onCreated={(newItem) => {
          // Recargamos automáticamente
          if (modalType === "country") {
            setCountries((prev) => [...prev, newItem]);
            setSelectedCountryId(newItem.id);
          } else if (modalType === "province") {
            setProvinces((prev) => [...prev, newItem]);
            setSelectedProvinceId(newItem.id);
          } else if (modalType === "city") {
            setCities((prev) => [...prev, newItem]);
            setSelectedCityId(newItem.id);
          }
        }}
      />
</div>

  );
}
