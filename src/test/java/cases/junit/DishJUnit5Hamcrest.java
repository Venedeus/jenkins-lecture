package cases.junit;

import model.Dish;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DishJUnit5Hamcrest {
    private Dish dish;

    @BeforeEach
    void setUp() {
        dish = new Dish("Pizza", new HashMap<>());
    }

    @AfterEach
    void tearDown() {
        dish = null;
    }

    @Test
    @DisplayName("Testing Dish instance creation")
    void testDishInstanceCreation() {
        assertThat("An instance should be created.", dish, notNullValue());
    }

    @Test
    @DisplayName("Testing class type of created instance")
    void testObjectIsInstanceOfDish() {
        assertThat("An instance of Dish should be created.", dish, instanceOf(Dish.class));
    }

    @Test
    void testGetTitle() {
        assertThat("Title \'Pizza\' should be returned.", dish.getTitle(), equalTo("Pizza"));
    }

    @Test
    void testAddIngredient() {
        dish.addIngredient("Dough", 250);

        assertThat("The ingredient list has another size then 1.", dish.getIngredients(), hasSize(1));
        assertThat("\'Dough\' ingredient should be added.", dish.getIngredients(), contains("Dough"));
    }

    @Test
    void testIngredient() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        dish.deleteIngredient("Dough");

        assertThat("\'Dough\' ingredient should be deleted.", dish.getIngredients(), not(contains("Dough")));
    }

    @Test
    void testGetIngredients() {

        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        Set<java.lang.String> ingredients = new HashSet<>();
        ingredients.add("Tomatoes");
        ingredients.add("Dough");

        assertThat("There are mismatches in the ingredient set.", dish.getIngredients(),  equalTo(ingredients));
    }

    @Test
    void testGetIngredientsByMultipleAssertions() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        assertThat("The dish does not contain tomatoes while expected.", dish.getIngredients(), hasItem("Tomatoes"));
        assertThat("The dish does not contain dough while expected.", dish.getIngredients(), hasItem("Dough"));
    }

    @Test
    void testGetIngredientWeight() {
        dish.addIngredient("Dough", 250);

        assertThat("Returned weigh is incorrect.", dish.getIngredientWeight("Dough"), equalTo(250));
    }

    @Test
    void testGetIngredientWeightAssumingDough() {
        dish.addIngredient("Dough", 250);

        assertThat("The test is run only in case dough has been added.", dish.getIngredients(), hasItem("Dough"));
        assertThat("Returned weigh is incorrect.", dish.getIngredientWeight("Dough"), equalTo(250));
    }

    @Test
    void testPrintRecipe() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        java.lang.String recipe =
                "Pizza\n" +
                        "Recipe:\n" +
                        "Dough: 250\n" +
                        "Tomatoes: 100";

        assertThat(getDescription("Printed recipe is incorrect:", recipe, dish.printRecipe()), dish.printRecipe(), equalTo(recipe));
    }

    @Test
    void testAddIngredientWithNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> dish.addIngredient("Dough", -250), "IllegalArgumentException.class is expected to be thrown for a negative weight.");
    }

    @Test
    void testAddIngredientWithZeroWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> dish.addIngredient("Dough", 0), "IllegalArgumentException.class is expected to be thrown for a zero weight.");
    }

    private java.lang.String getDescription(java.lang.String description, java.lang.String expectedValue, java.lang.String receivedValue) {
        return description + "\nexpected:\n" + expectedValue + "\ngot:\n" + receivedValue;
    }
}
