import { useState, useEffect } from "react";
import useSSE from "../../hooks/useSse";
import MessageList from "./MessageList";
import MessageInput from "./MessageInput";

export default function Chat() {
  const [messages, setMessages] = useState([]);

  // Escucha SSE del backend
  // const incoming = useSSE("http://localhost:8080/sse/messages");

  // // Cuando llega mensaje desde WhatsApp Cloud API (vía backend)
  // useEffect(() => {
  //   if (incoming) {
  //     setMessages((prev) => [
  //       ...prev,
  //       { text: incoming.text.body, fromMe: false }
  //     ]);
  //   }
  // }, [incoming]);

  // Cuando el usuario envía un mensaje
  const handleSend = (text) => {
    setMessages((prev) => [...prev, { text, fromMe: true }]);
  };

  return (
        <div className="container-fluid">
      <div className="card shadow-sm" style={{ height: "75vh" }}>
        <div className="card-header bg-light fw-bold">
          Chat WhatsApp
        </div>

        <div className="card-body p-0 d-flex flex-column">
          <MessageList messages={messages} />
          <MessageInput onSend={handleSend} />
        </div>
      </div>
    </div>
  );
}
