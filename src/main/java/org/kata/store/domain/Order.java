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
		if(status.equals(OrderStatus.DRAFT)){
			 order.setStatus(OrderStatus.PROCESSED);
		}
		if(status.equals(OrderStatus.PROCESSED)){
			 order.setStatus(OrderStatus.DISPATCHED);
		}
		if(status.equals(OrderStatus.DELIVERED)){
			order.setStatus(OrderStatus.DELIVERED);
		}
	}

	
}
