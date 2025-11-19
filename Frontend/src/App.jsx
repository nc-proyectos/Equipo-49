
import { Routes, Route, Router, BrowserRouter } from "react-router-dom";

import Dashboard from "./pages/Dashboard";
import Contactos from "./pages/Contactos";
import Whatsapp from "./pages/Whatsapp";
import Correos from "./pages/Correos";
import Segmentacion from "./pages/Segmentacion";
import Recordatorios from "./pages/Recordatorios";
import Analiticas from "./pages/Analiticas";
import Sidebar from "./components/layouts/Sidebar";

export default function App() {
  return (
    <div>

    <Sidebar/>

    <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/contactos" element={<Contactos />} />
        <Route path="/whatsapp" element={<Whatsapp />} />
        <Route path="/correos" element={<Correos />} />
        <Route path="/segmentacion" element={<Segmentacion />} />
        <Route path="/recordatorios" element={<Recordatorios />} />
        <Route path="/analiticas" element={<Analiticas />} />
    </Routes>
    </div>

  );
}


