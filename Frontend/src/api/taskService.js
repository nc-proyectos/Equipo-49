import { apiFetch } from "./apiFetcher.js";

const GET_PENDING_TASKS_ENDPOINT = "/task/getPending";
const SAVE_TASK_ENDPOINT = "/task/save";

export async function getPendingTasks() {
  try {
    const tasks = await apiFetch(GET_PENDING_TASKS_ENDPOINT, "GET");
    return tasks;
  } catch (error) {
    console.error("Failed to get all pending tasks:", error.message);
    throw error;
  }
}

export async function saveTask(taskData) {
  try {
    const response = await apiFetch(SAVE_TASK_ENDPOINT, "POST", taskData);
    return response;
  } catch (error) {
    console.error("Failed to save task:", error.message);
    throw error;
  }
}

export async function getRemindersCount() {
  try {
    const tasks = await getPendingTasks();
    if (Array.isArray(tasks)) return tasks.length;
    return 0;
  } catch (error) {
    return 0;
  }
}
