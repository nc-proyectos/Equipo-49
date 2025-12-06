<<<<<<< HEAD
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllContacts } from "../api/contactService";
import "./PagesStyles.css";

const Contactos = () => {
  const [contacts, setContacts] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    // --- MODO DESARROLLO ---
    const mockContacts = Array.from({ length: 50 }, (_, i) => ({
      id: i,
      firstname: `Nombre${i + 1}`,
      lastname: `Apellido${i + 1}`,
      email: `usuario${i + 1}@empresa.com`,
      phone: `+54 9 11 1234-${5678 + i}`,
      status: i % 3 === 0 ? "Cliente" : "Prospecto",
      source: i % 2 === 0 ? "Web" : "Referido",
    }));

    setTimeout(() => {
      setContacts(mockContacts);
      setLoading(false);
    }, 500);

    /* --- MODO PRODUCCION ---
    const fetchContacts = async () => {
      try {
        const data = await getAllContacts();
        setContacts(data.slice(0, 50)); 
      } catch (error) {
        console.error(error);
        alert("Failed to load contacts");
      } finally {
        setLoading(false);
      }
    };
    fetchContacts();
    -------------------------------------------------------------------------- */
  }, []);

  return (
    <div className="page-container">
      <header className="page-header">
        <h2>Contactos</h2>
        <button
          className="btn-primary"
          onClick={() => navigate("/contactos/nuevo")}
        >
          + Nuevo Contacto
        </button>
      </header>

      <div className="table-container">
        {loading ? (
          <p>Cargando contactos...</p>
        ) : (
          <table className="corporate-table">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Email</th>
                <th>Teléfono</th>
                <th>Segmento</th>
                <th>Origen</th>
              </tr>
            </thead>
            <tbody>
              {contacts.map((contact, index) => (
                <tr key={index}>
                  <td>
                    <strong>
                      {contact.firstname} {contact.lastname}
                    </strong>
                  </td>
                  <td>{contact.email}</td>
                  <td>{contact.phone}</td>
                  <td>
                    <span
                      className={`badge badge-${contact.status.toLowerCase()}`}
                    >
                      {contact.status}
                    </span>
                  </td>
                  <td>{contact.source}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default Contactos;
=======
import React from 'react'

const Contactos = () => {
  return (
    <div>Contactos</div>
  )
}

export default Contactos
>>>>>>> origin/ramaDeLautaro
