
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getPendingTasks } from "../api/taskService";
import "./PagesStyles.css";

const Recordatorios = () => {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    // --- MODO DESARROLLO: MOCK DATA ---
    const mockTasks = Array.from({ length: 50 }, (_, i) => ({
      id: i,
      contactName: `Cliente Demo ${i + 1}`,
      message: `Seguimiento de propuesta comercial #${100 + i}`,
      reminderAt: new Date(Date.now() + i * 86400000).toLocaleDateString(),
    }));

    setTimeout(() => {
      setTasks(mockTasks);
      setLoading(false);
    }, 500);

    /* --- MODO PRODUCCION: API REAL ---
    const fetchTasks = async () => {
      try {
        const data = await getPendingTasks();
        setTasks(data.slice(0, 50));
      } catch (error) {
        console.error(error);
        alert("Failed to load reminders");
      } finally {
        setLoading(false);
      }
    };
    fetchTasks();
    ---------------------------------- */
  }, []);

  return (
    <div className="page-container">
      <header className="page-header">
        <h2>Recordatorios</h2>
        <button
          className="btn-primary"
          onClick={() => navigate("/recordatorios/nuevo")}
        >
          + Nueva Tarea
        </button>
      </header>

      <div className="table-container">
        {loading ? (
          <p>Cargando tareas...</p>
        ) : (
          <table className="corporate-table">
            <thead>
              <tr>
                <th style={{ width: "25%" }}>Nombre</th>
                <th style={{ width: "55%" }}>Mensaje</th>
                <th style={{ width: "20%" }}>Fecha</th>
              </tr>
            </thead>
            <tbody>
              {tasks.map((task, index) => (
                <tr key={index}>
                  <td>
                    <strong>{task.contactName}</strong>
                  </td>
                  <td>{task.message}</td>
                  <td>{task.reminderAt}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default Recordatorios;

