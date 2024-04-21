import TelegramBot, { InlineKeyboardButton } from "node-telegram-bot-api";
import { getUrlWithParams } from "../utills";

export function setStartCommand(bot: TelegramBot) {
  bot.onText(/\/start/, async (msg) => {
    const chatId = msg.chat.id;
    const urlWithParams = await getUrlWithParams(msg).catch((e) => {});
    if (!urlWithParams) {
      bot.sendMessage(chatId, "Nie udało się zarejestrować");
      return;
    }

    const button: InlineKeyboardButton = {
      text: "Otwórz aplikację",
      web_app: { url: urlWithParams ?? "" },
    };

    bot.sendMessage(chatId, "Witamy na platformie do wyszukiwania pracy!", {
      reply_markup: {
        inline_keyboard: [[button]],
      },
    });
  });
}
