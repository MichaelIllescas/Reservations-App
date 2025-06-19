// src/components/CapacityInput/CapacityInput.jsx
export default function CapacityInput({ value, onChange, error }) {
  return (
    <div>
      <label className="mt-3 mx-3">
        Capacidad (número de huéspedes)
        <div>
          <input
            name="capacidad"
            value={value}
            onChange={(e) => onChange(e.target.value)}
            placeholder="Ej: 4"
            className="form-input"
          />
          {error && <p className="error">{error}</p>}
        </div>
      </label>
    </div>
  );
}
