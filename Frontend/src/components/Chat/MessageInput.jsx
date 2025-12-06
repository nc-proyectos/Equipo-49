import { useState } from "react";
import { sendMessage } from "../../api/whatsappApi";
import "../Chat/chat.css"

export default function MessageInput({ onSend }) {
  const [value, setValue] = useState("");

  const handleSend = async () => {
    if (!value.trim()) return;

    // Esto va al backend → WhatsApp Cloud API
    // await sendMessage("PHONE_NUMBER", value);

    onSend(value);
    setValue("");
  };

  return (
     <div className="chat-input-container">
      <div className="chat-input-wrapper">
        <textarea
          className="chat-textarea"
          rows="2"
          placeholder="Escribe un mensaje..."
          value={value}
          onChange={(e) => setValue(e.target.value)}
        />

        <button className="chat-send-btn" onClick={handleSend}>
          Enviar
        </button>
      </div>
    </div>
  );
}
