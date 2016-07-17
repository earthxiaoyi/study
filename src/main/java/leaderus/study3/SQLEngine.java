package leaderus.study3;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import scala.util.Random;

public class SQLEngine {
	
	private Map<String,Semaphore> limitMap;
	
	public SQLEngine(Map<String,Semaphore> limitMap){
		this.limitMap = limitMap;
	}
	
	public void runSQL(String ownerId,String sql){
		Semaphore semp = getSqlLimit(sql);
		new Thread(new Runnable() {
			@Override
			public void run() {
				long sleepTime = 1000;
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(ownerId+"，"+sql+",执行了："+sleepTime+" 毫秒");
				if(null != semp){
					semp.release();
				}
			}
		}).start();
	}
	
	/**
     * 查询sql限制
     */
    public Semaphore getSqlLimit(String runSQL){
    	Set<Entry<String, Semaphore>> entrySet = limitMap.entrySet();
    	Iterator<Entry<String, Semaphore>> iter = entrySet.iterator();
    	while(iter.hasNext()){
    		Entry<String, Semaphore> next = iter.next();
    		String limitSQL = next.getKey();
    		if(runSQL.startsWith(limitSQL)){
    			return next.getValue();
    		}
    	}
    	return null;
    }
}
