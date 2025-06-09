import Header from "../Components/Header/Header";
import "./MainLayout.css";
import Footer from "../Components/Footer/Footer";

function MainLayout({ children }) {
  return (
    <>
      <Header />
      <main className="main">{children}</main>
      <Footer />
    </>
  );
}

export default MainLayout;
