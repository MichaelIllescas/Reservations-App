import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";

export default function FeatureFormModal({ show, onClose, onSave, feature }) {
  const [name, setName] = useState("");
  const [icon, setIcon] = useState("");

  useEffect(() => {
    if (feature) {
      setName(feature.name || "");
      setIcon(feature.icon || "");
    } else {
      setName("");
      setIcon("");
    }
  }, [feature]);

  const handleSave = () => {
    onSave({ name, icon });
  };

  return (
    <Modal show={show} onHide={onClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>{feature ? "Editar característica" : "Agregar característica"}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3">
            <Form.Label>Nombre</Form.Label>
            <Form.Control value={name} onChange={(e) => setName(e.target.value)} />
          </Form.Group>
          <Form.Group>
            <Form.Label>Ícono</Form.Label>
            <Form.Control value={icon} onChange={(e) => setIcon(e.target.value)} />
          </Form.Group>
        </Form>
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

