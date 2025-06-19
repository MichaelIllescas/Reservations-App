export default function validateProductForm(data) {
  const errors = {};

  if (!data.titulo) errors.titulo = "El título es obligatorio.";
  if (!data.descripcion) errors.descripcion = "La descripción es obligatoria.";
  if (!data.tipo) errors.tipo = "Selecciona un tipo de alojamiento.";
  if (!data.capacidad) errors.capacidad = "Indica la capacidad.";
  if (!data.precio) errors.precio = "El precio es obligatorio.";

  return errors;
}
