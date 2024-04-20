import axios from "axios";
import TelegramBot, { InlineKeyboardButton } from "node-telegram-bot-api";

const MINI_APP_URL = process.env.MINI_APP_URL ?? "";
const REGISTER_PATH = process.env.REGISTER_PATH ?? "";

// const rolesKeyboard = {
//   inline_keyboard: [
//     [
//       { text: "Rekruter", callback_data: "rekruter" },
//       { text: "Pracownik", callback_data: "pracownik" },
//     ],
//   ],
// };

export function setStartCommand(bot: TelegramBot) {
  bot.onText(/\/start/, async (msg) => {
    const chatId = msg.chat.id;

    let token = "";

    try {
      const response = await axios.post(MINI_APP_URL + REGISTER_PATH, {
        telegramId: chatId,
        first_name: msg.from?.first_name,
        username: msg.from?.username,
        last_name: msg.from?.last_name,
      });
      token = response.data;
      console.log("POST request successful");
    } catch (error) {
      console.error("Error making POST request");
      await bot.sendMessage(chatId, "Nie udało się zalogować się :(");
      return;
    }

    const urlWithParams = `${MINI_APP_URL}?token=${token}`;

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

  bot.on("callback_query", (callbackQuery: TelegramBot.CallbackQuery) => {
    const data = callbackQuery.data;

    if (!callbackQuery.message) return;
    const chatId = callbackQuery.message.chat.id;

    switch (data) {
      case "rekruter":
        bot.sendMessage(chatId, "Strona Rekrutera");
        break;
      case "pracownik":
        bot.sendMessage(chatId, "Strona Pracownika");
        break;
      default:
        bot.sendMessage(chatId, "Nieprawidłowy przycisk");
        break;
    }
  });
}
