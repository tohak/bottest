package sincity.bot.repos;

import sincity.bot.domain.Route;
import sincity.bot.repos.common.BaseRepo;

import java.util.Optional;

public interface RouteRepo extends BaseRepo<Route, Long> {
    Optional<Route> findById (Long id);
}
