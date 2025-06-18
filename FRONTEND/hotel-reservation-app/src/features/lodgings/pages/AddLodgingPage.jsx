import BackArrowButton from '../../../Components/BackArrow/BackArrowButton';
import AdminNavbar from '../../admin/components/AdminNavbar';
import ProductUpload from '../components/ProductUpload';

export default function AddLodgingPage() {
  return (
    <main className="min-h-screen ">
            <AdminNavbar />
      
      <ProductUpload />
      <BackArrowButton/>
    </main>
  );
}
