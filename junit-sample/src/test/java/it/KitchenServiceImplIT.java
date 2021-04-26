package it;

import jdbc.DishDAOImplH2;
import model.DishDAO;
import model.DishStatus;
import model.KitchenService;
import model.KitchenServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KitchenServiceImplIT {
    DishDAO dishDAO;
    JdbcTemplate jdbcTemplate;
    EmbeddedDatabase db;
    private KitchenService kitchenService;

    @BeforeEach
    void setUp() {

        db = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(db);
        dishDAO = new DishDAOImplH2(new JdbcTemplate(db));
        kitchenService = new KitchenServiceImpl(dishDAO);
    }

    @AfterEach
    void tearDown() {
        db.shutdown();
    }

    @Test
    void testDishStatusIsReady() {
        assertThat(dishDAO.getDishStatus(1)).isEqualTo(DishStatus.READY);
    }

    @Test
    void testExceptionIsThrownIfIDIsMissed() {
        assertThatThrownBy(() -> dishDAO.getDishStatus(4)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void testGetCookingTimeWhenIngredientsAreAvailable() {

        assertThat(dishDAO.getCookingTime("Pizza")).isEqualTo(20);
    }

    @Test
    void testCookDishWhenEnoughIngredients() {
        assertThat(kitchenService.cookDish("Pasta")).isPresent().hasValue(30);
    }

    @Test
    void testCheckIngredientsForCookingWhenEnough() {
        assertThat(kitchenService.checkIngredientsForCooking("Pizza")).isFalse();
    }

    @Test
    void testCheckIngredientsForCookingWhenNotEnough() {
        assertThat(kitchenService.checkIngredientsForCooking("Pasta")).isTrue();
    }
}