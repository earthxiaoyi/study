package leaderus.study3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by JM on 2016-4-2.
 */
public class LinkedRateLimitingSQLQueue implements RateLimitingSQLQueue{
	
	//限制sql执行集合
	private final Map<String,Semaphore> limitMap = new HashMap<String,Semaphore>();
	//限制sql执行队列
	private final Map<String, LinkedBlockingQueue<String>> limitQueue = 
			new HashMap<String,LinkedBlockingQueue<String>>();
	
	private SQLEngine sqlEngine = new SQLEngine(limitMap);
	
    public void addLimit(String sql, int maxConcurrent) {
    	limitMap.put(sql, new Semaphore(maxConcurrent,true));
    	limitQueue.put(sql, new LinkedBlockingQueue<String>());
    }
    
    public boolean canRunImmediately(String ownerId, String sql) {
    	Semaphore semp = getSqlLimit(sql);
    	if(null != semp){
    		if(semp.tryAcquire()){
    			sqlEngine.runSQL(ownerId, sql);
    			String waitingSQL;
				while((waitingSQL = pullNextWaitingSQL(sql)) != null){
    				sqlEngine.runSQL(ownerId, waitingSQL);
    			}
    			return true;
    		}else{
    			LinkedBlockingQueue<String> queue = getLimitQueue(sql);
    			if(queue!=null){
    				queue.offer(sql);
    				return false;
    			}
    		}
    	}
    	//无限制sql，直接执行
    	sqlEngine.runSQL(ownerId, sql);
    	return true;
    }

    public String pullNextWaitingSQL(String sql) {
    	LinkedBlockingQueue<String> queue = getLimitQueue(sql);
    	return queue.poll();
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
    
    /**
     * 获取限制队列
     * @param runSQL
     * @return
     */
    public LinkedBlockingQueue<String> getLimitQueue(String runSQL){
    	Set<Entry<String, LinkedBlockingQueue<String>>> entrySet = limitQueue.entrySet();
    	Iterator<Entry<String, LinkedBlockingQueue<String>>> iter = entrySet.iterator();
    	while(iter.hasNext()){
    		Entry<String, LinkedBlockingQueue<String>> next = iter.next();
    		String limitSQL = next.getKey();
    		if(runSQL.startsWith(limitSQL)){
    			return next.getValue();
    		}
    	}
    	return null;
    }
}


