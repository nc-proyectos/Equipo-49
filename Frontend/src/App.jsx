<<<<<<< HEAD
import { Routes, Route } from "react-router-dom";
import Layout from "./components/layouts/Layout";
import Dashboard from "./pages/Dashboard";
import Contactos from "./pages/Contactos";
import NuevoContacto from "./pages/NuevoContacto";
import Whatsapp from "./pages/Whatsapp";
import Correos from "./pages/Correos";
import Segmentacion from "./pages/Segmentacion";
import Recordatorios from "./pages/Recordatorios";
import NuevaTarea from "./pages/NuevaTarea";
import Analiticas from "./pages/Analiticas";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Dashboard />} />
        <Route path="contactos" element={<Contactos />} />
        <Route path="contactos/nuevo" element={<NuevoContacto />} />
        <Route path="whatsapp" element={<Whatsapp />} />
        <Route path="correos" element={<Correos />} />
        <Route path="segmentacion" element={<Segmentacion />} />
        <Route path="recordatorios" element={<Recordatorios />} />
        <Route path="recordatorios/nuevo" element={<NuevaTarea />} />
        <Route path="analiticas" element={<Analiticas />} />
      </Route>
    </Routes>
  );
}
=======


import { BrowserRouter, Routes, Route, Router } from "react-router-dom";
import Sidebar from "./components/Sidebar/Sidebar";

import Dashboard from "./pages/Dashboard";
import Contactos from "./pages/Contactos";
import Whatsapp from "./pages/Whatsapp";
import Correos from "./pages/correo/Correos";
import Segmentacion from "./pages/Segmentacion";
import Recordatorios from "./pages/Recordatorios";
import Analiticas from "./pages/Analiticas";
import EnviarMensaje from "./pages/correo/enviarMensaje";
import BandejaDeEntrada from "./pages/correo/BandejaDeEntrada";


export default function App() {
  return (
  
      <div className="d-flex">
        
        {/* SIDEBAR */}
        <Sidebar />

        {/* CONTENIDO A LA DERECHA */}
        <div className="flex-grow-1 p-4">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/enviarMensaje" element={<EnviarMensaje/>}/>
            <Route path="/bandejaDeEntrada" element={<BandejaDeEntrada/>}></Route>
            <Route path="/contactos" element={<Contactos />} />
            <Route path="/whatsapp" element={<Whatsapp />} />
            <Route path="/correos" element={<Correos />} />
            <Route path="/segmentacion" element={<Segmentacion />} />
            <Route path="/recordatorios" element={<Recordatorios />} />
            <Route path="/analiticas" element={<Analiticas/>} />
          </Routes>
        </div>

      </div>
    )
}
//<Route path="/enviarMensajes" element={<EnviarMensaje/>}/>


>>>>>>> origin/ramaDeLautaro
