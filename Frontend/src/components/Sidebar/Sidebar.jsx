import { NavLink } from "react-router-dom";
import "./sidebar.css"; // estilos personalizados

export default function Sidebar() {
  return (
    <div className="sidebar d-flex flex-column p-3">
      <h2 className="fs-4 mb-4">CRM</h2>

      <ul className="nav nav-pills flex-column">
        <li className="nav-item mb-2">
          <NavLink to="/" end className="nav-link">
            Dashboard
          </NavLink>
        </li>

        <li className="nav-item mb-2">
          <NavLink to="/contactos" className="nav-link">
            Contactos
          </NavLink>
        </li>

        <li className="nav-item mb-2">
          <NavLink to="/whatsapp" className="nav-link">
            Whatsapp
          </NavLink>
        </li>

        <li className="nav-item mb-2">
          <NavLink to="/correos" className="nav-link">
            Correos
          </NavLink>
        </li>

        <li className="nav-item mb-2">
          <NavLink to="/segmentacion" className="nav-link">
            Segmentación
          </NavLink>
        </li>

        <li className="nav-item mb-2">
          <NavLink to="/recordatorios" className="nav-link">
            Recordatorios
          </NavLink>
        </li>

        <li className="nav-item mb-2">
          <NavLink to="/analiticas" className="nav-link">
            Analíticas
          </NavLink>
        </li>
      </ul>
    </div>
  );
}

