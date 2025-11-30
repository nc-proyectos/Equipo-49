import React from "react";
import "./SidebarDashboard.css";

const Link = ({ to, className, children, onClick }) => (
  <a href={to} className={className} onClick={onClick}>
    {children}
  </a>
);

const navItems = [
  { name: "Dashboard", to: "/" },
  { name: "Contactos", to: "/contactos" },
  { name: "Whatsapp", to: "/whatsapp" },
  { name: "Correos", to: "/correos" },
  { name: "Segmentación", to: "/segmentacion" },
  { name: "Recordatorios", to: "/recordatorios" },
  { name: "Analíticas", to: "/analiticas" },
];

export function Sidebar({ activePath, setActivePath }) {
  return (
    <div className="sidebar">
      <h2>SmartCRM</h2>
      <nav className="nav-container">
        {navItems.map((item) => (
          <Link
            key={item.to}
            to={item.to}
            className={`nav-item ${item.to === activePath ? "active" : ""}`}
            onClick={(e) => {
              e.preventDefault();
              window.history.pushState(null, "", item.to);
              setActivePath(item.to);
            }}
          >
            {item.name}
          </Link>
        ))}
      </nav>
    </div>
  );
}

export default function MainSection() {
  const [activePath, setActivePath] = React.useState("/");

  return (
    <>
      <div className="dashboard-container">
        <Sidebar activePath={activePath} setActivePath={setActivePath} />
        <main className="main-content">
          <div className="content-box">
            <h1>Bienvenido al Dashboard del Equipo 49</h1>
            <p>
              Te encuentras en → <strong>{activePath}</strong>
            </p>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin
              ullamcorper arcu sit amet imperdiet lacinia.
            </p>
            <p>
              Suspendisse potenti. Integer eleifend nisi ac quam condimentum, a
              suscipit mauris euismod. Maecenas nec vulputate eros, pretium
              dignissim urna.
            </p>
            {/* Contenido de prueba para scroll */}
            <div
              style={{
                height: "1000px",
                marginTop: "30px",
                borderTop: "1px solid #ccc",
                paddingTop: "20px",
              }}
            >
              <p>
                Nunc vehicula risus ut nibh malesuada laoreet. Curabitur
                efficitur tellus sit amet tristique porttitor. Praesent a
                porttitor felis.
              </p>
            </div>
          </div>
        </main>
      </div>
    </>
  );
}
