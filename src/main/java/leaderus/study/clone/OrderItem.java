package leaderus.study.clone;

import java.util.List;

public class OrderItem {
	
	private Integer itemId;
	
	private List<GoodsInfo> itemList;

	public List<GoodsInfo> getItemList() {
		return itemList;
	}

	public void setItemList(List<GoodsInfo> itemList) {
		this.itemList = itemList;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "OrderItem [itemList=" + itemList + "]";
	}
	
}
