package model;

public class DishDAOImpl implements DishDAO{
    @Override
    public boolean checkDish(String title) {
        return false;
    }

    @Override
    public int getCookingTime(String dishTitle) {
        return 0;
    }

    @Override
    public Dish getDishRecipe(String dishRecipe) {
        return null;
    }

    @Override
    public void updateIngredients(String dishTitle) {

    }

    @Override
    public DishStatus getDishStatus(int id) {
        return null;
    }
}
