package leaderus.study7;

import java.nio.ByteBuffer;

public class TestDeEnCode {
	public static void main(String[] args) {
		ByteBuffer reqBuffer = ByteBuffer.allocate(10000);
		RPCRequest request = new RPCRequest();
		request.setReqMethod(new byte[10]);
		short a = 1;
		request.setRequestId(a);
		request.setRequestParams(new byte[20]);
		request.setSessionId(222);
		Encoder.encodeReq(reqBuffer, request);
		
		RPCRequest decodeReq = Decoder.decodeReq(reqBuffer);
	}
}
