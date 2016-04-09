package leaderus.study3;

/**
 * Created by JM on 2016-4-2.
 */
public class RateLimitSQLQueueTest {

    public static void main(String[] args) {
        RateLimitingSQLQueue myQueue = new LinkedRateLimitingSQLQueue();
        //myQueue.addLimit("select * from table a",2);
        //myQueue.addLimit("select * from table b",1);
        myQueue.addLimit("update * from table b",2);
        String sql = "update * from table b where id = 1";
        for(int i=0;i<20;i++){
            Test test = new Test(myQueue, sql);
            new Thread(test).start();
        }
    }

}
class Test implements Runnable{
    private RateLimitingSQLQueue myQueue;
    private String sql;
    public Test(RateLimitingSQLQueue myQueue,String sql){
        this.myQueue=myQueue;
        this.sql=sql;
    }
    public void run() {
    	//执行sql
        boolean bool = myQueue.canRunImmediately(Thread.currentThread().getName(),sql);
        //获取队列中相同的sql，如果有就执行，否则退出
        while(true){
        	String waiteSQL = myQueue.pullNextWaitingSQL(sql);
        	if(waiteSQL==null){
        		break;
        	}
            myQueue.canRunImmediately(Thread.currentThread().getName(),waiteSQL);
        }
    }
}
