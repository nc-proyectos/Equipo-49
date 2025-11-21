import { Queue } from "bullmq";
import { redisConnection } from "./redis.js";

export const reminderQueue = new Queue("task-reminders", {
  connection: redisConnection,
});
