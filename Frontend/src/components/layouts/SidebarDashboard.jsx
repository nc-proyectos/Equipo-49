import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { login } from "../../api/authService.js";
import { getContactsCount } from "../../api/contactService.js";
import { getRemindersCount } from "../../api/taskService.js";
import "./SidebarDashboard.css";

const getWhatsappCountSimulation = async () =>
  new Promise((r) => setTimeout(() => r(5), 1500));
const getEmailsCountSimulation = async () =>
  new Promise((r) => setTimeout(() => r(15), 2000));

export default function SidebarDashboard() {
  const [counts, setCounts] = useState({
    contactos: 0,
    whatsapp: 0,
    correos: 0,
    recordatorios: 0,
  });

  useEffect(() => {
    const fetchCounts = async () => {
      try {
        await login("admin", "112233");
        const [contacts, whatsapp, emails, reminders] = await Promise.all([
          getContactsCount(),
          getWhatsappCountSimulation(),
          getEmailsCountSimulation(),
          getRemindersCount(),
        ]);
        setCounts({ contacts, whatsapp, emails, reminders });
      } catch (error) {
        console.error("Error loading counters", error);
        setCounts({ contacts: 64, whatsapp: 5, emails: 15, reminders: 10 });
      }
    };
    fetchCounts();
  }, []);

  const navItems = [
    { name: "Dashboard", to: "/", count: 0 },
    { name: "Contactos", to: "/contactos", count: counts.contacts },
    { name: "Whatsapp", to: "/whatsapp", count: counts.whatsapp },
    { name: "Correos", to: "/correos", count: counts.emails },
    { name: "Segmentación", to: "/segmentacion", count: 0 },
    { name: "Recordatorios", to: "/recordatorios", count: counts.reminders },
    { name: "Analíticas", to: "/analiticas", count: 0 },
  ];

  return (
    <div className="sidebar">
      <h2>SmartCRM</h2>
      <nav className="nav-container">
        {navItems.map((item) => (
          <NavLink
            key={item.to}
            to={item.to}
            className={({ isActive }) => `nav-item ${isActive ? "active" : ""}`}
          >
            <span>{item.name}</span>
            {item.count > 0 && (
              <div className="notification-badge">{item.count}</div>
            )}
          </NavLink>
        ))}
      </nav>
    </div>
  );
}
