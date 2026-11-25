package main.java.org.kata.store.domain;

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

	private String orderId;
	private String userId;
	private double price;
	private OrderStatus status;

	public Order(String orderId, String userId, double price) {
		this.setOrderId(orderId);
        this.setUserId(userId);
        this.setPrice(price);
        this.setStatus(OrderStatus.DRAFT);
		
	}

	public OrderStatus getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
