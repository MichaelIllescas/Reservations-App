import "../styles/productUpload.css";
import AddressForm from "../../../Components/Address/AddressForm";
import FeaturesForm from "../../../Components/FeaturesCheks/FeaturesForm";
import ImageUpload from "../../../Components/UploadImages/ImageUpload";
import useForm from "../../../hooks/useForm";
import useFeatures from "../hooks/useFeatures";
import validateProductForm from "../validations/productFormValidation";
import { useEffect, useState } from "react";
import LodgingTypeSelect from "./LodgingTypeSelect";
import CapacityInput from "./CapacityInput";
import PriceInput from "./PriceInput";
import ResponsibleForm from "./ResponsibleForm";
import LodgingTitleInput from "./LodgingTitleInput";
import LodgingDescriptionInput from "./LodgingDescriptionInput";
import useLodgingUpdate from "../hooks/useLodgingUpdate";
import apiClient from "../../../services/apiClient";
import { showSuccessAlert } from "../../../Components/Alerts/alerts";
import { useNavigate } from "react-router-dom";

export default function EditLodgingForm({ lodgingId }) {
  const { features, loading, error } = useFeatures();
  const { updateLodging, isSubmitting, submitError } = useLodgingUpdate(lodgingId);
  const [address, setAddress] = useState(null);
  const [responsible, setResponsible] = useState(null);
  const [images, setImages] = useState([]);
  const navigate = useNavigate();

  const { formData, errors, handleChange, handleSubmit, setFormData } = useForm(
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

  useEffect(() => {
    const fetchLodging = async () => {
      try {
        const res = await apiClient.get(`/lodgings/${lodgingId}`);
        const lodg = res.data.data;
        setFormData({
          titulo: lodg.name,
          descripcion: lodg.description,
          ubicacion: "",
          tipo: lodg.lodgingTypeId.toString(),
          capacidad: lodg.capacity.toString(),
          comodidades: lodg.featureIds,
          precio: lodg.dailyPrice.toString(),
        });
        setAddress(lodg.address);
        setResponsible(lodg.responsible);
      } catch (err) {
        console.error("Error al cargar alojamiento", err);
      }
    };
    fetchLodging();
  }, [lodgingId, setFormData]);

  return (
    <div className="bg-white p-5 mt-2 product-upload-container">
      <h1>Editar alojamiento</h1>

      <form
        onSubmit={async (e) => {
          e.preventDefault();
          const validationErrors = validateProductForm(formData);
          if (Object.keys(validationErrors).length > 0) {
            return;
          }
          try {
            await updateLodging(formData, address, responsible, images);
            showSuccessAlert(
              "¡Alojamiento actualizado!",
              "Los datos del alojamiento se guardaron correctamente."
            );
            navigate("/lodgingList");
          } catch (error) {
            console.error("Error al actualizar alojamiento:", error);
          }
        }}
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

        <AddressForm onAddressChange={setAddress} value={address} />
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

        <ResponsibleForm value={responsible} onChange={setResponsible} />

        <ImageUpload onSelectFiles={setImages} />

        <div className="form-submit">
          <button type="submit" className="btn-submit" disabled={isSubmitting}>
            {isSubmitting ? "Guardando..." : "Guardar cambios"}
          </button>
        </div>

        {submitError && <p className="error mt-2">{submitError}</p>}
      </form>
    </div>
  );
}

