package org.kata.store.repository;

import java.util.Optional;

import org.kata.store.domain.Order;

/*
 * TODO: Almacén de pedidos.
 *
 * Igual que UserRepository: en los tests unitarios de los servicios se sustituye por un doble de prueba.
 *
 * Pista: con verify() de Mockito puedes comprobar que el servicio guarda el pedido con los datos correctos,
 * y con never() que NO lo guarda cuando la validación falla.
 */
public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(String orderId);
}
