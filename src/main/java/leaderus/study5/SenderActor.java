package leaderus.study5;

import akka.actor.UntypedActor;

public class SenderActor extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("sender msg:"+message);
	}
	
}
