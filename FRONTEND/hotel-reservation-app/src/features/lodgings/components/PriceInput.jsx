// src/components/PriceInput/PriceInput.jsx
export default function PriceInput({ value, onChange, error }) {
  return (
    <label className="mt-3">
      Precio por noche
      <input
        name="precio"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        placeholder="Ej: 100 USD"
        className="form-input"
      />
      {error && <p className="error">{error}</p>}
    </label>
  );
}
