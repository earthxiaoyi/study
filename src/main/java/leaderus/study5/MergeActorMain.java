package leaderus.study5;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MergeActorMain {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MergeSystem");
		ActorRef mergeSend = system.actorOf(Props.create(MergeSendActor.class));
		mergeSend.tell("sendMsg", mergeSend);
	}
}
