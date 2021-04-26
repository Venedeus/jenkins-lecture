package model;

import java.util.Optional;

public interface ServantService {
    Optional<Integer> orderCooking(String title);

    Optional<Dish> giveOrder(int id);
}
