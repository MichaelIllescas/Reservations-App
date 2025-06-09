import RegisterForm from "../components/RegisterForm";

function RegisterPage() {
  return (
    <div className="container d-flex align-items-center justify-content-center" style={{ minHeight: "100vh" }}>
      <div className="w-100" style={{ maxWidth: "500px" }}>
        <RegisterForm />
      </div>
    </div>
  );
}

export default RegisterPage;
