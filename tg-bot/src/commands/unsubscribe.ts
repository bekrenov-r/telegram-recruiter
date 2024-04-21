import axios from "axios";
import TelegramBot from "node-telegram-bot-api";

const BACKEND_URL = process.env.BACKEND_URL ?? "";
const CONTACT = process.env.CONTACT ?? "";

export function setUnsubscribeCommand(bot: TelegramBot) {
  bot.onText(/\/unsubscribe/, async (msg) => {
    const chatId = msg.chat.id;

    try {
      const response = await axios.post(BACKEND_URL + "/" + chatId + "/notifications/unsubscribe", {
        userId: chatId,
      });

      bot.sendMessage(chatId, "Subskrypcja na nowe oferty pracy anulowana");
    } catch (error) {
      console.error("Error making POST request");
      await bot.sendMessage(
        chatId,
        "Błąd podczas anulowania subskrypcji\n\nZe względu na błąd, prosimy napisać do " + CONTACT
      );
      return;
    }
  });
}
