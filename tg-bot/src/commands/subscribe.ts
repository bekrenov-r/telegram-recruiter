import axios from "axios";
import TelegramBot from "node-telegram-bot-api";

const BACKEND_URL = process.env.BACKEND_URL ?? "";

export function setSubscribeCommand(bot: TelegramBot) {
  bot.onText(/\/subscribe/, async (msg) => {
    const chatId = msg.chat.id;

    try {
      const response = await axios.post(BACKEND_URL + "/" + chatId + "/notifications/subscribe", {
        userId: chatId,
      });

      bot.sendMessage(chatId, "Subskrybujesz na nowe oferty pracy");
    } catch (error) {
      console.error("Error making POST request");
      await bot.sendMessage(chatId, "Nie udało się subskrybować :(");
      return;
    }
  });
}
