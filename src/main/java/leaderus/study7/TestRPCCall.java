package leaderus.study7;


public class TestRPCCall {
	
	public static void main(String[] args) {
		RPCCaller rpcCaller = new RPCCaller();
		
		for(int i=0;i<2;i++){
			final short requestId = (short) i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					RPCResponse response = rpcCaller.doRPCCall(new RPCRequest((int) Thread.currentThread().getId()
							, requestId, new byte[10], new byte[20]));
					System.out.println(Thread.currentThread().getName()+"==="+response);
				}
			}).start();
		}
		
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
