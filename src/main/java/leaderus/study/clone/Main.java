package leaderus.study.clone;

import java.util.ArrayList;
import java.util.Date;

public class Main {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		shallowClone();
	}
	
	/**
	 * 浅克隆：生成一个需克隆对象的副本，不会克隆对象的引用
	 */
	public static void shallowClone() throws CloneNotSupportedException{
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setOrderNum(10001);
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(88888);
		orderItem.setItemList(new ArrayList<GoodsInfo>());
		order.setOrderItem(orderItem);
		//克隆
		Order order2 = (Order)order.clone();
		
		System.out.println("order 修改前："+order.getOrderNum());
		order.setOrderNum(10002);
		System.out.println("order 修改后："+order.getOrderNum());
		
		System.out.println("order2 修改前："+order2.getOrderNum());
		order2.setOrderNum(10003);
		
		System.out.println("order->orderItem 修改前："+order.getOrderItem().getItemId());
		//修改对象的引用
		order.getOrderItem().setItemId(99999);
		System.out.println("order->orderItem 修改后："+order.getOrderItem().getItemId());
		
		System.out.println("order2->orderItem 修改前："+order2.getOrderItem().getItemId());
	}
}
