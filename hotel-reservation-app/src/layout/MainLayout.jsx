import React from "react";
import Header from "../Components/Header/Header";
import "./MainLayout.css";

function MainLayout({ children }) {
  return (
    <>
      <Header />
      <main className="main">{children}</main>
    </>
  );
}

export default MainLayout;
