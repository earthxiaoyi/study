package leaderus.study4.leaderfollower;

import java.util.ArrayList;
import java.util.List;

public class FixedLFThreadPool {
	private Object monitor = new Object();
	private int poolSize = 10;
	private EventHandler eventHandler;
	private List<Thread> threadList = new ArrayList<Thread>();
	private EventProducer producer;
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
			LFThread lfThread = new LFThread(this,eventHandler,producer);
			lfThread.setName("线程"+i);
			lfThread.start();
			threadList.add(lfThread);
		}
		this.promoteToLeader();
	}
	
	public void waitToLeader(){
		synchronized (monitor) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void promoteToLeader(){
		synchronized (monitor) {
			monitor.notify();
		}
	}
}