package leaderus.study7;

import java.nio.ByteBuffer;


/**
 * 反序列化类
 * @author jiaming.jiang
 */
public class Decoder {
	
	/**
	 * 解码response
	 * @param data
	 * @return RPCResponse
	 */
	public synchronized static RPCResponse decodeRes(ByteBuffer resBuffer){
		resBuffer.flip();
		RPCResponse response = new RPCResponse();
		response.setSessionId(resBuffer.getInt());
		response.setRequestId(resBuffer.getShort());
		response.setRetCode(resBuffer.get());
		int errMsgSize = resBuffer.getInt();
		byte[] errMsg = new byte[errMsgSize];
		response.setErrMsg(errMsg);
		int retValueSize = resBuffer.getInt();
		byte[] retValue = new byte[retValueSize];
		response.setRetValue(retValue);
		return response;
	}
	
	/**
	 * 解码request
	 * @param reqBuffer
	 * @return RPCRequest
	 */
	public synchronized static RPCRequest decodeReq(ByteBuffer reqBuffer){
		reqBuffer.flip();
		RPCRequest request = new RPCRequest();
		request.setSessionId(reqBuffer.getInt());
		request.setRequestId(reqBuffer.getShort());
		int reqMethodSize = reqBuffer.getInt();
		byte[] reqMethod = new byte[reqMethodSize];
		request.setReqMethod(reqMethod);
		int requestParamsSize = reqBuffer.getInt();
		byte[] requestParams = new byte[requestParamsSize];
		request.setRequestParams(requestParams);
		return request;
	}
}
