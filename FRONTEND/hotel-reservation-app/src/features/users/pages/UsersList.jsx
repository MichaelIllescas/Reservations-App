import { useMemo, useEffect, useState } from "react";
import useUsers from "../hooks/useUsers";
import DataTable from '../../../Components/Tables/DataTable';
import UserEditModal from "../components/UserEditModal";

const UsersList = () => {
  const { users, fetchUsers, toggleAdmin } = useUsers();
  const [selectedUser, setSelectedUser] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const handleOpenEdit = (user) => {
    setSelectedUser(user);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedUser(null);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const columns = useMemo(
    () => [
      { Header: "ID", accessor: "id" },
      { Header: "NOMBRE", accessor: "firstName" },
      { Header: "APELLIDO", accessor: "lastName" },
      { Header: "EMAIL", accessor: "email" },
      { Header: "ROL", accessor: "role" },
      {
        Header: "ACCIONES",
        accessor: "actions",
        Cell: ({ row }) => (
          <div className="d-flex ">
            <button
              className="btn btn-primary btn-sm "
              style={{ width: "40px", margin: "0 auto", borderRadius: "150px" }}
              onClick={() => handleOpenEdit(row.original)}
            >
              ✏️
            </button>
            <button
              className="btn btn-warning btn-sm"
              style={{ width: "40px", margin: "0 auto", borderRadius: "150px" }}
            >
              {row.original.enabled ? "✅" : "🚫"}
            </button>
            <button
              className="btn btn-secondary btn-sm"
              style={{ width: "40px", margin: "0 auto", borderRadius: "150px" }}
              onClick={() => toggleAdmin(row.original.id)}
            >
              {row.original.role === "ADMIN" ? "👑" : "➕"}
            </button>
          </div>
        ),
      },
    ],
    [users, toggleAdmin]
  );

  return (
    <div className="mt-3 pt-1">
      <div
        className="container card mt-5 pt-5 table-responsive table"
        data-aos="fade-left"
      >
        <h2>Usuarios Registrados</h2>
        <DataTable columns={columns} data={users} />
      </div>
      <UserEditModal
        show={showModal}
        onClose={handleCloseModal}
        user={selectedUser}
        onUpdated={fetchUsers}
      />
    </div>
  );
};

export default UsersList;
