import { apiFetch } from "./apiFetcher.js";

const GET_ALL_CONTACTS_ENDPOINT = "/contact/getAll";
const SAVE_CONTACT_ENDPOINT = "/contact/save";

export async function getAllContacts() {
  try {
    const contactsArray = await apiFetch(GET_ALL_CONTACTS_ENDPOINT, "GET");
    return contactsArray;
  } catch (error) {
    console.error("Failed to fetch all contacts:", error.message);
    throw error;
  }
}

export async function saveContact(contactData) {
  try {
    const response = await apiFetch(SAVE_CONTACT_ENDPOINT, "POST", contactData);
    return response;
  } catch (error) {
    console.error("Failed to save contact:", error.message);
    throw error;
  }
}

export async function getContactsCount() {
  try {
    const contactsArray = await getAllContacts();
    if (Array.isArray(contactsArray)) return contactsArray.length;
    return 0;
  } catch (error) {
    return 0;
  }
}
