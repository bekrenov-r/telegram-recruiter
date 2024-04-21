import axios from "axios";
import TelegramBot from "node-telegram-bot-api";

const BACKEND_URL = process.env.BACKEND_URL ?? "";
const MINI_APP_URL = process.env.MINI_APP_URL ?? "";
const REGISTER_PATH = process.env.REGISTER_PATH ?? "";

export async function getUrlWithParams(msg?: TelegramBot.Message, _chatId?: number, path?: string) {
  const url = MINI_APP_URL + (path ?? "");
  const chatId = msg?.chat.id ?? _chatId;
  let token = "";

  try {
    const response = await axios.post(BACKEND_URL + REGISTER_PATH, {
      telegramId: chatId,
      first_name: msg?.from?.first_name,
      username: msg?.from?.username,
      last_name: msg?.from?.last_name,
    });
    token = response.data;
    console.log("get token request successful: " + token.slice(0, 10));
    return `${url}?token=${token}`;
  } catch (error) {
    throw new Error("Error while getting url with params from backend");
  }
}
