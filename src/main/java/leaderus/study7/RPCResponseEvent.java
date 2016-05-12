package leaderus.study7;

import java.nio.ByteBuffer;

public class RPCResponseEvent {
	
	private ByteBuffer responseBuffer;
	
	public RPCResponseEvent(ByteBuffer responseBuffer){
		this.responseBuffer = responseBuffer;
	}

	public ByteBuffer getResponseBuffer() {
		return responseBuffer;
	}

	public void setResponseBuffer(ByteBuffer responseBuffer) {
		this.responseBuffer = responseBuffer;
	}
	
}
