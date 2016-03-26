package leaderus.study2.objectsize;

import java.lang.instrument.Instrumentation;

public class MyAgent {
	public static void premain(String args, Instrumentation inst) {
		Object obj = new Object();
		Integer value = new Integer(1);
		Boolean bool = new Boolean(false);
		System.out
				.println("Bytes used by Integer:" + inst.getObjectSize(value));
		System.out.println("Bytes used by Boolean:" + inst.getObjectSize(bool));
		System.out.println("Bytes used by Object:" + inst.getObjectSize(obj));
		System.out.println("Bytes used by MyObject:"
				+ inst.getObjectSize(new MyObject()));
		System.out.println("Bytes used by MyObject:"
				+ inst.getObjectSize(new MyObject()));
	}

	public static void main(String[] args) {
		System.out.println("main is running~");
	}
}
