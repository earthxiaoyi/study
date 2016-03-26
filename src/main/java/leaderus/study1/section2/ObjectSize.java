package leaderus.study1.section2;

import java.lang.instrument.Instrumentation;

public class ObjectSize {
	private static volatile Instrumentation ins;
	public static void premain(String args, Instrumentation inst){
		ins = inst;
	}
	
	public static long getObjectSize(Object obj){
		return ins.getObjectSize(obj);
	}
	
	public static void main(String[] args) {
		Integer integer = new Integer(1);
		Boolean bool = new Boolean(false);
		long integerSize = ObjectSize.getObjectSize(integer);
		long boolSize = ObjectSize.getObjectSize(bool);
		System.out.println("integerSize="+integerSize);
		System.out.println("boolSize="+boolSize);
	}
}
