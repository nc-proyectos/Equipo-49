import { Link } from "react-router-dom";


export default function Sidebar() {
  return (
    <div className="sidebar">
      <h2>CRM</h2>
      <nav>
        <Link to="/" className="nav-item">Dashboard</Link>
        <Link to="/contactos" className="nav-item">Contactos</Link>
        <Link to="/whatsapp" className="nav-item">Whatsapp</Link>
        <Link to="/correos" className="nav-item">Correos</Link>
        <Link to="/segmentacion" className="nav-item">Segmentación</Link>
        <Link to="/recordatorios" className="nav-item">Recordatorios</Link>
        <Link to="/analiticas" className="nav-item">Analíticas</Link>
      </nav>
    </div>
  );
}
