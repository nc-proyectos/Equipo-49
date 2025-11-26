import dotenv from "dotenv";
dotenv.config();

export const config = {
  REDIS_HOST: process.env.REDIS_HOST || "localhost",
  REDIS_PORT: Number.parseInt(process.env.REDIS_PORT || "6379"),
  SPRING_BASE_URL: process.env.SPRING_BASE_URL || "http://spring:8080",
};
