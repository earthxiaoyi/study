package leaderus.study2;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {
	public static TransferQueue<Integer> transferQueue = new LinkedTransferQueue<Integer>();

	public static void main(String[] args) {
		ThreadB b = new ThreadB(transferQueue);
		ThreadA a = new ThreadA(transferQueue);
		new Thread(a).start();
		new Thread(b).start();
	}

}

class ThreadB implements Runnable {

	private TransferQueue<Integer> transferQueue;

	public ThreadB(TransferQueue<Integer> transferQueue) {
		this.transferQueue = transferQueue;
	}

	public void run() {
		while (true) {
			try {
				Integer take = transferQueue.take();
				System.out.println(take);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class ThreadA implements Runnable {
	private TransferQueue<Integer> transferQueue;

	public void run() {
		while (true) {
			try {
				transferQueue.put(new Integer(1));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ThreadA(TransferQueue<Integer> transferQueue) {
		this.transferQueue = transferQueue;
	}
}
