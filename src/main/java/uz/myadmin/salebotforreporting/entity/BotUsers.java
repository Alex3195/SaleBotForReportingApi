package uz.myadmin.salebotforreporting.entity;
import lombok.Data;
import uz.myadmin.salebotforreporting.constant.Status;

import javax.persistence.*;

@Data
@Entity
public class BotUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "dealerId")
    private Long dealerId;

    @Column(name = "username")
    private String userName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "chatid",unique = true)
    private String chatId;

    @Column(name = "contact")
    private String contact;

    @Column(name = "status")
    private Status status;
}
