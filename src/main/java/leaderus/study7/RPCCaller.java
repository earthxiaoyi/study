package leaderus.study7;

import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RPCCaller{
	
	//默认10m
	private ByteBuffer requestDataBuffer = ByteBuffer.allocate(1024*1024*10);
	private BlockingQueue<RPCRequestEvent> requestQueue = new ArrayBlockingQueue<RPCRequestEvent>(100);
	private BlockingQueue<RPCResponseEvent> responseQueue = new ArrayBlockingQueue<RPCResponseEvent>(100);
	
	public RPCCaller(){
		Thread handler = new Thread(new RPCHandler(requestQueue, responseQueue));
		handler.start();
	}
	
	public synchronized RPCResponse doRPCCall(RPCRequest request){
		RPCResponse response = null;
		try {
			int sessionId=request.getSessionId();
			ByteBuffer slice = requestDataBuffer.slice();
			Encoder.encodeReq(slice, request);//序列化
			RPCRequestEvent event = new RPCRequestEvent(slice);
			while(true){
				if(requestQueue.offer(event)){
					break;
				}
			}
			
			while(true){
				RPCResponseEvent peek = responseQueue.peek();
				if(peek!=null){
					RPCResponse peekRes = Decoder.decodeRes(peek.getResponseBuffer());
					if(sessionId==peekRes.getSessionId()){
						RPCResponseEvent responseEvent = responseQueue.take();
						response = Decoder.decodeRes(responseEvent.getResponseBuffer());
						break;
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		return response;
	}
}
