import { Modal, Button } from "react-bootstrap";

function TerminosModal({ show, handleClose }) {
  return (
    <Modal show={show} onHide={handleClose} size="lg" scrollable>
      <Modal.Header closeButton>
        <Modal.Title>Términos y Condiciones</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <p><strong>1. Aceptación de los términos</strong></p>
        <p>
          Al registrarte en esta aplicación, aceptás quedar obligado por estos términos y condiciones, 
          nuestra política de privacidad y cualquier norma adicional que podamos publicar.
        </p>

        <p><strong>2. Uso de la plataforma</strong></p>
        <p>
          Solo podés usar la aplicación para fines personales y legales. No podés usar la plataforma para 
          actividades fraudulentas, comerciales no autorizadas ni para violar leyes locales o internacionales.
        </p>

        <p><strong>3. Registro de usuario</strong></p>
        <p>
          Al registrarte, garantizás que la información proporcionada es verdadera, completa y actualizada. 
          Sos responsable de mantener la confidencialidad de tu contraseña.
        </p>

        <p><strong>4. Reservas</strong></p>
        <p>
          Las reservas están sujetas a disponibilidad. Es responsabilidad del usuario verificar los datos 
          antes de confirmar la operación. Cualquier cancelación debe realizarse según las políticas 
          de cada alojamiento.
        </p>

        <p><strong>5. Protección de datos</strong></p>
        <p>
          Respetamos tu privacidad y protegemos tus datos personales conforme a la ley de protección de datos. 
          Podés leer más en nuestra política de privacidad.
        </p>

        <p><strong>6. Modificaciones</strong></p>
        <p>
          Nos reservamos el derecho de modificar estos términos en cualquier momento. 
          Las actualizaciones serán notificadas en el sitio.
        </p>

        <p><strong>7. Contacto</strong></p>
        <p>
          Si tenés preguntas, contactanos a: soporte@reservastrenque.com
        </p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Cerrar
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default TerminosModal;
