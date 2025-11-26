import express from "express";
import { reminderQueue } from "./queue.js";

const app = express();
app.use(express.json());

app.post("/queue/reminder", async (req, res) => {
  const { taskId, userId, reminderAt } = req.body;

  if (!taskId || !userId || !reminderAt) {
    return res.status(400).json({
      error: "taskId, userId and reminderAt are required",
    });
  }

  const targetTime = new Date(reminderAt).getTime();
  const now = Date.now();

  if (Number.isNaN(targetTime)) {
    return res.status(400).json({
      error: "Invalid date format for reminderAt",
    });
  }

  if (targetTime <= now) {
    return res.status(400).json({
      error: "reminderAt must be in the future",
    });
  }

  const MAX_DELAY = 2 ** 31 - 1; // límite de BullMQ
  const delay = Math.min(targetTime - now, MAX_DELAY);

  await reminderQueue.add(
    "send-reminder",
    { taskId, userId },
    {
      delay,
      attempts: 3,
      removeOnComplete: true,
    }
  );
  res.json({ status: "OK" });
});

app.listen(3000, "0.0.0.0", () =>
  console.log("Worker API running on port 3000")
);
