package uz.myadmin.salebotforreporting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.myadmin.salebotforreporting.constant.Status;
import uz.myadmin.salebotforreporting.entity.BotUsers;
import uz.myadmin.salebotforreporting.model.BotUserModel;
import uz.myadmin.salebotforreporting.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotUserServices {
    private final UserRepository userRepository;

    public BotUserModel getByChatId(Long chatId) {
        Optional<BotUsers> opt = userRepository.findByChatId(chatId.toString());
        if (opt.isPresent()) {
            return convertToModel(opt.get());
        }
        return null;
    }

    public BotUserModel getByUserName(String name) {
        BotUserModel botUserModel = null;
        Optional<BotUsers> entity = userRepository.findByUserName(name);
        if (entity.isPresent()) {
            botUserModel = convertToModel(entity.get());
        }
        return botUserModel;
    }

    public BotUserModel getById(Long id) {
        BotUserModel model = null;
        Optional<BotUsers> entity = userRepository.findById(id);
        if (entity.isPresent()) {
            model = convertToModel(entity.get());
        }
        return model;
    }

    public void update(BotUserModel model) {
        userRepository.save(convertToEntity(model));
    }

    public void save(Message message) {
        Optional<BotUsers> entity = userRepository.findByChatId(message.getChatId().toString());

        if (!entity.isPresent()) {
            BotUsers users = new BotUsers();
            users.setChatId(message.getChatId().toString());
            users.setUserName(message.getFrom().getUserName());
            users.setFirstName(message.getFrom().getFirstName());
            users.setLastName(message.getFrom().getLastName());
            users.setStatus(Status.PASSIVE);
            users.setContact(message.getContact().getPhoneNumber());
            userRepository.save(users);
        }
    }

    private BotUsers convertToEntity(BotUserModel model) {
        BotUsers entity = new BotUsers();
        entity.setId(model.getId());
        entity.setChatId(model.getChatId().toString());
        entity.setDealerId(model.getDealerId());
        entity.setUserName(model.getUserName());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setFullName(model.getFullName());
        entity.setContact(model.getContact());
        entity.setStatus(model.getStatus());
        return entity;
    }

    private BotUserModel convertToModel(BotUsers botUsers) {
        BotUserModel userModel = new BotUserModel();
        userModel.setId(botUsers.getId());
        userModel.setChatId(Long.valueOf(botUsers.getChatId()));
        userModel.setDealerId(botUsers.getDealerId());
        userModel.setUserName(botUsers.getUserName());
        userModel.setFirstName(botUsers.getFirstName());
        userModel.setLastName(botUsers.getLastName());
        userModel.setFullName(botUsers.getFullName());
        userModel.setContact(botUsers.getContact());
        userModel.setStatus(botUsers.getStatus());
        return userModel;
    }
}
