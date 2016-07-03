package leaderus.study3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JM on 2016-4-2.
 */
public class RateLimitSQLQueueTest {

    public static void main(String[] args) throws InterruptedException {
        RateLimitingSQLQueue myQueue = new LinkedRateLimitingSQLQueue();
        myQueue.addLimit("select * from table a",2);
        myQueue.addLimit("select * from table b",1);
        myQueue.addLimit("update * from table b",1);
        
        ArrayList<String> list = new ArrayList<String>();
        list.add("select * from table a where id=xxxx");
        list.add("select * from table b limit xxxx");
        list.add("update * from table b where id=33");
        
        for(int i=0;i<20;i++){
            Test test = new Test(myQueue, list);
            new Thread(test).start();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

}
class Test implements Runnable{
    private RateLimitingSQLQueue myQueue;
    private List<String> sqlList;
    public Test(RateLimitingSQLQueue myQueue,List<String> sqlList){
        this.myQueue=myQueue;
        this.sqlList=sqlList;
    }
    public void run() {
    	//执行sql
    	String sql = sqlList.get(new Random().nextInt(3));
    	for(int i=0;i<50;i++){
    		boolean bool = myQueue.canRunImmediately(Thread.currentThread().getName(),sql);
    	}
    }
}
