package model;

import java.util.Optional;

public class KitchenServiceImpl implements KitchenService{
    private DishDAO dishDAO;
    private Dish dish;

    public KitchenServiceImpl(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    // The method cooks a dish.
    // Items for testing:
    // 1.   The method just returns a cooking time gotten from the DAO;
    // 2.   While the method is running there is an interaction with DAO
    //      to update ingredients and only once and at least one interaction
    //      to get a dish recipe.
    @Override
    public Optional<Integer> cookDish(String dishTitle) {
        dishDAO.updateIngredients(dishTitle);
        dish = dishDAO.getDishRecipe(dishTitle);

        System.out.println("Some logic for cooking inside...");

        return Optional
                .ofNullable(
                        dishDAO.checkDish(dishTitle)?
                                dishDAO.getCookingTime(dishTitle):
                                null);
    }

    // The method checks if a dish if available for cooking.
    // Items for testing:
    // 1.   true is expected when ingredients are available;
    // 2.   false is expected when ingredients are not available;
    @Override
    public boolean checkIngredientsForCooking(String dishTitle) {

        return dishDAO.checkDish(dishTitle);
    }

    // The method returns true/false depending on the DAO response.
    // Items for testing:
    // 1.   When status is QUEUED or COOKING false should be returned;
    // 2.   When status is READY true should be returned;
    @Override
    public boolean checkDishStatus(int id) {
        return dishDAO.getDishStatus(id).equals(DishStatus.READY)? true: false;
    }

    @Override
    public Dish provideDish() {
        return dish;
    }
}
