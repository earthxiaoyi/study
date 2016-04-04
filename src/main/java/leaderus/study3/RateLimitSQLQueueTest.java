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
            String runSQL = null;
            while((runSQL = myQueue.pullNextWaitingSQL(sql))!=null){
                myQueue.canRunImmediately(Thread.currentThread().getName(),runSQL);
            }
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
        myQueue.canRunImmediately(Thread.currentThread().getName(),sql);
    }
}
