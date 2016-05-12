package leaderus.study7;

import java.io.Serializable;
import java.util.Arrays;

public class RPCRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int sessionId;//回话id
	private short requestId;//请求序号，从0开始，达到最大值后复位为0
	private byte[] reqMethod;
	private byte[] requestParams;
	
	public RPCRequest(int sessionId, short requestId, byte[] reqMethod,
			byte[] requestParams) {
		super();
		this.sessionId = sessionId;
		this.requestId = requestId;
		this.reqMethod = reqMethod;
		this.requestParams = requestParams;
	}
	public RPCRequest() {
		super();
	}

	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public short getRequestId() {
		return requestId;
	}
	public void setRequestId(short requestId) {
		this.requestId = requestId;
	}
	public byte[] getReqMethod() {
		return reqMethod;
	}
	public void setReqMethod(byte[] reqMethod) {
		this.reqMethod = reqMethod;
	}
	public byte[] getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(byte[] requestParams) {
		this.requestParams = requestParams;
	}
	@Override
	public String toString() {
		return "RPCRequest [sessionId=" + sessionId + ", requestId="
				+ requestId + ", reqMethod=" + Arrays.toString(reqMethod)
				+ ", requestParams=" + Arrays.toString(requestParams) + "]";
	}
}
