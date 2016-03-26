package leaderus.study3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumber {
	private static int number = 1;
	public static Lock lock = new ReentrantLock();
	public static Condition threeCondition = lock.newCondition();
	public static Condition sixCondition = lock.newCondition();
	public static void main(String[] args) {
		Thread threadB = new Thread(new WorkB());
		threadB.setName("ThreadB");
		Thread threadA = new Thread(new WorkA());
		threadA.setName("ThreadA");
	}
	
	private static class WorkA implements Runnable{
		public void run() {
			//打印1-3
			lock.lock();
			try {
				while(number<=3){
					System.out.println(Thread.currentThread().getName()+",number:"+number++);
				}
				threeCondition.signal();
			}finally{
				lock.unlock();
			}
			//打印7-9
			lock.lock();
			try {
				sixCondition.await();
				while(number<=9){
					System.out.println(Thread.currentThread().getName()+",number:"+number++);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			
		}
		
	}
	
	private static class WorkB implements Runnable{
		public void run() {
			lock.lock();
			try {
				while(number<=3){
					threeCondition.await();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			lock.lock();
			try {
				while(number<=6){
					System.out.println(Thread.currentThread().getName()+",number:"+number++);
				}
				sixCondition.signal();
			} finally{
				lock.unlock();
			}
		}
		
	}
}





