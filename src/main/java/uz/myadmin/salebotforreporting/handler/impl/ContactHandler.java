package uz.myadmin.salebotforreporting.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.myadmin.salebotforreporting.commands.Commands;
import uz.myadmin.salebotforreporting.handler.Handler;
import uz.myadmin.salebotforreporting.service.BotUserServices;
import uz.myadmin.salebotforreporting.service.TelegramService;

@Service
@RequiredArgsConstructor
public class ContactHandler implements Handler<Message> {
    private final TelegramService telegramService;
    private final BotUserServices userServices;

    @Override
    public void handleMessage(Message message) throws TelegramApiException {
        userServices.save(message);
        telegramService.executeMessage(SendMessage
                .builder()
                .chatId(message.getChatId().toString())
                .text("you are registered for being active ask for admin")
                .replyToMessageId(message.getMessageId())
                .replyMarkup(Commands.getEmptyKeyboard())
                .build());
    }
}

