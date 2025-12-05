import { apiFetch } from "./apiFetcher.js";

export async function getContactsCount() {
  try {
    const contactsArray = await apiFetch("/contacts", "GET");

    if (Array.isArray(contactsArray)) {
      return contactsArray.length;
    }

    if (
      contactsArray &&
      typeof contactsArray === "object" &&
      contactsArray.totalCount !== undefined
    ) {
      console.warn(
        "API returned object with 'totalCount'. Using that property."
      );
      return contactsArray.totalCount;
    }

    console.error(
      "API response was not an array or did not contain totalCount."
    );
    return 0;
  } catch (error) {
    console.error("Fallo al obtener el conteo de contactos:", error.message);
    throw error;
  }
}
