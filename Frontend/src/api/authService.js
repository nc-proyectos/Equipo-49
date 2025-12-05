import { apiFetch } from "./apiFetcher.js";

const LOGIN_ENDPOINT = "/auth/authenticate";

export async function login(username, password) {
  try {
    const response = await apiFetch(LOGIN_ENDPOINT, "POST", {
      username: username,
      password: password,
    });

    if (response && response.token) {
      localStorage.setItem("authToken", response.token);
      return response.token;
    } else {
      throw new Error("Successful login response, but no token in the body.");
    }
  } catch (error) {
    console.error("Authentication failed:", error.message);
    localStorage.removeItem("authToken");
    throw error;
  }
}

export function getAuthToken() {
  return localStorage.getItem("authToken");
}

export function logout() {
  localStorage.removeItem("authToken");
}
