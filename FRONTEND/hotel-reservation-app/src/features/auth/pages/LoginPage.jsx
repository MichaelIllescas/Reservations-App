import LoginForm from "../components/LoginForm";

function LoginPage() {
  return (
    <div className="container d-flex mt-5 justify-content-center" >
      <div className="w-100" style={{ maxWidth: "400px"}}>
        <LoginForm />
      </div>
    </div>
  );
}

export default LoginPage;
