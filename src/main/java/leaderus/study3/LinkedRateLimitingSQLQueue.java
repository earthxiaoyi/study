package leaderus.study3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Set;
/**
 * Created by JM on 2016-4-2.
 */
public class LinkedRateLimitingSQLQueue implements RateLimitingSQLQueue{

    private Map<String,AtomicInteger> sqlLimitMap = new HashMap<String, AtomicInteger>();
    private SQLEngine sqlEngine = new SQLEngine();
    private SQLLinkList link = new SQLLinkList();

    public void addLimit(String sql, int maxConcurrent) {
        //添加sql限制条件
        if(sql!=null && !sql.equals("")){
            sqlLimitMap.put(sql, new AtomicInteger(maxConcurrent));
        }
    }

    /**
     * 通过执行sql，获取限制条件
     * @param sql 执行sql
     * @return entry
     */
    public Map.Entry<String,AtomicInteger> getLimitSql(String sql){
        //便利map
        Set<Map.Entry<String,AtomicInteger>> set = sqlLimitMap.entrySet();
        Iterator<Map.Entry<String,AtomicInteger>> iter = set.iterator();
        while(iter.hasNext()) {
            Map.Entry<String, AtomicInteger> entry = iter.next();
            String sqlKey = entry.getKey();
            //有限制条件
            if (sql.startsWith(sqlKey)) {
                return entry;
            }
        }
        return null;
    }

    public boolean canRunImmediately(String ownerId, String sql) {
        Map.Entry<String, AtomicInteger> limitSql = getLimitSql(sql);
        if(limitSql!=null){
            AtomicInteger maxConcurrent = limitSql.getValue();
            if(maxConcurrent.intValue() != 0){
                //执行次数减一
                maxConcurrent.decrementAndGet();
                sqlEngine.runSQL(ownerId, sql);
                //设置
                threadLocal.set(maxConcurrent);
                return true;
            }else{
                //放入队列中等待
                link.add(sql,limitSql.getKey());
                System.out.println("sql进入队列："+sql);
                return false;
            }
        }else{
            //没有限制条件,执行sql
            sqlEngine.runSQL(ownerId,sql);
            return true;
        }
    }

    public String pullNextWaitingSQL(String sql) {
        return link.getRunSQLBySameSQL(sql);
    }

    private class SQLLinkList{
        private Node firstNode;

        public void add(String runSQL,String limitSQL){
            Node newNode = new Node(runSQL, limitSQL);
            newNode.setNext(newNode);
            firstNode = newNode;
        }

        /**
         * 获取队列中排队的sql
         * @param runSQL 执行的sql
         * @return 执行的sql
         */
        public String getRunSQLBySameSQL(String runSQL){
            Node currentNode = firstNode;
            while(currentNode != null && currentNode.getLimitSQL() != null){
                if(!runSQL.startsWith(currentNode.getLimitSQL())){
                    currentNode = currentNode.getNext();
                }else{
                    //删除节点
                    remove(currentNode);
                    return currentNode.getRunSQL();
                }
            }
            return null;
        }

        /**
         * 删除节点
         * @param node 需要删除的节点
         */
        public void remove(Node node){
            Node previNode = firstNode;
            Node needNode = node;
            if(!previNode.getRunSQL().equals(needNode.getRunSQL())){
                //查找需要删除节点的前置节点
                while(!previNode.getNext().getRunSQL().equals(needNode.getRunSQL())){
                    previNode = previNode.getNext();
                }
                previNode.setNext(needNode.getNext());
            }else{
                firstNode = null;
            }
        }
    }
}


class Node{
    private String runSQL;//执行的sql
    private String limitSQL;//限制的sql
    private Node next;//下一个节点

    public Node(String runSQL, String limitSQL) {
        this.runSQL = runSQL;
        this.limitSQL = limitSQL;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "runSQL='" + runSQL + '\'' +
                ", limitSQL='" + limitSQL + '\'' +
                ", next=" + next +
                '}';
    }

    public void setRunSQL(String runSQL) {
        this.runSQL = runSQL;
    }

    public void setLimitSQL(String limitSQL) {
        this.limitSQL = limitSQL;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getRunSQL() {
        return runSQL;
    }

    public String getLimitSQL() {
        return limitSQL;
    }

    public Node getNext() {
        return next;
    }
}
