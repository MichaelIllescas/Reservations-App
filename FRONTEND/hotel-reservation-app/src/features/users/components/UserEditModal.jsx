import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { updateUser } from "../services/userService";
import { showSuccessAlert, showErrorAlert } from "../../../Components/Alerts/alerts";
import { validateEditUserForm } from "../validation/validateEditUserForm";

export default function UserEditModal({ show, onClose, user, onUpdated }) {
  const [formData, setFormData] = useState({
    id: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    documentNumber: "",
    address: "",
    enabled: true,
    role: "USER",
    registrationDate: "",
  });
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (user) {
      setFormData({
        id: user.id,
        firstName: user.firstName || "",
        lastName: user.lastName || "",
        email: user.email || "",
        phone: user.phone || "",
        documentNumber: user.documentNumber || "",
        address: user.address || "",
        enabled: user.enabled,
        role: user.role,
        registrationDate: user.registrationDate || "",
      });
      setErrors({});
    }
  }, [user]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    const validationErrors = validateEditUserForm(formData);
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }
    try {
      setLoading(true);
      await updateUser(formData);
      showSuccessAlert("Usuario actualizado", "Los datos se guardaron correctamente");
      onUpdated?.();
      onClose();
    } catch (err) {
      const message = err?.response?.data?.message || "Error al actualizar el usuario";
      showErrorAlert("Error", message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Modal show={show} onHide={onClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Editar Usuario</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3">
            <Form.Label>Nombre</Form.Label>
            <Form.Control
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
            />
            {errors.firstName && <p className="text-danger mt-2">{errors.firstName}</p>}
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Apellido</Form.Label>
            <Form.Control
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
            />
            {errors.lastName && <p className="text-danger mt-2">{errors.lastName}</p>}
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />
            {errors.email && <p className="text-danger mt-2">{errors.email}</p>}
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Teléfono (opcional)</Form.Label>
            <Form.Control
              type="tel"
              name="phone"
              value={formData.phone}
              onChange={handleChange}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>
          Cancelar
        </Button>
        <Button variant="primary" onClick={handleSave} disabled={loading}>
          {loading ? "Guardando..." : "Guardar"}
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

