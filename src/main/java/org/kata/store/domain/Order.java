package org.kata.store.domain;

/*
 * TODO: Pedido de la tienda.
 *
 * Datos: id de pedido, id de usuario, estado (OrderStatus) y precio.
 *
 * Requisitos (escribe primero el test de cada uno):
 *   - Un pedido nuevo empieza en estado DRAFT.
 *   - El precio tiene que ser mayor que 0.
 *   - Cambia de estado siguiendo el flujo descrito en OrderStatus.
 *
 * Pista: para el precio, piensa en los casos límite (0, negativo, decimales).
 * Un @ParameterizedTest te ahorra repetir tests casi iguales.
 */
public class Order {

	private final String orderId;
	private final String userId;
	private final double price;
	private OrderStatus status;

	public Order(String orderId, String userId, double price) {
		this.orderId = orderId;
		this.userId = userId;
		this.price = price;
		this.status = OrderStatus.DRAFT;

		if(price<=0)
			throw new IllegalArgumentException("Order price must be greater than zero");
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getUserId() {
		return userId;
	}

	public double getPrice() {
		return price;
	}

	public void move(Order order) {
    if (order == null) {
        throw new IllegalArgumentException("Order must not be null");
    }
    OrderStatus currentStatus = order.getStatus();
    if (currentStatus == null) {
        throw new IllegalStateException("Order status must not be null");
    }
    order.setStatus(currentStatus.next());
}

	
}
