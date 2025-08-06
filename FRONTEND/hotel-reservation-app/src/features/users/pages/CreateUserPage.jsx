import AdminNavbar from "../../admin/components/AdminNavbar";
import BackArrowButton from "../../../Components/BackArrow/BackArrowButton";
import UserRegisterForm from "../components/UserRegisterForm";

export default function CreateUserPage() {
  return (
    <main className="min-h-screen">
      <AdminNavbar />
      <div className="container d-flex align-items-center justify-content-center" style={{ minHeight: "80vh" }}>
        <div className="w-100" style={{ maxWidth: "600px" }}>
          <UserRegisterForm />
        </div>
      </div>
      <BackArrowButton />
    </main>
  );
}

