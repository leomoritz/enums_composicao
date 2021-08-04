package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private static Date moment = new Date();
	private OrderStatus status;

	List<OrderItem> items = new ArrayList<>();

	private Client client;

	public Order() {

	}

	public Order(OrderStatus status, Client client) {
		this.status = status;
		this.client = client;
	}

	public String getMoment() {
		return sdf.format(moment);
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getOrderItems() {
		return items;
	}

	public void addItem(OrderItem item) {
		items.add(item);
	}

	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	public Double total() {
		double sum = 0.0;
		for (OrderItem oi : items) {
			sum += oi.subTotal();
		}
		return sum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: " + getMoment() + "\n");
		sb.append("Order status: " + getStatus() + "\n");
		sb.append("Client: " + client.getName());
		sb.append(" (" + client.getBirthday() + ")");
		sb.append(" - " + client.getEmail());
		sb.append("\nOrder Items: \n");
		for (OrderItem oi : items) {
			sb.append(oi.getProduct().getName() + ", ");
			sb.append(String.format("$%.2f", oi.getProduct().getPrice()) + ", ");
			sb.append("Quantity: " + oi.getQuantity() + ", ");
			sb.append(String.format("Subtotal: $%.2f", oi.subTotal()) + "\n");
		}
		sb.append(String.format("Total price: $%.2f", total()));
		return sb.toString();
	}

}
