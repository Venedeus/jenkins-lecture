package cases.mockito;

import model.ServantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServantServiceMockitoTest {
    private ServantService servantService = Mockito.mock(ServantService.class);

    @Test
    public void testDishService() {

    }
}
