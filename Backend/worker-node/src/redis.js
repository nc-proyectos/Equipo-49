import { Redis } from "ioredis";
import { config } from "./config.js";

export const redisConnection = new Redis({
  host: config.REDIS_HOST,
  port: config.REDIS_PORT,
});
