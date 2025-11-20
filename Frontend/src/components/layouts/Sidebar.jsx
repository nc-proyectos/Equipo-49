import { Link } from "react-router-dom"
import Dashboard from "../../pages/Dashboard"

export function Sidebar () {

  return (
    <>
      <nav>
        <Link to="/" className="nav-item"></Link>
        <Link to="/contactos" className="nav-item"></Link>
        <Link to="/whatsapp" className="nav-item"></Link>
        <Link to="/correos" className="nav-item"></Link>
        <Link to="/segmentacion" className="nav-item"></Link>
        <Link to="/recordatorios" className="nav-item"></Link>
        <Link to="/analiticas" className="nav-item"></Link>
      </nav>
  </>
  )

}

export default Sidebar






















