package cases.junit;

import model.Dish;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DishAssertJUnit4Test {
    private Dish dish;

    @Before
    public void setUp() {
        dish = new Dish("Pizza", new HashMap<>());
    }

    @After
    public void tearDown() {
        dish = null;
    }

    @Test
    public void testDishInstanceCreation() {
        assertThat(dish)
                .as("An instance should be created.")
                .isNotNull();
    }

    @Test
    public void testObjectIsInstanceOfDish() {
        assertThat(dish)
                .as("An instance of Dish should be created.")
                .isInstanceOf(Dish.class);
    }

    @Test
    public void testGetTitle() {
        assertThat(dish.getTitle())
                .as("Title \'Pizza\' should be returned.")
                .isEqualTo("Pizza");
    }

    @Test
    public void testAddIngredient() {
        dish.addIngredient("Dough", 250);

        assertThat(dish.getIngredients())
                .as("\'Dough\' ingredient should be added.")
                .isNotEmpty().contains("Dough");
    }

    @Test
    public void testIngredient() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        dish.deleteIngredient("Dough");

        assertThat(dish.getIngredients())
                .as("\'Dough\' ingredient should be deleted.")
                .doesNotContain("Dough");
    }

    @Test
    public void testGetIngredients() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        Set<java.lang.String> ingredients = new HashSet<>();
        ingredients.add("Tomatoes");
        ingredients.add("Dough");

        assertThat(dish.getIngredients())
                .as("There are mismatches in the ingredient set.")
                .containsAll(ingredients);
    }

    @Test
    public void testGetIngredientWeight() {
        dish.addIngredient("Dough", 250);

        assertThat(dish.getIngredientWeight("Dough"))
                .as("Returned weigh is incorrect.")
                .isEqualTo(250);
    }

    @Test
    public void testPrintRecipe() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        java.lang.String recipe =
                "Pizza\n" +
                        "Recipe:\n" +
                        "Dough: 250\n" +
                        "Tomatoes: 100";

        assertThat(dish.printRecipe())
                .as(getDescription("Printed recipe is incorrect:", recipe, dish.printRecipe()))
                .isEqualTo(recipe);
    }

    @Test
    public void testAddIngredientWithNegativeWeight() {
        assertThatThrownBy(() -> dish.addIngredient("Dough", -250))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testAddIngredientWithZeroWeight() {
        assertThatThrownBy(() -> dish.addIngredient("Dough", 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private java.lang.String getDescription(java.lang.String description, java.lang.String expectedValue, java.lang.String receivedValue) {
        return description + "\nexpected:\n" + expectedValue + "\ngot:\n" + receivedValue;
    }
}