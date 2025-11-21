import { Worker } from "bullmq";
import axios from "axios";
import { redisConnection } from "./redis.js";
import { config } from "./config.js";

const worker = new Worker(
  "task-reminders",
  async (job) => {
    console.log("Job ejecutado:", job.data);

    const { taskId } = job.data;

    // Llamar al Backend Spring para disparar el WebSocket
    await axios.post(
      `${config.SPRING_BASE_URL}/internal/tasks/${taskId}/trigger`
    );
  },
  {
    connection: redisConnection,
  }
);

worker.on("completed", (job) => {
  console.log("Recordatorio enviado, Job:", job.id);
});

worker.on("failed", (job, err) => {
  console.error("Job falló:", job.id, err);
});
