import TelegramBot from "node-telegram-bot-api";
import { setStartCommand } from "./start";

const myCommands: TelegramBot.BotCommand[] = [
  { command: "/start", description: "Urochomij bota" },
];

export function setupCommands(bot: TelegramBot) {
  setOnMessage(bot);

  setStartCommand(bot);
}

export function setOnMessage(bot: TelegramBot) {
  bot.on("message", async (msg) => {
    const chatId = msg.chat.id;

    const firstWord = msg.text?.split(" ")[0] as string;

    if (myCommands.some((command) => command.command === firstWord)) return;

    bot.sendMessage(chatId, "Nie ma takiej komendy :(");
  });
}
