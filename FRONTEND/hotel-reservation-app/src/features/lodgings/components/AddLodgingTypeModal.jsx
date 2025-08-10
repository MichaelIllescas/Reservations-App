import { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { createLodgingType } from "../services/lodgingTypeService";

export default function AddLodgingTypeModal({ show, onClose, onCreated }) {
  const [name, setName] = useState("");
  const [icon, setIcon] = useState("");
  const [error, setError] = useState("");

  const handleSave = async () => {
    if (!name || !icon) {
      setError("Todos los campos son obligatorios.");
      return;
    }

    try {
      const res = await createLodgingType({ name, icon });
      onCreated(res.data.data);
      onClose();
      setName("");
      setIcon("");
      setError("");
    } catch (err) {
      setError(err?.response?.data?.error || "Error al guardar.");
    }
  };

  return (
    <Modal show={show} onHide={onClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Agregar tipo de alojamiento</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form.Group className="mb-3">
          <Form.Label>Nombre</Form.Label>
          <Form.Control
            type="text"
            placeholder="Ej: Cabaña"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Ícono</Form.Label>
          <Form.Control
            type="text"
            placeholder="Ej: fa-hotel"
            value={icon}
            onChange={(e) => setIcon(e.target.value)}
          />
        </Form.Group>
        {error && <p className="text-danger mt-2">{error}</p>}
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>
          Cancelar
        </Button>
        <Button variant="primary" onClick={handleSave}>
          Guardar
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
