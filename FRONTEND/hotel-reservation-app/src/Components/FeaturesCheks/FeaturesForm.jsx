import '../FeaturesCheks/featureForm.css'

export const FEATURE_MAP = {
  AIRE_ACONDICIONADO: "AIR_CONDITIONING",
  CALEFACCION: "HEATING",
  WIFI: "WIFI",
  TV: "TV",
  PISCINA: "POOL",
  COCHERA: "GARAGE",
  COCINA: "KITCHEN",
  BALCON: "BALCONY",
  ACCESIBLE: "ACCESSIBLE",
  APTO_MASCOTAS: "PET_FRIENDLY",
  ROPA_DE_CAMA: "BED_LINEN",
  DESAYUNO_INCLUIDO: "BREAKFAST_INCLUDED",
};

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
        {Object.keys(FEATURE_MAP).map((feature) => (
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
