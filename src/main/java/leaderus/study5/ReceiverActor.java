package leaderus.study5;

import akka.actor.UntypedActor;

public class ReceiverActor extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println("receiver msg:"+message);
		getSender().tell("呵呵呵", getSelf());
	}
	
}
