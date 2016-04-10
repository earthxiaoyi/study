package leaderus.study4.leaderfollower;

public class Main {
	public static void main(String[] args) {
		PrintNumber eventHandler = new PrintNumber();
		EventProducer producer = new EventProducer();
		FixedLFThreadPool pl = new FixedLFThreadPool(eventHandler, producer);
		pl.start();
	}
}
