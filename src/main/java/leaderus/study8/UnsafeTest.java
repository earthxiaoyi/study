package leaderus.study8;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeTest {
	public static void main(String[] args) throws Exception{
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe)f.get(null);
		unsafe.allocateInstance(null);
	}
}
