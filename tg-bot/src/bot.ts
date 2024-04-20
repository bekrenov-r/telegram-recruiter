import TelegramBot from "node-telegram-bot-api";
import "dotenv/config";
import { setupCommands } from "./commands/index";
import express, { Request, Response } from 'express';

const token: string | undefined = process.env.TG_BOT_TOKEN;
if (!token) {
  throw new Error("Telegram token is not provided in the environment variables");
}
const bot = new TelegramBot(token, { polling: true });

setupCommands(bot);

const app = express();
const port = process.env.EXPRESS_PORT ?? 3000;

app.use(express.json());

app.post('/send-offers', async (req: Request, res: Response) => {
    try {
        const jobTitle = req.body.jobTitle;
        const recipientsIds = req.body.recipientsIds;

        await sendJobOfferToAllUsers(jobTitle, recipientsIds, bot);

        res.status(200).send('Job offer sent to all users successfully.');
    } catch (error) {
        console.error('Error sending job offer:', error);
        res.status(500).send('Internal server error.');
    }
});

async function sendJobOfferToAllUsers(jobPost: any, userIds: any, bot: TelegramBot) {
    for (const userId of userIds) {
        try {
            await bot.sendMessage(userId, `Nowa oferta pracy:\n${JSON.stringify(jobPost, null, 2)}`);
        } catch (error) {
            console.error(`Error sending job offer to user ${userId}:`, error);
        }
    }
}

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});