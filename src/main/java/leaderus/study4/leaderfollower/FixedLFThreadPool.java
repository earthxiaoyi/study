package leaderus.study4.leaderfollower;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FixedLFThreadPool {
	private int poolSize = 10;
	private EventHandler eventHandler;
	private List<Thread> threadList = new ArrayList<Thread>();
	private EventProducer producer;
	private Lock lock = new ReentrantLock(true);
	
	public FixedLFThreadPool(EventHandler eventHandler,EventProducer producer){
		this.eventHandler = eventHandler;
		this.producer = producer;
	}
	
	//启动线程池开始工作
	public void start() {
		init();
	}
	
	//停止线程池
	public void stop(){
		for(Thread t:threadList){
			if(t.isAlive()){
				t.interrupt();
			}
		}
	}
	
	public void init(){
		for(int i=0;i<poolSize;i++){
			LFThread lfThread = new LFThread(this,eventHandler,producer,lock);
			lfThread.setName("线程"+i);
			lfThread.start();
			threadList.add(lfThread);
		}
	}
}