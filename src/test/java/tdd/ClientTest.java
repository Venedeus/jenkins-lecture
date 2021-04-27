package tdd;

import model.KitchenService;
import model.KitchenServiceImpl;
import model.ServantService;
import model.ServantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientTest {
    private Client client;
    @Mock
    private KitchenServiceImpl kitchen;
    @InjectMocks
    private ServantServiceImpl servant;

    @BeforeEach
    void setUp() {
        client = new Client("Jack", servant);
    }

    @Test
    void testInstanceOfClassClient() {
        assertThat(client.getClass().getName().equals(Client.class));
    }

    @Test
    void testInstanceNotNullWhenCreated() {
        assertThat(client).isNotNull();
    }

    @Test
    void testSetNameToClient() {
        assertThat(client.getName()).isEqualTo("Jack");
    }

    @Test
    void testSetNameIfNull() {
        assertThatThrownBy(() -> new Client(null, servant)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testOrderAvailableDish() {
        when(servant.orderCooking("Pizza")).thenReturn(Optional.of(20));
        assertThat(client.orderDish("Pizza")).isPresent().hasValue(20);
    }

    @Test
    void testOrderNotAvailableDish() {
        when(servant.orderCooking("Pasta")).thenReturn(Optional.ofNullable(null));
        assertThat(client.orderDish("Pasta")).isNotPresent();
    }
}
