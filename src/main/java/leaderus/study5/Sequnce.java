package leaderus.study5;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequnce {
	private static AtomicInteger atomic = new AtomicInteger(0);
	public static Integer getSequnce(){
		return atomic.incrementAndGet();
	}
}
