package leaderus.study7;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

public class RPCHandler implements Runnable{
	
	private BlockingQueue<RPCRequestEvent> requestQueue;
	private BlockingQueue<RPCResponseEvent> responseQueue;

	private ByteBuffer resDataBuffer = ByteBuffer.allocate(1024*1024*10);

	public RPCHandler(BlockingQueue<RPCRequestEvent> requestQueue2
			,BlockingQueue<RPCResponseEvent> responseQueue2){
		this.requestQueue = requestQueue2;
		this.responseQueue = responseQueue2;
	}
	
	public void doHandler(){
		try {
			while(true){
				doResponse(requestQueue.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void doResponse(RPCRequestEvent event){
		
		ByteBuffer reqBuffer = event.getRequestBuffer();
		RPCRequest request = Decoder.decodeReq(reqBuffer);
		
		RPCResponse response = new RPCResponse(request.getSessionId(), request.getRequestId()
				, (byte) '0', new byte[10], new byte[20]);
		ByteBuffer slice = resDataBuffer.slice();
		Encoder.encodeRes(slice, response);
		RPCResponseEvent responseEvent = new RPCResponseEvent(slice);
		try {
			responseQueue.put(responseEvent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			if(Thread.interrupted()){
				break;
			}
			//处理请求
			doHandler();
		}
	}
}
