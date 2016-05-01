package leaderus.study4.leaderfollower;

import java.util.concurrent.locks.Lock;

public class LFThread extends Thread{
	public static final int LF_STATUS_PROCESSING=1;
	private FixedLFThreadPool pl;
	private EventHandler eventHandler;
	private EventProducer producer;
	private Lock lock;
	public LFThread(FixedLFThreadPool pl,EventHandler eventHandler,EventProducer producer,Lock lock){
		this.pl = pl;
		this.eventHandler = eventHandler;
		this.producer = producer;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		while(true){
			//等待成为LF线程
			lock.lock();
			//产生数据
			String fecthRequest = producer.fecthRequest();
			//选出一个LF线程
			lock.unlock();
			//处理数据
			eventHandler.handleData(fecthRequest);
		}
	}
	
}