import "../Chat/chat.css"

export default function MessageList({ messages }) {
  return (
   <div className="chat-body">
      {messages.map((msg) => (
        <div
          key={msg.id}
          className={`message-row ${msg.fromMe ? "me" : "other"}`}
        >
          <div
            className={`message-bubble ${
              msg.fromMe ? "me" : "other"
            }`}
          >
            {msg.text}
          </div>
        </div>
      ))}
    </div>
  );
}
