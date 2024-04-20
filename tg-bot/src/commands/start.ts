import TelegramBot from "node-telegram-bot-api";

const rolesKeyboard = {
  inline_keyboard: [
    [
      { text: "Rekruter", callback_data: "rekruter" },
      { text: "Pracownik", callback_data: "pracownik" },
    ],
  ],
};

export function setStartCommand(bot: TelegramBot) {
  bot.onText(/\/start/, async (msg) => {
    const chatId = msg.chat.id;

    bot.sendMessage(chatId, "Witamy na platformie do wyszukiwania pracy!\n\nWybierz rolę:", {
      reply_markup: rolesKeyboard,
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
