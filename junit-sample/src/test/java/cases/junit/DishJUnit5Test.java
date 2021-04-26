package cases.junit;

import model.Dish;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(LoggingExtension.class)
@Tag("junit5test")
public class DishJUnit5Test {
    private Logger logger;
    private Dish dish;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @BeforeEach
    void setUp() {
        dish = new Dish("Pizza", new HashMap<>());
    }

    @AfterEach
    void tearDown() {
        dish = null;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void testCheckNumbers(int number) {
    }

    @Test
    @DisplayName("Testing Dish instance creation")
    void testDishInstanceCreation() {
        assertNotNull("An instance should be created.", dish);
    }

    @Test
//    @DisplayName("Testing class type of created instance")
    void testObjectIsInstanceOfDish() {
        assertTrue(dish instanceof Dish, "An instance of Dish should be created.");
    }

    @Test
    void testGetTitle() {
        assertEquals(dish.getTitle(), "Pizza", "Title \'Pizza\' should be returned.");
    }

    @Test
    void testAddIngredient() {
        dish.addIngredient("Dough", 250);

        assertTrue(dish.getIngredients().size() == 1 && dish.getIngredients().contains("Dough"), "\'Dough\' ingredient should be added.");
    }

    @Test
    void testIngredient() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        dish.deleteIngredient("Dough");

        assertFalse(dish.getIngredients().contains("Dough"), "\'Dough\' ingredient should be deleted.");
    }

    @Test
    void testGetIngredients() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        Set<java.lang.String> ingredients = new HashSet<>();
        ingredients.add("Tomatoes");
        ingredients.add("Dough");

        assertTrue(dish.getIngredients().containsAll(ingredients), "There are mismatches in the ingredient set.");
    }

    @Test
    void testGetIngredientsByMultipleAssertions() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        assertAll("Testing ingredients:",
                () -> assertTrue(dish.getIngredients().contains("Tomatoes"), "The dish does not contain tomatoes while expected."),
                () -> assertTrue(dish.getIngredients().contains("Dough"), "The dish does not contain dough while expected."));
    }

    @Test
    void testGetIngredientWeight() {
        dish.addIngredient("Dough", 250);

        assertEquals(dish.getIngredientWeight("Dough"), new Integer(250), "Returned weigh is incorrect.");
    }

    @Test
    void testGetIngredientWeightAssumingDough() {
        dish.addIngredient("Dough", 250);

        assumeTrue(dish.getIngredients().contains("Dough"), "The test is run only in case dough has been added.");
        assertEquals(dish.getIngredientWeight("Dough"), new Integer(250), "Returned weigh is incorrect.");
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

        assertEquals(dish.printRecipe(), recipe, getDescription("Printed recipe is incorrect:", recipe, dish.printRecipe()));
    }

    @Test
    void testAddIngredientWithNegativeWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> dish.addIngredient("Dough", -250), "IllegalArgumentException.class is expected to be thrown for a negative weight.");
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

