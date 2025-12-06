import { useState, useEffect } from "react";
import useSSE from "../../hooks/useSse";
import MessageList from "./MessageList";
import MessageInput from "./MessageInput";
import "../Chat/chat.css"

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
    setMessages((prev) => [...prev, {id: Date.now(), text, fromMe: true }]);
  };

  return (
        <div className="chat-container">
      <div className="chat-header">Chat WhatsApp</div>

      <MessageList messages={messages} />
      <MessageInput onSend={handleSend} />
    </div>
  );
}
