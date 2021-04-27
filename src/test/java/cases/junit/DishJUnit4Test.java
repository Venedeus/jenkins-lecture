package cases.junit;

import model.Dish;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class DishJUnit4Test {
    private Dish dish;
    int number;

    public DishJUnit4Test(int number){
        this.number = number;
    }

    @Parameterized.Parameters
    public static List<Integer> numbers() {
        return Arrays.asList(new Integer[]{1, 2, 3, 4});
    }

    @Test
    public void testNumbers() {
        Assert.assertTrue("Failed with incorrect parameter: ", Arrays.stream(new Integer[]{1, 2, 3, 4}).anyMatch(num -> num == number));
    }

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
        Assert.assertNotNull("An instance should be created.", dish);
    }

    @Test
    public void testObjectIsInstanceOfDish() {
        Assert.assertTrue("An instance of Dish should be created.", dish instanceof Dish);
    }

    @Test
    public void testGetTitle() {
        Assert.assertEquals("Title \'Pizza\' should be returned.", dish.getTitle(), "Pizza");
    }

    @Test
    public void testAddIngredient() {
        dish.addIngredient("Dough", 250);

        Assert.assertTrue("\'Dough\' ingredient should be added.", dish.getIngredients().size() == 1 && dish.getIngredients().contains("Dough"));
    }

    @Test
    public void testIngredient() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        dish.deleteIngredient("Dough");

        Assert.assertFalse("\'Dough\' ingredient should be deleted.", dish.getIngredients().contains("Dough"));
    }

    @Test
    public void testGetIngredients() {
        dish.addIngredient("Dough", 250);
        dish.addIngredient("Tomatoes", 100);

        Set<java.lang.String> ingredients = new HashSet<>();
        ingredients.add("Tomatoes");
        ingredients.add("Dough");

        Assert.assertTrue("There are mismatches in the ingredient set.", dish.getIngredients().containsAll(ingredients));
    }

    @Test
    public void testGetIngredientWeight() {
        dish.addIngredient("Dough", 250);

        Assert.assertEquals("Returned weigh is incorrect.", dish.getIngredientWeight("Dough"), new Integer(250));
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

        Assert.assertEquals(getDescription("Printed recipe is incorrect:", recipe, dish.printRecipe()), dish.printRecipe(), recipe);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIngredientWithNegativeWeight() {
        dish.addIngredient("Dough", -250);
    }

    @Test
    public void testAddIngredientWithZeroWeight() {
        Assert.assertThrows("IllegalArgumentException.class is expected to be thrown for a zero weight.",
                IllegalArgumentException.class,
                () -> dish.addIngredient("Dough", 0));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAddIngredientWithNegativeWeightUsingRule() {
        thrown.expect(IllegalArgumentException.class);

        dish.addIngredient("Dough", -250);
    }

    private java.lang.String getDescription(java.lang.String description, java.lang.String expectedValue, java.lang.String receivedValue) {
        return description + "\nexpected:\n" + expectedValue + "\ngot:\n" + receivedValue;
    }
}