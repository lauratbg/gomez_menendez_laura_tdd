package org.kata.store.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kata.store.domain.Order;
import org.kata.store.domain.OrderStatus;
import org.kata.store.domain.User;
import org.kata.store.repository.OrderRepository;
import org.kata.store.repository.UserRepository;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * TODO: Tests unitarios de OrderService con Mockito.
 *
 * UserRepository y OrderRepository son dependencias: se sustituyen por dobles de prueba.
 *
 * Ideas de tests:
 *   - shouldCreateOrderInDraftForExistingUser      -> stub del usuario existente + verify del save
 *   - shouldRejectOrderForUnknownUser              -> stub: existe = false + assertThrows
 *   - shouldNotSaveOrderWhenUserDoesNotExist       -> verify(orderRepository, never()).save(any())
 *   - shouldRejectOrderWithInvalidPrice
 *   - shouldAdvanceOrderToNextStatus
 *   - shouldRejectInvalidStatusTransition
 *
 * Recuerda la diferencia:
 *   - Stub: la dependencia devuelve un valor fijo y compruebas el resultado (estado).
 *   - Mock: compruebas con verify que el servicio llamó a la dependencia (comportamiento).
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository,userRepository);
    }

    @Test
    void shouldCreateOrderInDraftForExistingUser() {
        // Arrange
        String orderId = "ORD-1";
        String userId = "87596258P";
        double price = 25.2;
        when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        Order order = orderService.createOrder(orderId, userId, price);

        // Assert
        assertNotNull(order);
        assertEquals(orderId, order.getOrderId());
        assertEquals(userId, order.getUserId());
        assertEquals(price, order.getPrice());
        assertEquals(OrderStatus.DRAFT, order.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void shouldRejectOrderForUnknownUser() {
        // Arrange
        String orderId = "ORD-1";
        String unknownUserId = "noname";
        double price = 25.2;
        when(userRepository.existsById(unknownUserId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(orderId, unknownUserId, price)
        );
        assertNotNull(exception.getMessage());
        assertEquals("User does not exist: " + unknownUserId, exception.getMessage());
    }
}
