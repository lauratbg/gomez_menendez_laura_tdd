package org.kata.store.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/*
 * TODO: Tests unitarios de Order y de su flujo de estados.
 *
 * Empieza aquí: es la parte sin dependencias, la más fácil para arrancar el ciclo Red -> Green -> Refactor.
 *
 * Ideas de tests (uno por comportamiento, de uno en uno):
 *   - shouldStartInDraftStatus
 *   - shouldMoveFromDraftToProcessed
 *   - shouldNotSkipFromDraftToDispatched
 *   - shouldNotChangeStatusWhenDelivered
 *   - shouldRejectPriceLowerOrEqualThanZero  (prueba con @ParameterizedTest y @ValueSource)
 *
 * Recuerda:
 *   - Patrón AAA: // Arrange, // Act, // Assert.
 *   - Haz un commit en rojo, otro en verde y otro tras refactorizar.
 */
class OrderTest {
	
	@Test
    void shouldStartInDraftStatus() {
		// Arrange
		String orderId = "ORD-1";
        String userId = "USR-1";
        double price = 25.50;

        // Act
        Order order = new Order(orderId, userId, price);

        // Assert
        assertEquals(OrderStatus.DRAFT, order.getStatus());
    }
	
	@Test
    void shouldMoveFromDraftToProcessed() {
		// Arrange
		String orderId = "ORD-1";
        String userId = "USR-1";
        double price = 25.50;

        // Act
        Order order = new Order(orderId, userId, price);
        order.move(order);
        
        // Assert
        assertEquals(OrderStatus.PROCESSED, order.getStatus());
    }

    @Test
    void shouldMoveFromProcessedToDispatched() {
		// Arrange
		String orderId = "ORD-1";
        String userId = "USR-1";
        double price = 25.50;

        // Act
        Order order = new Order(orderId, userId, price);
        order.setStatus(OrderStatus.PROCESSED);
        order.move(order);
        
        // Assert
        assertEquals(OrderStatus.DISPATCHED, order.getStatus());
    }

    @Test
    void shouldMoveFromDispatchedToDelivered() {
		// Arrange
		String orderId = "ORD-1";
        String userId = "USR-1";
        double price = 25.50;

        // Act
        Order order = new Order(orderId, userId, price);
        order.setStatus(OrderStatus.DISPATCHED);
        order.move(order);
        
        // Assert
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenAdvancingDeliveredOrder() {
        // Arrange
        String orderId = "ORD-1";
        String userId = "USR-1";
        double price = 25.50;
        Order order = new Order(orderId, userId, price);
        order.setStatus(OrderStatus.DELIVERED);

        // Act & Assert
        assertThrows(
                IllegalStateException.class,
                () -> order.move(order)
        );
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
}
	
    @ParameterizedTest(name = "price={0} should throw exception")
    @ValueSource(doubles = {0.0, -0.01, -1.0, -50.0})
    void shouldRejectPriceLowerOrEqualThanZero(double invalidPrice) {
        // Arrange
        String orderId = "ORD-1";
        String userId = "USR-1";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Order(orderId, userId, invalidPrice)
        );
        assertNotNull(exception.getMessage());
        assertEquals("Order price must be greater than zero", exception.getMessage());
    }

	
}
