import TelegramBot from "node-telegram-bot-api";
import "dotenv/config";
import { setupCommands } from "./index";

const token: string | undefined = process.env.TG_BOT_TOKEN;
if (!token) {
  throw new Error("Telegram token is not provided in the environment variables");
}
const bot = new TelegramBot(token, { polling: true });

setupCommands(bot);