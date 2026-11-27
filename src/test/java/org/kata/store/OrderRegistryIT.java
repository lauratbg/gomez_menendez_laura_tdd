package org.kata.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.store.domain.Order;
import org.kata.store.domain.OrderStatus;
import org.kata.store.domain.User;
import org.kata.store.repository.OrderRepository;
import org.kata.store.repository.UserRepository;
import org.kata.store.service.OrderService;
import org.kata.store.service.UserService;

/*
 * TODO: Prueba de integración (repositorio en memoria obligatorio, TestContainers opcional).
 *
 * A diferencia de los tests unitarios, aquí NO se usan mocks: se conectan las piezas reales
 * (servicios + repositorios) y se prueba un flujo completo. Por ejemplo:
 *
 *   dar de alta un usuario -> crear un pedido -> avanzarlo hasta DELIVERED -> comprobar su estado final
 *
 * Opciones:
 *   - Repositorios en memoria (lo mínimo).
 *   - Extra: repositorios con PostgreSQL probados con Testcontainers
 *     (@Testcontainers, @Container y PostgreSQLContainer; las dependencias ya están en el pom.xml).
 *
 * Las clases que terminan en IT las ejecuta maven-failsafe-plugin con `mvn verify`, no con `mvn test`.
 */
class OrderRegistryIT {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private UserService userService;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        orderRepository = new InMemoryOrderRepository();
        userService = new UserService(userRepository);
        orderService = new OrderService(orderRepository, userRepository);
    }

    @Test
    void shouldRegisterUserCreateOrderAndAdvanceUntilDelivered() {
        // 1. Alta de usuario
        User user = userService.registerUser("87596258P", "Nombre", "Apellido");

        // 2. Crear pedido (queda en DRAFT)
        Order createdOrder = orderService.createOrder("ORD-1", user.getId(), 25.2);
        assertEquals(OrderStatus.DRAFT, createdOrder.getStatus());

        // 3. Avanzar hasta DELIVERED
        orderService.advance("ORD-1"); // PROCESSED
        orderService.advance("ORD-1"); // DISPATCHED
        Order deliveredOrder = orderService.advance("ORD-1"); // DELIVERED

        // 4. Comprobar estado final en el servicio y en el repositorio real
        assertEquals(OrderStatus.DELIVERED, deliveredOrder.getStatus());
        Order persistedOrder = orderRepository.findById("ORD-1").orElseThrow();
        assertEquals(OrderStatus.DELIVERED, persistedOrder.getStatus());

        // 5. Verificar que ya no admite más avances
        assertThrows(IllegalStateException.class, () -> orderService.advance("ORD-1"));
    }

    private static final class InMemoryUserRepository implements UserRepository {
        private final Map<String, User> users = new HashMap<>();

        @Override
        public void save(User user) {
            users.put(user.getId(), user);
        }

        @Override
        public boolean existsById(String id) {
            return users.containsKey(id);
        }
    }

    private static final class InMemoryOrderRepository implements OrderRepository {
        private final Map<String, Order> orders = new HashMap<>();

        @Override
        public void save(Order order) {
            orders.put(order.getOrderId(), order);
        }

        @Override
        public Optional<Order> findById(String orderId) {
            return Optional.ofNullable(orders.get(orderId));
        }
    }
}
