import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { saveTask } from "../api/taskService";
import "./PagesStyles.css";

const NuevaTarea = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    contactName: "",
    message: "",
    reminderAt: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await saveTask(formData);
      alert("Tarea creada exitosamente.");
      navigate("/recordatorios");
    } catch (error) {
      alert("Failed to create task: " + error.message);
    }
  };

  const handleClear = () => {
    setFormData({ contactName: "", message: "", reminderAt: "" });
  };

  return (
    <div className="page-container">
      <h2>Nueva Tarea</h2>
      <div className="form-wrapper">
        <form onSubmit={handleSubmit} className="corporate-form">
          <div className="form-group">
            <label>Nombre del Contacto</label>
            <input
              required
              name="contactName"
              value={formData.contactName}
              onChange={handleChange}
              placeholder="Ej. Empresa SA"
            />
          </div>
          <div className="form-group">
            <label>Mensaje</label>
            <textarea
              required
              name="message"
              value={formData.message}
              onChange={handleChange}
              rows="4"
              placeholder="Detalle de la tarea..."
            />
          </div>
          <div className="form-group">
            <label>Fecha de Recordatorio</label>
            <input
              required
              type="datetime-local"
              name="reminderAt"
              value={formData.reminderAt}
              onChange={handleChange}
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

export default NuevaTarea;
