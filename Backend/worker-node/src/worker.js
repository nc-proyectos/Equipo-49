import pkg from "bullmq";
const { Worker } = pkg;

const worker = new Worker(
  "reminders",
  async (job) => {
    console.log("Received job:", job.id, job.data);

    await fetch(
      `${process.env.SPRING_BASE_URL}/internal/task/notify/${job.id}`,
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(job.data),
      }
    );

    console.log("Processed job:", job.id);
  },
  {
    connection: {
      host: process.env.REDIS_HOST,
      port: 6379,
    },
  }
);

worker.on("ready", () => console.log("Worker ready and connected to Redis"));
worker.on("failed", (job, err) => console.error("Job failed", job.id, err));
worker.on("completed", (job) => console.log("Job completed", job.id));
