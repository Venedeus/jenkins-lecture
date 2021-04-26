package model;

import java.util.Optional;

public interface KitchenService {
    Optional<Integer> cookDish(String title);

    Dish provideDish();

    boolean checkIngredientsForCooking(String title);

    boolean checkDishStatus(int id);
}
