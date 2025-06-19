import useLodgingList from "../hooks/useLodgingList";
const LodgingList = () => {
  const { lodgings, loading, error } = useLodgingList();

  if (loading) return <p>Cargando alojamientos...</p>;
  if (error) return <p>Error al cargar alojamientos.</p>;

  return (
    <div className="table-responsive">
      <table className="table table-bordered">
        <thead className="thead-light">
          <tr>
            <th>Imagen</th>
            <th>Título</th>
            <th>Ubicación</th>
            <th>Tipo</th>
            <th>Precio</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {lodgings.map((a) => (
            <tr key={a.id}>
              <td>
                <div
                  className="rounded-circle bg-cover"
                  style={{
                    width: 40,
                    height: 40,
                    backgroundSize: "cover",
                    backgroundImage: `url(${a.imageUrls?.[0] || "/default.jpg"})`,
                  }}
                />
              </td>
              <td>{a.name}</td>
              <td className="text-muted">
                {a.address?.city}, {a.address?.province}
              </td>
              <td className="text-muted">{a.lodgingType}</td>
              <td className="text-muted">${a.dailyPrice}/noche</td>
              <td>
                <span className="text-primary font-weight-bold">Editar</span> |{" "}
                <span className="text-danger font-weight-bold">Eliminar</span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LodgingList;
