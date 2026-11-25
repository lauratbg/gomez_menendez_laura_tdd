package test.java.org.kata.store.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


import main.java.org.kata.store.domain.Order;
import main.java.org.kata.store.domain.OrderStatus;

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
		/// Arrange
		String orderId = "ORD-1";
        String userId = "USR-1";
        double price = 25.50;

        // Act
        Order order = new Order(orderId, userId, price);
        order.move();
        
        // Assert
        assertEquals(OrderStatus.PROCESSED, order.getStatus());
    }
	
	
}
