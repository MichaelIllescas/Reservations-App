import "../FeaturesCheks/featureForm.css";

export default function FeaturesForm({ availableFeatures = [], selectedFeatures, setSelectedFeatures }) {
  const safeSelected = Array.isArray(selectedFeatures) ? selectedFeatures : [];

  const toggleFeature = (featureId) => {
    setSelectedFeatures(
      safeSelected.includes(featureId)
        ? safeSelected.filter((f) => f !== featureId)
        : [...safeSelected, featureId]
    );
  };

  return (
    <fieldset className="features-box">
      <p>Características</p>
      <div className="features-grid">
        {availableFeatures.map((feature) => (
          <label key={feature.id} className="checkbox-item">
            <input
              type="checkbox"
              checked={safeSelected.includes(feature.id)}
              onChange={() => toggleFeature(feature.id)}
            />
            {feature.name}
          </label>
        ))}
      </div>
    </fieldset>
  );
}
