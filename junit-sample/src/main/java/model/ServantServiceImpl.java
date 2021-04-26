package model;

import java.util.Optional;

public class ServantServiceImpl implements ServantService {
    private KitchenService kitchenService;

    public ServantServiceImpl(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    // The method is used to cook a dish.
    // Items for testing:
    // 1.   The method just returns a cooking time gotten from the DAO;
    // 2.   If a dish cannot be cooked for any reason null is returned;
    public Optional<Integer> orderCooking(String dishTitle) {
        return kitchenService.cookDish(dishTitle);
    }

    // The method gives an order is ready.
    // Items to test:
    // 1.   If a dish is ready a Dish object is returned;
    // 2.   If a dish is not ready null is returned;
    @Override
    public Optional<Dish> giveOrder(int id) {
        return Optional
                .ofNullable(
                        kitchenService.checkDishStatus(id) ?
                                kitchenService.provideDish():
                                null);
    }
}
