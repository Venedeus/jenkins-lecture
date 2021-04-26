package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class KitchenServiceImplTest {
    @Mock
    private DishDAO dishDAO = Mockito.mock(DishDAO.class);

    @InjectMocks
    private KitchenServiceImpl kitchenService;

    /*
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        kitchenService = new KitchenServiceImpl(dishDAO);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    */

    @Test
    void testCheckDishWhenIngredientsAvailable() {
        when(dishDAO.checkDish("Pizza")).thenReturn(true);

        assertThat(kitchenService.checkIngredientsForCooking("Pizza")).isEqualTo(true);
    }

    @Test
    void testCheckDishWhenIngredientsNotAvailable() {
        when(dishDAO.checkDish("Pasta")).thenReturn(false);

        assertThat(kitchenService.checkIngredientsForCooking("Pasta")).isEqualTo(false);
    }

    @Test
    void testCookDishWhenEnoughIngredients() {
        when(dishDAO.checkDish("Pizza")).thenReturn(true);
        when(dishDAO.getCookingTime("Pizza")).thenReturn(20);

        assertThat(kitchenService.cookDish("Pizza")).isPresent().hasValue(20);
    }

    @Test
    void testCookDishWhenNotEnoughIngredients() {
        when(dishDAO.checkDish("Pasta")).thenReturn(false);

        assertThat(kitchenService.cookDish("Pasta")).isNotPresent();
    }

    @Test
    void testCookDishVerifyingUpdateIngredientsInvocationInDishDAO() {
//        lenient().when(dishDAO.getCookingTime(anyString())).thenReturn(10);
        when(dishDAO.getCookingTime(anyString())).thenReturn(10);

        kitchenService.cookDish(anyString());

        verify(dishDAO, times(1)).updateIngredients(anyString());
        verify(dishDAO, atLeastOnce()).getDishRecipe(anyString());
    }

    @Test
    void testProvideDish() {
        String dishTitle = "Pizza";

        when(dishDAO.getDishRecipe(dishTitle)).thenReturn(new Dish(dishTitle, new HashMap<>()));

        kitchenService.cookDish(dishTitle);

        assertThat(kitchenService.provideDish().getTitle())
                .isEqualTo(new Dish(dishTitle, new HashMap<>()).getTitle());
    }

    @Test
    void testCheckDishStatusWhenDishIsReady() {
        when(dishDAO.getDishStatus(anyInt())).thenReturn(DishStatus.READY);

        assertThat(kitchenService.checkDishStatus(anyInt()))
                .isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = DishStatus.class, names = {"QUEUED", "COOKING"})
    void testCheckDishStatusWhenDishIsNotReady(DishStatus dishStatus) {
        when(dishDAO.getDishStatus(anyInt())).thenReturn(dishStatus);

        assertThat(kitchenService.checkDishStatus(anyInt()))
                .isFalse();
    }
}