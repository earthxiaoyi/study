package leaderus.study5;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

public class MergeSendActor extends UntypedActor{
	
	Router router;
	{
		List<Routee> routees = new ArrayList<Routee>();
		for(int i=0;i<10;i++){
			ActorRef r = getContext().actorOf(Props.create(ReceiverActor.class));
			getContext().watch(r);
			routees.add(new ActorRefRoutee(r));
		}
		router = new Router(new RoundRobinRoutingLogic(), routees);
	}
	public MergeSendActor(){
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message.equals("sendMsg")){
			for(int i=0;i<100;i++){
				router.route("eee"+i, getSelf());
			}
		}
	}

}
