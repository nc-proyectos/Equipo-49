

import { BrowserRouter, Routes, Route, Router } from "react-router-dom";
import Sidebar from "./components/Sidebar/Sidebar";

import Dashboard from "./pages/Dashboard";
import Contactos from "./pages/Contactos";
import Whatsapp from "./pages/Whatsapp";
import Correos from "./pages/Correos";
import Segmentacion from "./pages/Segmentacion";
import Recordatorios from "./pages/Recordatorios";
import Analiticas from "./pages/Analiticas";

export default function App() {
  return (
  
      <div className="d-flex">
        
        {/* SIDEBAR */}
        <Sidebar />

        {/* CONTENIDO A LA DERECHA */}
        <div className="flex-grow-1 p-4">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/contactos" element={<Contactos />} />
            <Route path="/whatsapp" element={<Whatsapp />} />
            <Route path="/correos" element={<Correos />} />
            <Route path="/segmentacion" element={<Segmentacion />} />
            <Route path="/recordatorios" element={<Recordatorios />} />
            <Route path="/analiticas" element={<Analiticas/>} />
          </Routes>
        </div>

      </div>
   
  );
}



