package leaderus.study;

public class Question5 {
	
	public static void main(String[] args) {
		ByteStore store = new ByteStore();
		MyItem item = new MyItem();
		item.setColor((byte)2);
		item.setType((byte)3);
		item.setPrice((byte)11);
		store.putMyItem(0, item);
		MyItem myItem = store.getMyItem(0);
		System.out.println(myItem);
	}
	
}

class ByteStore{
	
	private byte[] storeByteArry;
	
	public ByteStore(){
		this.storeByteArry = new byte[3000];
	}
	
	public void putMyItem(int index,MyItem item){
		int startPos = index*3;
		storeByteArry[startPos] = item.getType();
		storeByteArry[++startPos] = item.getColor();
		storeByteArry[++startPos] = item.getPrice();
	}
	
	public MyItem getMyItem(int index){
		int startPos = index*3;
		return new MyItem(storeByteArry[startPos],storeByteArry[++startPos],storeByteArry[++startPos]);
	}
	
}

class MyItem {
	byte type;
	byte color;
	byte price;
	
	public MyItem() {}
	
	public MyItem(byte type, byte color, byte price) {
		this.type = type;
		this.color = color;
		this.price = price;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public byte getColor() {
		return color;
	}
	public void setColor(byte color) {
		this.color = color;
	}
	public byte getPrice() {
		return price;
	}
	public void setPrice(byte price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MyItem [type=" + type + ", color=" + color + ", price=" + price
				+ "]";
	}
}