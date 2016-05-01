package leaderus.study5;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorMain {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("helloWorldSystem");
		ActorRef receiver = system.actorOf(Props.create(ReceiverActor.class),"receiver");
		ActorRef sender = system.actorOf(Props.create(SenderActor.class),"sender");
		receiver.tell("嘿嘿嘿", sender);
	}
}
