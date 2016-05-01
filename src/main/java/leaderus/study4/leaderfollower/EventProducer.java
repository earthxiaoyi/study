package leaderus.study4.leaderfollower;

import java.util.concurrent.atomic.AtomicInteger;

public class EventProducer{
	private AtomicInteger atomic = new AtomicInteger(0);
	/**
	 * 生产数据
	 * @return
	 */
	public String fecthRequest(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "呵呵"+atomic.incrementAndGet();
	}
}