import TelegramBot from "node-telegram-bot-api";
import { setStartCommand } from "./start";
import { setSubscribeCommand } from "./subscribe";
import { setUnsubscribeCommand } from "./unsubscribe";

const myCommands: TelegramBot.BotCommand[] = [
  { command: "/start", description: "Urochomij bota" },
  { command: "/subscribe", description: "Subskrybuj na nowe oferty pracy" },
  { command: "/unsubscribe", description: "Rezygnuj z subskrypcji" },
];

export function setupCommands(bot: TelegramBot) {
  setOnMessage(bot);

  setStartCommand(bot);
  setSubscribeCommand(bot);
  setUnsubscribeCommand(bot);
}

export function setOnMessage(bot: TelegramBot) {
  bot.on("message", async (msg) => {
    const chatId = msg.chat.id;

    const firstWord = msg.text?.split(" ")[0] as string;

    if (myCommands.some((command) => command.command === firstWord)) return;

    bot.sendMessage(chatId, "Nie ma takiej komendy :(");
  });
}
