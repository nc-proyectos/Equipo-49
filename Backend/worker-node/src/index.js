import express from "express";
import { reminderQueue } from "./queue.js";

const app = express();
app.use(express.json());

app.post("/queue/reminder", async (req, res) => {
  const { taskId, userId, reminderAt } = req.body;

  const delay = new Date(reminderAt).getTime() - Date.now();

  await reminderQueue.add(
    "send-reminder",
    { taskId, userId },
    {
      delay: Math.max(delay, 0),
      attempts: 3,
      removeOnComplete: true,
    }
  );

  return res.json({ status: "queued" });
});

app.listen(3000, () => console.log("Worker API running on port 3000"));
