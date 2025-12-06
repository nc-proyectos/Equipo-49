export const sendMessage = async (to, message) => {
  try {
    const res = await fetch("http://localhost:8080/whatsapp/send", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ to, message })
    });

    return res.json();
  } catch (err) {
    console.error("Error enviando mensaje:", err);
  }
};
