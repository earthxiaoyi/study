package leaderus.study7;

import java.nio.ByteBuffer;

public class RPCRequestEvent {
	
	private ByteBuffer requestBuffer;
	
	public RPCRequestEvent(ByteBuffer requestBuffer){
		this.requestBuffer = requestBuffer;
	}
	
	public ByteBuffer getRequestBuffer() {
		return requestBuffer;
	}

	public void setRequestBuffer(ByteBuffer requestBuffer) {
		this.requestBuffer = requestBuffer;
	}
	
}
