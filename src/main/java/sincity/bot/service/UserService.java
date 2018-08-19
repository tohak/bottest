package sincity.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Message;
import sincity.bot.domain.User;
import sincity.bot.domain.enums.Role;
import sincity.bot.repos.UserRepo;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    /**
     * Добовление юзера, если такого юзера нету, создает и возвращает тру.
     * Если такой юзер есть проверяет роль, если у юзера нет такой роли добовляет и возвращает фолсе
     */
    public boolean addUser(Message message, Role role){
        User user;
        user = userRepo.findByUserId(message.getFrom().getId().longValue());
        if (user == null) {
            user.setUserId(Long.valueOf(message.getFrom().getId()));
            user.setUserName(getNickName(message));
            user.setChatId(message.getChatId());
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user.setState(0);
            user.setTelegramLink("@" + message.getFrom().getUserName());
            userRepo.save(user);
            return true;
        }else {
            updateRoleUser(user, role);
            return false;
        }
    }

    /**
     * Если у юзера нет такой роли добовляет ее
     */
    private void updateRoleUser(User user, Role role){
        Set<Role> roles= new HashSet<>();
        roles = user.getRoles();
        if (!roles.contains(role)){
            roles.add(role);
            user.setRoles(roles);
        }
    }

    /**
     * Создает никнейм для юзера, если нечего подходящеего не вытягивает возвращает "Anonymous"
     */
    private String getNickName(Message message) {
        String userName;
        if (message.getFrom().getUserName().isEmpty()){
            if (message.getFrom().getFirstName().isEmpty()){
                if (message.getFrom().getLastName().isEmpty()){
                    userName = "Anonymous";
                }else userName= message.getFrom().getLastName();
            }else userName = message.getFrom().getFirstName();
        }else userName = message.getFrom().getUserName();
        return userName;
    }
}
