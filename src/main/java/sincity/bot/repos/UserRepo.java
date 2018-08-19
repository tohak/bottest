package sincity.bot.repos;


import sincity.bot.domain.User;
import sincity.bot.repos.common.BaseRepo;

public interface UserRepo extends BaseRepo<User, Long> {

    User findByUserId (Long id);


}
