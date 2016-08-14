package leaderus.study;

public class TestBit {
	
	public static void main(String[] args) {
		byte[] ba = new byte[3];
		ba[0] = 126;//1111110
		ba[1] = 111;//1101111
		ba[2] = 112;//1110000
		int i = byteArrayToInt(ba);//11111100 11011110 11100000 0000000
		System.out.println(i);
		System.out.println(byteToInt(ba));
	}
	
	public static int byteToInt(byte[] b){
		int res = 0;
		for(int i=b.length+1;i>1;i--){
			res |= ((b[i-1]&0xFF)<<(i*8));
		}
		return res;
	}
	
	public static int byteArrayToInt(byte[] b) {  
	    return   
	            (b[2] & 0xFF) << 8 |  
	            (b[1] & 0xFF) << 16 |  
	            (b[0] & 0xFF) << 24;  
	}  
}
