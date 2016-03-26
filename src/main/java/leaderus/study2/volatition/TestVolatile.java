package leaderus.study2.volatition;

public class TestVolatile{
	private static volatile int number=0;
	
	private static class Volatitle1 implements Runnable{
		public void run() {
			int i=0;
			while(i<100000){
				i++;
				number++;
			}
		}
	}
	
	private static class Volatitle2 implements Runnable{
		public void run() {
			int i=0;
			while(i<100000){
				i++;
				number++;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Volatitle2 test1 = new Volatitle2();
		Volatitle1 test2 = new Volatitle1();
		Thread thread1 = new Thread(test1);
		Thread thread2 = new Thread(test2);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println("number value :"+number);
	}
}
