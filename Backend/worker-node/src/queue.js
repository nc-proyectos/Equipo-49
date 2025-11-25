import pkg from "bullmq";
const { Queue } = pkg;

export const reminderQueue = new Queue("reminders", {
  connection: {
    host: process.env.REDIS_HOST,
    port: 6379,
  },
});
