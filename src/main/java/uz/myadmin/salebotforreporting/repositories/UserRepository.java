package uz.myadmin.salebotforreporting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.myadmin.salebotforreporting.entity.BotUsers;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BotUsers, Long> {

    Optional<BotUsers> findByChatId(String chatId);

    Optional<BotUsers> findByUserName(String userName);
}
