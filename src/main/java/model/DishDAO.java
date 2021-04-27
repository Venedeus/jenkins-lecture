package model;

public interface DishDAO {
    boolean checkDish(String title);

    int getCookingTime(String dishTitle);

    Dish getDishRecipe(String dishRecipe);

    void updateIngredients(String dishTitle);

    DishStatus getDishStatus(int id);
}
