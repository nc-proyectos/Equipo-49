const BASE_URL = "http://localhost:8080/api/v1";

export async function apiFetch(endpoint, method = "GET", data = null) {
  const url = `${BASE_URL}${endpoint}`;

  const config = {
    method: method,
    headers: {
      "Content-Type": "application/json",
    },
  };

  if (data) {
    config.body = JSON.stringify(data);
  }

  try {
    const response = await fetch(url, config);

    if (!response.ok) {
      let errorDetail = `Error ${response.status}: ${response.statusText}`;
      try {
        const errorBody = await response.json();
        if (errorBody.message) {
          errorDetail = errorBody.message;
        }
      } catch (e) {
        console.error("Failed to parse error body:", e);
      }

      throw new Error(`API Error al solicitar ${url} (${errorDetail})`);
    }

    if (response.status === 204) {
      return null;
    }

    return await response.json();
  } catch (error) {
    console.error("Error en la función apiFetch:", error.message);
    throw error;
  }
}
