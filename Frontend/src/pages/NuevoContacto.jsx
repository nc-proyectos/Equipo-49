import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { saveContact } from "../api/contactService";
import "./PagesStyles.css";

const NuevoContacto = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstname: "",
    lastname: "",
    email: "",
    phone: "",
    status: "",
    source: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await saveContact(formData);
      alert("Contacto creado exitosamente.");
      navigate("/contactos");
    } catch (error) {
      alert("Failed to create contact: " + error.message);
    }
  };

  const handleClear = () => {
    setFormData({
      firstname: "",
      lastname: "",
      email: "",
      phone: "",
      status: "",
      source: "",
    });
  };

  return (
    <div className="page-container">
      <h2>Nuevo Contacto</h2>
      <div className="form-wrapper">
        <form onSubmit={handleSubmit} className="corporate-form">
          <div className="form-group">
            <label>Nombre</label>
            <input
              required
              name="firstname"
              value={formData.firstname}
              onChange={handleChange}
              placeholder="Ej. Juan"
            />
          </div>
          <div className="form-group">
            <label>Apellido</label>
            <input
              required
              name="lastname"
              value={formData.lastname}
              onChange={handleChange}
              placeholder="Ej. Pérez"
            />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input
              required
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="juan@email.com"
            />
          </div>
          <div className="form-group">
            <label>Teléfono</label>
            <input
              required
              name="phone"
              value={formData.phone}
              onChange={handleChange}
              placeholder="+54..."
            />
          </div>
          <div className="form-group">
            <label>Segmento</label>
            <select
              name="status"
              value={formData.status}
              onChange={handleChange}
              required
            >
              <option value="">Seleccione...</option>
              <option value="Prospecto">Prospecto</option>
              <option value="Cliente">Cliente</option>
            </select>
          </div>
          <div className="form-group">
            <label>Origen</label>
            <input
              required
              name="source"
              value={formData.source}
              onChange={handleChange}
              placeholder="Ej. Linkedin"
            />
          </div>

          <div className="form-actions">
            <button
              type="button"
              onClick={handleClear}
              className="btn-secondary"
            >
              Limpiar
            </button>
            <button type="submit" className="btn-primary">
              Enviar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default NuevoContacto;
