package leaderus.study3;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SQLEngine {
	
	public void runSQL(String ownerId,String sql){
		final String runSQL = sql;
		//启动一个线程异步执行SQL，随机Sleep 10-1000毫秒表示执行时间
		new Thread(
				new Runnable(){
					public void run() {
						int time = new Random().nextInt(1000);
						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						System.out.println("SQL:"+runSQL+",执行了"+time+"毫秒！");
					}
				}
		).start();
		//执行完之后加1
		AtomicInteger atomicInteger = RateLimitingSQLQueue.threadLocal.get();
		atomicInteger.incrementAndGet();
	}
}