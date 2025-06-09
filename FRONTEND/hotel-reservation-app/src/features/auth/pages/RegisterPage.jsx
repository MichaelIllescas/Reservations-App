import RegisterForm from "../components/RegisterForm";
import { useRegisterUser } from "../hooks/useRegisterUser";

function RegisterPage() {
      const { handleRegister, isLoading } = useRegisterUser();
  
  return (
    <div className="container d-flex align-items-center justify-content-center" style={{ minHeight: "100vh" }}>
      <div className="w-100" style={{ maxWidth: "500px" }}>
        <RegisterForm onSubmit={handleRegister} />
      </div>
    </div>
  );
}

export default RegisterPage;
