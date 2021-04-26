package jdbc;

import model.Dish;
import model.DishDAO;
import model.DishStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.stream.Collectors;

public class DishDAOImplH2 implements DishDAO {
    private JdbcTemplate jdbcTemplate;

    public DishDAOImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DishStatus getDishStatus(int id) {
        String query = "SELECT status FROM dish_statuses WHERE id = ?";

        DishStatus dishStatus = jdbcTemplate.queryForObject(query, new Object[]{id}, new DishStatusMapper());

        return dishStatus;
    }

    @Override
    public boolean checkDish(String title) {
        String queryGetDishId = "SELECT id FROM cooking_time WHERE title = ?";
        int dishId = jdbcTemplate.queryForObject(queryGetDishId, new String[]{title}, Integer.class);

        String queryGetRecipe = "SELECT title, weight FROM recipes WHERE id = ?";
        String queryGetIngredientsFromStorage = "SELECT weight FROM ingredients WHERE title = ?";

        Map<String, Integer> ingredientsByRecipe =
                jdbcTemplate.queryForList(queryGetRecipe, dishId)
                        .stream()
                        .collect(Collectors
                                .toMap(
                                e -> (String) e.get("title"),
                                e -> Integer.parseInt(e.get("weight").toString())));

        boolean isEnough = ingredientsByRecipe
                .entrySet()
                .stream()
                .anyMatch(e -> jdbcTemplate.queryForObject(queryGetIngredientsFromStorage, new String[]{e.getKey()}, Integer.class) < e.getValue());

        return isEnough;
    }

    @Override
    public int getCookingTime(String dishTitle) {
        String query = "SELECT cooking_time FROM cooking_time WHERE title = ?";

        int cookingTime = jdbcTemplate.queryForObject(query, new Object[]{dishTitle}, Integer.class);

        return cookingTime;
    }

    // To be implemented...
    @Override
    public Dish getDishRecipe(String dishRecipe) {
        return null;
    }

    // To be implemented...
    @Override
    public void updateIngredients(String dishTitle) {

    }
}
