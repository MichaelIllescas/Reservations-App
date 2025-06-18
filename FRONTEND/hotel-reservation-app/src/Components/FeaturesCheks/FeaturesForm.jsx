import '../FeaturesCheks/featureForm.css'

const FEATURES = [
  "AIRE_ACONDICIONADO",
  "CALEFACCION",
  "WIFI",
  "TV",
  "PISCINA",
  "COCHERA",
  "COCINA",
  "BALCON",
  "ACCESIBLE",
  "APTO_MASCOTAS",
  "ROPA_DE_CAMA",
  "DESAYUNO_INCLUIDO",
];

export default function FeaturesForm({ selectedFeatures, setSelectedFeatures }) {
  const safeSelected = Array.isArray(selectedFeatures) ? selectedFeatures : [];

  const toggleFeature = (feature) => {
    setSelectedFeatures(
      safeSelected.includes(feature)
        ? safeSelected.filter((f) => f !== feature)
        : [...safeSelected, feature]
    );
  };

  return (
    <fieldset className="features-box">
      <p>Características</p>
      <div className="features-grid">
        {FEATURES.map((feature) => (
          <label key={feature} className="checkbox-item">
            <input
              type="checkbox"
              checked={safeSelected.includes(feature)}
              onChange={() => toggleFeature(feature)}
            />
            {feature.replace(/_/g, " ")}
          </label>
        ))}
      </div>
    </fieldset>
  );
}
