package leaderus.study4.leaderfollower;

public class LFThread extends Thread{
	public static final int LF_STATUS_PROCESSING=1;
	private FixedLFThreadPool pl;
	private EventHandler eventHandler;
	private EventProducer producer;
	
	public LFThread(FixedLFThreadPool pl,EventHandler eventHandler,EventProducer producer){
		this.pl = pl;
		this.eventHandler = eventHandler;
		this.producer = producer;
	}
	
	@Override
	public void run() {
		while(true){
			//等待成为LF线程
			this.pl.waitToLeader();
			//产生数据
			String fecthRequest = producer.fecthRequest();
			//选出一个LF线程
			this.pl.promoteToLeader();
			//处理数据
			eventHandler.handleData(fecthRequest);
		}
	}
	
}