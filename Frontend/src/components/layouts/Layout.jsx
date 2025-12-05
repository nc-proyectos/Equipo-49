import React from "react";
import { Outlet } from "react-router-dom";
import SidebarDashboard from "./SidebarDashboard";

const Layout = () => {
  return (
    <div
      className="dashboard-container"
      style={{ display: "flex", height: "100vh", width: "100vw" }}
    >
      <SidebarDashboard />
      <main
        className="main-content"
        style={{
          flex: 1,
          padding: "2rem",
          overflowY: "auto",
          backgroundColor: "#f4f7f6",
        }}
      >
        <Outlet />
      </main>
    </div>
  );
};

export default Layout;
