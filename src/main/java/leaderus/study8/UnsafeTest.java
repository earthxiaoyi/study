package leaderus.study8;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeTest {
	
	private static Unsafe unsafe = null;
	
	public static void main(String[] args) throws Exception{
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		unsafe = (Unsafe)f.get(null);
		
		long pos = getAddr(new Object());
		System.out.println(pos);
	}
	
	/**
	 * 获取对象在内存中的偏移量
	 * @param obj
	 * @return offset
	 */
	private static long getAddr(Object obj) {  
	    Object[] array = new Object[]{obj};  
	    long baseOffset = unsafe.arrayBaseOffset(Object[].class);  
	    return unsafe.getLong(array, baseOffset);  
	}
}
