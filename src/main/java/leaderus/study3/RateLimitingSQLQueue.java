package leaderus.study3;

public interface RateLimitingSQLQueue {
	/**
	 * 符合该SQL前缀匹配的所有SQL，同时只能有N个在执行
	 * @param sql
	 * @param maxConcurrent
	 */
	public void adddLimit(String sql,int maxConcurrent);
	
	/**
	 * 表明属于指定owner的SQL是否可以立即执行，如果当前SQL满足限流条件，
	 * 则调用SQLEngine的runSQL方法异步执行（并更新SQL执行数），否则放入队列排队，等待通知
	 * @param ownerId
	 * @param sql
	 * @return 
	 */
	public boolean canRunImmediately(String ownerId,String sql);
	
	/**
	 * 该SQL执行完成后，尝试获取跟该SQL一起排队的其他带执行SQL，如果没有排队的，则返回NULL，
	 * @param sql
	 * @return
	 */
	public String pullNextWaitingSQL(String sql);
	
}
