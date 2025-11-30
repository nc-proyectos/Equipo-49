import React from "react";
import "./SidebarDashboard.css";

async function getContactsCountSimulation() {
  await new Promise((resolve) => setTimeout(resolve, 1000));
  return 49;
}

async function getWhatsappCountSimulation() {
  await new Promise((resolve) => setTimeout(resolve, 1500));
  return 5;
}

async function getEmailsCountSimulation() {
  await new Promise((resolve) => setTimeout(resolve, 2000));
  return 15;
}

async function getRemindersCountSimulation() {
  await new Promise((resolve) => setTimeout(resolve, 500));
  return 10;
}

const navItemsBase = [
  { name: "Dashboard", to: "/", count: 0, key: "dashboard" },
  { name: "Contactos", to: "/contactos", count: 0, key: "contactos" },
  { name: "Whatsapp", to: "/whatsapp", count: 0, key: "whatsapp" },
  { name: "Correos", to: "/correos", count: 0, key: "correos" },
  { name: "Segmentación", to: "/segmentacion", count: 0, key: "segmentacion" },
  {
    name: "Recordatorios",
    to: "/recordatorios",
    count: 0,
    key: "recordatorios",
  },
  { name: "Analíticas", to: "/analiticas", count: 0, key: "analiticas" },
];

const getActiveName = (path) => {
  const activeItem = navItemsBase.find((item) => item.to === path);
  return activeItem ? activeItem.name : path;
};

const Link = ({ to, className, children, onClick, count }) => (
  <a href={to} className={className} onClick={onClick}>
    <span>{children}</span>
    {count > 0 && <div className="notification-badge">{count}</div>}
  </a>
);

export function Sidebar({ activePath, setActivePath, dynamicNavItems }) {
  return (
    <div className="sidebar">
      <h2>SmartCRM</h2>
      <nav className="nav-container">
        {dynamicNavItems.map((item) => (
          <Link
            key={item.to}
            to={item.to}
            count={item.count}
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
  const [activePath, setActivePath] = React.useState(window.location.pathname);
  const [contactsCount, setContactsCount] = React.useState(0);
  const [whatsappCount, setWhatsappCount] = React.useState(0);
  const [emailsCount, setEmailsCount] = React.useState(0);
  const [remindersCount, setRemindersCount] = React.useState(0);
  const [isLoading, setIsLoading] = React.useState(true);

  React.useEffect(() => {
    const handlePopState = () => setActivePath(window.location.pathname);
    window.addEventListener("popstate", handlePopState);
    return () => window.removeEventListener("popstate", handlePopState);
  }, []);

  React.useEffect(() => {
    const fetchCounts = async () => {
      setIsLoading(true);
      try {
        const [contacts, whatsapp, emails, reminders] = await Promise.all([
          getContactsCountSimulation(),
          getWhatsappCountSimulation(),
          getEmailsCountSimulation(),
          getRemindersCountSimulation(),
        ]);

        setContactsCount(contacts);
        setWhatsappCount(whatsapp);
        setEmailsCount(emails);
        setRemindersCount(reminders);
      } catch (error) {
        console.error("Error: No se pudo cargar uno o más conteos", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchCounts();
  }, []);

  const dynamicNavItems = navItemsBase.map((item) => {
    switch (item.key) {
      case "contactos":
        return { ...item, count: contactsCount };
      case "whatsapp":
        return { ...item, count: whatsappCount };
      case "correos":
        return { ...item, count: emailsCount };
      case "recordatorios":
        return { ...item, count: remindersCount };
      default:
        return item;
    }
  });

  const activeName = getActiveName(activePath);

  return (
    <>
      <div className="dashboard-container">
        <Sidebar
          activePath={activePath}
          setActivePath={setActivePath}
          dynamicNavItems={dynamicNavItems}
        />

        <main className="main-content">
          <div className="content-box">
            <h1>Bienvenido al Dashboard del Equipo 49</h1>
            <p>
              Te encuentras en → <strong>{activeName}</strong>
            </p>

            {isLoading ? (
              <div
                className="flex items-center space-x-2 loading-text"
                style={{
                  display: "flex",
                  alignItems: "center",
                  gap: "10px",
                  marginTop: "15px",
                }}
              >
                <div className="spinner"></div>
                <p>Cargando conteos dinámicos de todos los módulos...</p>
              </div>
            ) : (
              <div
                style={{
                  fontWeight: "600",
                  marginTop: "15px",
                  padding: "15px",
                  backgroundColor: "#e8f6f3",
                  borderRadius: "8px",
                  border: "1px solid #d0e9e9",
                }}
              >
                <p style={{ color: "#27ae60", marginBottom: "8px" }}>
                  Si falla la llamada a la API, se muestra la palabra TEST junto
                  a un número hardcodeado.
                </p>
                <ul className="list-disc ml-5 text-sm">
                  <li>
                    Contactos (Total):{" "}
                    <span style={{ color: "#c0392b", fontWeight: 700 }}>
                      {contactsCount} {"TEST"}
                    </span>
                  </li>
                  <li>
                    Whatsapp (Mensajes Sin Leer):{" "}
                    <span style={{ color: "#c0392b", fontWeight: 700 }}>
                      {whatsappCount} {"TEST"}
                    </span>
                  </li>
                  <li>
                    Correos (Emails Sin Leer):{" "}
                    <span style={{ color: "#c0392b", fontWeight: 700 }}>
                      {emailsCount} {"TEST"}
                    </span>
                  </li>
                  <li>
                    Recordatorios (Recordatorios Activos):{" "}
                    <span style={{ color: "#c0392b", fontWeight: 700 }}>
                      {remindersCount} {"TEST"}
                    </span>
                  </li>
                </ul>
              </div>
            )}

            <p style={{ marginTop: "20px" }}>
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
