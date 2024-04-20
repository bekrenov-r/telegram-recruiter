import TelegramBot from "node-telegram-bot-api";

export function setupCommands(bot: TelegramBot) {
    setOnMessage(bot)
}

export function setOnMessage(bot: TelegramBot) {
    bot.on("message", async (msg) => {
      const chatId = msg.chat.id;
  
      bot.sendMessage(chatId, "It works");
    });
  }
  