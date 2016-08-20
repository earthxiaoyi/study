package leaderus.study;

public class Question9 {
	
	public static void main(String[] args) {
		ByteStore2 store = new ByteStore2(1000);
		MyItem item = new MyItem();
		item.setType((byte)11);
		item.setPrice((byte)10);
		item.setColor((byte)80);
		store.putMyItem(0, item);
		MyItem myItem = store.getMyItem(0);
		System.out.println(myItem);
	}
	
}

class ByteStore2{
	
	private int[] storeByteArry;
	private int size;
	public ByteStore2(int size){
		this.size = size;
		this.storeByteArry = new int[size];
	}
	
	public void putMyItem(int index,MyItem item){
		if(index > size){
			throw new RuntimeException("超过数组的长度");
		}
		byte[] b = new byte[4];
		b[0] = item.getType();
		b[1] = item.getColor();
		b[2] = item.getPrice();
		int intValue = this.byteToInt(b, 0);
		storeByteArry[index] = intValue;
	}
	
	public MyItem getMyItem(int index){
		int value = storeByteArry[index];
		byte[] b = intToByte(value);
		return new MyItem(b[0],b[1],b[2]);
	}
	
	public int byteToInt(byte[] b,int off){
		return ((b[off] & 0xff) << 24) 	 | 
			   ((b[off+1] & 0xff) << 16) | 
			   ((b[off+2] & 0xff) << 8)  |
			   ((b[off+3] & 0xff));
	}
	
	public byte[] intToByte(int value){
		byte[] b = new byte[4];
		b[0] = (byte) ((value>>24) & 0xff);
		b[1] = (byte) ((value>>16) & 0xff);
		b[2] = (byte) ((value>>8) & 0xff);
		b[3] = (byte) (value & 0xff);
		return b;
	}
}



