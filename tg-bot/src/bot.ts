import TelegramBot, { InlineKeyboardButton } from "node-telegram-bot-api";
import "dotenv/config";
import { setupCommands } from "./commands/index";
import express, { Request, Response } from "express";
import { getUrlWithParams } from "./utills";

const token: string | undefined = process.env.TG_BOT_TOKEN;
if (!token) {
  throw new Error("Telegram token is not provided in the environment variables");
}
const bot = new TelegramBot(token, { polling: true });

setupCommands(bot);


const app = express();
const port = process.env.EXPRESS_PORT ?? 3000;

app.use(express.json());

app.post("/send-offers", async (req: Request, res: Response) => {
  try {
    const jobTitle = req.body.jobTitle;
    const recipientsIds = req.body.recipientsIds;

    await sendJobOfferToAllUsers(jobTitle, recipientsIds, bot);

    res.status(200).send("Job offer sent to all users successfully.");
  } catch (error) {
    console.error("Error sending job offer:", error);
    res.status(500).send("Internal server error.");
  }
});

async function sendJobOfferToAllUsers(jobTitle: any, recipientsIds: any, bot: TelegramBot) {
  for (const recipientId of recipientsIds) {
    const urlWithParams = await getUrlWithParams(
      undefined,
      recipientId,
      "/offers/" + recipientId
    ).catch((e) => {});
    if (!urlWithParams) return;

    const button: InlineKeyboardButton = {
      text: "Otwórz aplikację",
      web_app: { url: urlWithParams },
    };

    try {
      await bot.sendMessage(recipientId, `Nowa oferta pracy:\n<b>${jobTitle}</b>`, {
        parse_mode: "HTML",
        reply_markup: {
          inline_keyboard: [[button]],
        },
      });
    } catch (error) {
      console.error(`Error sending job offer to user ${recipientId}:`, error);
    }
  }
}

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
