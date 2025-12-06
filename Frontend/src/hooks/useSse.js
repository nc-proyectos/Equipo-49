import { useEffect, useState } from "react";

export default function useSSE(url) {
  const [message, setMessage] = useState(null);

  useEffect(() => {
    const eventSource = new EventSource(url);

    eventSource.onmessage = (event) => {
      const data = JSON.parse(event.data);
      setMessage(data);
    };

    eventSource.onerror = () => {
      console.error("SSE error, closing connection");
      eventSource.close();
    };

    return () => eventSource.close();
  }, [url]);

  return message;
}