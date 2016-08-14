package leaderus.study;

public class Question9 {
	
}

class ByteStore2{
	
	private int[] storeByteArry;
	
	public ByteStore2(){
		this.storeByteArry = new int[750];
	}
	
	public void putMyItem(int index,MyItem item){
		
	}
	
	public MyItem getMyItem(int index){
		return null;
	}
	
	//获取int的第N位
	public byte getIntBitByN(int flag,int n){
		return (byte)((flag>>n)&0xff);
	}
}



