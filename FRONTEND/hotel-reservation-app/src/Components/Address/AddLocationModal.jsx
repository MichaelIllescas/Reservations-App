import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import apiClient from "../../services/apiClient";

export default function AddLocationModal({
  show,
  onClose,
  type,
  parentId,
  onCreated,
}) {
  const [name, setName] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSave = async () => {
    if (!name.trim()) {
      setError("El nombre no puede estar vacío.");
      return;
    }

    try {
      setLoading(true);
      setError("");

      let endpoint = "";
      let payload = { name };

      if (type === "country") {
        endpoint = "/countries";
      } else if (type === "province") {
        endpoint = "/provinces";
        payload.countryId = parentId;
      } else if (type === "city") {
        endpoint = "/cities";
        payload.provinceId = parentId;
      }

      const response = await apiClient.post(endpoint, payload);
      onCreated(response.data.data);
      setName("");
      onClose();
    } catch (err) {
      setError(`Error al guardar. ${err.response.data.error}` );
      console.error("Error al guardar:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
  if (!show) {
    // Se ejecuta cuando el modal se cierra
    setName("");
    setError("");
    setLoading(false);
  }
}, [show]);
  return (
    <Modal show={show} onHide={onClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>
          Agregar{" "}
          {type === "country"
            ? "país"
            : type === "province"
            ? "provincia"
            : "ciudad"}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form.Group>
          <Form.Label>Nombre</Form.Label>
          <Form.Control
            type="text"
            placeholder={`Ej: ${
              type === "country"
                ? "Argentina"
                : type === "province"
                ? "Buenos Aires"
                : "Trenque Lauquen"
            }`}
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          {error && <p className="text-danger mt-2">{error}</p>}
        </Form.Group>
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
