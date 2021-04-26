package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServantServiceImplTest {
    @Mock
    private KitchenServiceImpl kitchenService;

    @InjectMocks
    private ServantServiceImpl servantService;

    @Captor
    ArgumentCaptor<String> argument;

    @Test
    void testOrderCookingWhenOrderingIsPossible() {
//        when(kitchenService.cookDish(anyString())).thenReturn(Optional.of(20));
        doReturn(Optional.of(20)).when(kitchenService).cookDish(anyString());

        assertThat(servantService.orderCooking("Pizza")).isPresent().hasValue(20);
    }

    @Test
    void testOrderCookingWhenOrderingIsImpossible() {
        when(kitchenService.cookDish(anyString())).thenReturn(Optional.ofNullable(null));

        assertThat(servantService.orderCooking("Pizza")).isNotPresent();
    }

    @Test
    void testGiveOrderWhenReady() {
        when(kitchenService.checkDishStatus(anyInt())).thenReturn(true);
        when(kitchenService.provideDish()).thenReturn(new Dish("Pizza", new HashMap<>()));

        System.out.println(servantService.giveOrder(100).get().getTitle());
    }

    @Test
    void testGiveOrderWhenNotReady() {
//        when(kitchenService.checkDishStatus(anyInt())).thenReturn(false);
        given(kitchenService.checkDishStatus(anyInt())).willReturn(false);

        assertThat(servantService.giveOrder(anyInt())).isNotPresent();
    }

    @Test
    void testCheckingMockAndSpyLists() {
        List<String> mockList = mock(ArrayList.class);
        List<String> spyList = spy(ArrayList.class);

        mockList.add("Some string");
        spyList.add("Some string");

        assertThat(mockList.size()).isEqualTo(0);
        assertThat(spyList.size()).isEqualTo(1);
    }

    @Test
    void testCaptureArguments() {
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        List<String> mockList = mock(ArrayList.class);
        mockList.add("Some string #1");
        mockList.add("Some string #2");

        verify(mockList, atLeastOnce()).add(argument.capture());

        assertThat(argument.getAllValues().toString()).isEqualTo("[Some string #1, Some string #2]");
    }

    @Test
    void testOrderingMockAndSpyLists() {
        List<String> mockList = mock(ArrayList.class);
        List<String> spyList = spy(ArrayList.class);

        mockList.add("Some string");
        spyList.add("Some string");

        System.out.println(mockList.size());
        System.out.println(spyList.size());

        InOrder inOrder = inOrder(mockList, spyList);

        inOrder.verify(mockList).size();
        inOrder.verify(spyList).size();
    }
}