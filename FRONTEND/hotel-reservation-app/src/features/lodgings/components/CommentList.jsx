import React from "react";

const mockComments = [
  {
    usuario: "Juan Pérez",
    fecha: "2025-06-01",
    valoracion: 5,
    comentario: "Hermoso lugar, muy tranquilo y cómodo. Volvería sin dudas.",
  },
  {
    usuario: "María González",
    fecha: "2025-05-28",
    valoracion: 4,
    comentario: "Excelente atención y limpieza. La cabaña es tal cual se muestra.",
  },
  {
    usuario: "Carlos Díaz",
    fecha: "2025-04-12",
    valoracion: 3,
    comentario: "La ubicación es ideal para descansar. Muy recomendable.",
  },
];

export default function CommentList() {
  return (
    <div className="mt-5">
      <h4 className="text-dark mb-3">Comentarios de huéspedes</h4>
      <ul className="list-group">
        {mockComments.map((c, i) => (
          <li key={i} className="list-group-item">
            <p className="mb-1 text-warning">
              {"★".repeat(c.valoracion)}
              {"☆".repeat(5 - c.valoracion)}
            </p>
            <strong>{c.usuario}</strong>{" "}
            <small className="text-muted ms-2">{c.fecha}</small>
            <p className="mb-0">{c.comentario}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
