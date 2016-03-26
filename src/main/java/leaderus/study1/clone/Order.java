package leaderus.study1.clone;

import java.util.Date;

public class Order implements Cloneable{
	
	private Integer orderNum;
	private Date orderDate;
	private OrderItem orderItem;
	
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", orderDate=" + orderDate
				+ ", orderItem=" + orderItem + "]";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
