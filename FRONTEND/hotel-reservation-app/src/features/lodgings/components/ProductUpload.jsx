import "../styles/productUpload.css";
import AddressForm from "../../../Components/Address/AddressForm";
import FeaturesForm from "../../../Components/FeaturesCheks/FeaturesForm";
import ImageUpload from "../../../Components/UploadImages/ImageUpload";
import useForm from "../../../hooks/useForm";
import useFeatures from "../hooks/useFeatures";
import validateProductForm from "../validations/productFormValidation";
import { useState } from "react";
import LodgingTypeSelect from "./LodgingTypeSelect";
import CapacityInput from "./CapacityInput";
import PriceInput from "./PriceInput";
import ResponsibleForm from "./ResponsibleForm";
import LodgingTitleInput from "./LodgingTitleInput";
import LodgingDescriptionInput from "./LodgingDescriptionInput";
import useLodgingSubmit from "../hooks/useLodgingSubmit";
import { showSuccessAlert } from "../../../Components/Alerts/alerts";

export default function ProductUpload() {
  const { features, loading, error } = useFeatures();
  const { submitLodging, isSubmitting, submitError } = useLodgingSubmit();
  const [resetAddressForm, setResetAddressForm] = useState(false);
  const [resetResponsibleForm, setResetResponsibleForm] = useState(false);
  const [address, setAddress] = useState(null);
  const [responsible, setResponsible] = useState(null);
  const [images, setImages] = useState([]);
  const [formResetKey, setFormResetKey] = useState(0); // 👈 nuevo

  const { formData, errors, handleChange, handleSubmit, resetForm } = useForm(
    {
      titulo: "",
      descripcion: "",
      ubicacion: "",
      tipo: "",
      capacidad: "",
      comodidades: [],
      precio: "",
    },
    validateProductForm
  );

  return (
    <div className="bg-white p-5 mt-2 product-upload-container">
      <h1>Agregar un nuevo alojamiento</h1>

      <form
  onSubmit={handleSubmit(async (data) => {
    try {
      await submitLodging(data, address, responsible, images);
      await showSuccessAlert("¡Alojamiento publicado!", "Tu alojamiento fue creado exitosamente.");

      resetForm();
      setAddress(null);
      setResponsible(null);
      setImages([]);
      setFormResetKey((prev) => prev + 1);

      setResetAddressForm(true);
      setResetResponsibleForm(true);
      setTimeout(() => {
        setResetAddressForm(false);
        setResetResponsibleForm(false);
      }, 0);
    } catch (error) {
      console.error("Error al guardar alojamiento:", error);
    }
  })}
  className="product-upload-form"
>

        <LodgingTitleInput
          value={formData.titulo}
          onChange={handleChange}
          error={errors.titulo}
        />

        <LodgingDescriptionInput
          value={formData.descripcion}
          onChange={handleChange}
          error={errors.descripcion}
        />

        <AddressForm
          shouldReset={resetAddressForm}
          onAddressChange={setAddress}
        />
        <div className="d-flex">
          <LodgingTypeSelect
            value={formData.tipo}
            onChange={(val) =>
              handleChange({ target: { name: "tipo", value: val } })
            }
            error={errors.tipo}
          />
          <CapacityInput
            value={formData.capacidad}
            onChange={(val) =>
              handleChange({ target: { name: "capacidad", value: val } })
            }
            error={errors.capacidad}
          />
        </div>

        {loading ? (
          <p>Cargando comodidades...</p>
        ) : error ? (
          <p>Error al cargar comodidades</p>
        ) : (
          <FeaturesForm
            availableFeatures={features}
            selectedFeatures={formData.comodidades}
            setSelectedFeatures={(selected) =>
              handleChange({
                target: { name: "comodidades", value: selected },
              })
            }
          />
        )}

        <PriceInput
          value={formData.precio}
          onChange={(val) =>
            handleChange({ target: { name: "precio", value: val } })
          }
          error={errors.precio}
        />

        <ResponsibleForm
          shouldReset={resetResponsibleForm}
          onChange={setResponsible}
        />

        <ImageUpload key={formResetKey} onSelectFiles={setImages} />

        <div className="form-submit">
          <button type="submit" className="btn-submit" disabled={isSubmitting}>
            {isSubmitting ? "Guardando..." : "Guardar y publicar"}
          </button>
        </div>

        {submitError && <p className="error mt-2">{submitError}</p>}
      </form>
    </div>
  );
}
