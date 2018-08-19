package sincity.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import sincity.bot.domain.Route;
import sincity.bot.domain.User;
import sincity.bot.domain.enums.Role;
import sincity.bot.domain.enums.Stations;
import sincity.bot.repos.RouteRepo;
import sincity.bot.repos.UserRepo;
import sincity.bot.service.UserService;

import java.util.*;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RouteRepo routeRepo;
    @Autowired
    UserService userService;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.getMessage() != null) {
            if (message.getText().equals("/start")) {
                Role role=Role.PASSENGER;
                userService.addUser(message, role);

                Route route = new Route();
                Calendar cl = Calendar.getInstance();
                cl.set(Calendar.YEAR, 1981);
                cl.set(Calendar.MONTH, Calendar.OCTOBER);
                cl.set(Calendar.DAY_OF_MONTH, 11);
                Date date = cl.getTime();
                route.setDeparture(Stations.DNEPR);
                route.setArrivel(Stations.SINVO);
                route.setPlaceCount(1);
                route.setDate(date);
                route.setComment("Abra-Kadabra");
                List<Route> routeList = new ArrayList<>();
                routeList.add(route);
                System.out.println(routeList.size());
                List<User> userList = new ArrayList<>();
                userList.add(userRepo.findByUserId(618687159L));
                route.setUsers(userList);
                routeRepo.save(route);
                // System.out.println(user.getRoutes().get(0).getId());
                System.out.println(route.getUsers().get(0).getUserName());

            }
            if (message.getText().equals("/test")) {
                User user1 = userRepo.findByUserId(618687159L);
                System.out.println(user1.getRoutes().get(0).getArrivel());
            }

            if (message.getText().equals("/tests")) {
                Optional<Route> route1 = routeRepo.findById(1L);
                if (route1.isPresent()) {
                    System.out.println(route1.get().getUsers().get(0).getUserName());
                }
            }
        }


        if (update.hasCallbackQuery()) {

        }


    }

    @Override
    public String getBotUsername() {
        return "SinCityBot";
    }

    @Override
    public String getBotToken() {
        return "631642590:AAE-0wgCYd8ztCP4ZqRkTQWC0S0LzAAeUio";
    }
}
