import React from "react";

const LodgingList = () => {
  const alojamientos = [
    {
      titulo: "Apartamento en el centro",
      ubicacion: "Trenque Lauquen, Buenos Aires",
      tipo: "Apartamento",
      precio: "$150/noche",
      imagen:
        "https://lh3.googleusercontent.com/aida-public/AB6AXuDTCcNWFoDNrHbW1oCO2dCgPHTA740hSOijJuWXbgJ3ooPUPFySyqfuFNkMkHcW4BpIDsYpH5DBMkppLpQ0SeBtxwCkyGN2DDe5LbPtj1mL1y92KxyRytpE7LabsX8Rf8XUorUIG7FxOA-ohWJFjLOamYMLGJ64XtwKlkW2OA_csTD9jCZA-kfi4W-V5N3xrdhlAan1bZ575XvXRmsmkfw-hDRqxw4zuT3PqW7z2Z9i7kaC_ZGbDm9FWRsPnDI6xJOc288Z5TX7Rh-4",
    },
    {
      titulo: "Casa de campo",
      ubicacion: "Trenque Lauquen, Buenos Aires",
      tipo: "Casa",
      precio: "$200/noche",
      imagen:
        "https://lh3.googleusercontent.com/aida-public/AB6AXuDp6XgJ24yp1IGedErfdPwlQvAUmLjCDnIMLKfPhOQsICvsT8FrUd4rn6AselJBztOThGRA1-5tKPrfm4GmCV60WsuljZsQrwE0grjSV7Il-L3IirlS6YIUGdE22ibGAv9yS2PDcFjCpyRDAQw7eEXnAP30uSz6Ka4MSrfcmBbCan8vQIhH6iG08g8b4CycenxZMkCy_0sQUXo1MuE-KrhWjCaqSuqfhjwdJZtbB-RlQ0JANlcmjyZ985U8V1ao3Wwu4LVMbMevhTcq",
    },
    // ...otros alojamientos
  ];

  return (
    <div className="table-responsive">
      <table className="table table-bordered">
        <thead className="thead-light">
          <tr>
            <th scope="col">Imagen</th>
            <th scope="col">Título</th>
            <th scope="col">Ubicación</th>
            <th scope="col">Tipo</th>
            <th scope="col">Precio</th>
            <th scope="col">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {alojamientos.map((a, index) => (
            <tr key={index}>
              <td>
                <div
                  className="rounded-circle bg-cover"
                  style={{
                    width: 40,
                    height: 40,
                    backgroundSize: "cover",
                    backgroundImage: `url(${a.imagen})`,
                  }}
                ></div>
              </td>
              <td>{a.titulo}</td>
              <td className="text-muted">{a.ubicacion}</td>
              <td className="text-muted">{a.tipo}</td>
              <td className="text-muted">{a.precio}</td>
              <td>
                <span className="text-primary font-weight-bold">Editar</span> |
                <span className="text-danger font-weight-bold"> Eliminar</span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LodgingList;
