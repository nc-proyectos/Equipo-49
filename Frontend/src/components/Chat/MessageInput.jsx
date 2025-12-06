import { useState } from "react";
import { sendMessage } from "../../api/whatsappApi";

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
    <div className="p-3 bg-white border-top">
      <div className="d-flex gap-3 align-items-end">
        <textarea
          className="form-control"
          rows="2"
          placeholder="Escribe un mensaje..."
          value={value}
          onChange={(e) => setValue(e.target.value)}
        />

        <button
          className="btn btn-primary px-4"
          onClick={handleSend}
        >
          Enviar
        </button>
      </div>
    </div>
  );
}
