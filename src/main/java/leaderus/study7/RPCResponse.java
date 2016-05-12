package leaderus.study7;

import java.io.Serializable;
import java.util.Arrays;

public class RPCResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int sessionId;
	private short requestId;
	private byte retCode;//返回码，字符'0'为正常，其他为错误码
	private byte[] errMsg;//如果有错误，则提示消息。
	private byte[] retValue;//返回值
	
	public RPCResponse(int sessionId, short requestId, byte retCode,
			byte[] errMsg, byte[] retValue) {
		super();
		this.sessionId = sessionId;
		this.requestId = requestId;
		this.retCode = retCode;
		this.errMsg = errMsg;
		this.retValue = retValue;
	}
	public RPCResponse() {
		super();
		// TODO Auto-generated constructor stub
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
	public byte getRetCode() {
		return retCode;
	}
	public void setRetCode(byte retCode) {
		this.retCode = retCode;
	}
	public byte[] getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(byte[] errMsg) {
		this.errMsg = errMsg;
	}
	public byte[] getRetValue() {
		return retValue;
	}
	public void setRetValue(byte[] retValue) {
		this.retValue = retValue;
	}
	@Override
	public String toString() {
		return "RPCResponse [sessionId=" + sessionId + ", requestId="
				+ requestId + ", retCode=" + retCode + ", errMsg="
				+ Arrays.toString(errMsg) + ", retValue="
				+ Arrays.toString(retValue) + "]";
	}
}
