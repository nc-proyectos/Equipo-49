export default function MessageList({ messages }) {
  return (
    <div className="flex-grow-1 p-3 overflow-auto bg-light">
      {messages.map((msg) => (
        <div
          key={msg.id}
          className={`d-flex mb-2 ${
            msg.fromMe ? "justify-content-end" : "justify-content-start"
          }`}
        >
          <div
            className={`p-2 rounded ${
              msg.fromMe
                ? "bg-primary text-white"
                : "bg-white border"
            }`}
            style={{ maxWidth: "70%" }}
          >
            {msg.text}
          </div>
        </div>
      ))}
    </div>
  );
}
