import { useParams } from "react-router-dom";
import BackArrowButton from "../../../Components/BackArrow/BackArrowButton";
import AdminNavbar from "../../admin/components/AdminNavbar";
import EditLodgingForm from "../components/EditLodgingForm";

export default function EditLodgingPage() {
  const { id } = useParams();
  return (
    <main className="min-h-screen ">
      <AdminNavbar />
      <EditLodgingForm lodgingId={id} />
      <BackArrowButton />
    </main>
  );
}

