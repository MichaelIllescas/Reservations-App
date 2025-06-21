import React from "react";
import { createPortal } from "react-dom";
import "./styless.css";

export default function ConfirmationModal({
  title,
  message,
  onConfirm,
  onClose,
  isOpen,
}) {
  if (!isOpen) return null;

  return createPortal(
    <>
      <div className="modal-backdrop show d-block"></div>
      <div className="modal show d-block" tabIndex="10" role="dialog">
        <div className="modal-dialog modal-dialog-centered" role="document">
          <div className="modal-content ">
            <div className="modal-header">
              <h5 className="modal-title">{title}</h5>
              <button
                type="button"
                className="btn-close"
                onClick={onClose}
              ></button>
            </div>

            <div className="modal-body">
              <p>{message}</p>
            </div>

            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={onClose}
              >
                Cancelar
              </button>
              <button
                type="button"
                className="btn btn-danger"
                onClick={onConfirm}
              >
                Confirmar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>,
    document.body
  );
}
