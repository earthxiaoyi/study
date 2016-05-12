package leaderus.study7;

import java.nio.ByteBuffer;

/**
 * 序列化类
 * @author jiaming.jiang
 *
 */
public class Encoder {
	
	/**
	 * 编码request
	 * @param reqBuffer
	 * @param request
	 */
	public synchronized static void encodeReq(ByteBuffer reqBuffer,RPCRequest request){
		reqBuffer.putInt(request.getSessionId());
		reqBuffer.putShort(request.getRequestId());
		reqBuffer.putInt(request.getReqMethod().length);
		reqBuffer.put(request.getReqMethod());
		reqBuffer.putInt(request.getRequestParams().length);
		reqBuffer.put(request.getRequestParams());
	}
	
	/**
	 * 编码response
	 * @param resBuffer
	 * @param response
	 */
	public synchronized static void encodeRes(ByteBuffer resBuffer,RPCResponse response){
		resBuffer.putInt(response.getSessionId());
		resBuffer.putShort(response.getRequestId());
		resBuffer.put(response.getRetCode());
		resBuffer.putInt(response.getErrMsg().length);
		resBuffer.put(response.getErrMsg());
		resBuffer.putInt(response.getRetValue().length);
		resBuffer.put(response.getRetValue());
	}
	
}
