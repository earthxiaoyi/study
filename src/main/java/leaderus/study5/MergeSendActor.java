package leaderus.study5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

public class MergeSendActor extends UntypedActor{
	
	private int finishedReceivers=0;
	private int totalReceivers=10;
	private Lock lock = new ReentrantLock();
	private List<Integer> list = new ArrayList<>();
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
		if(message.equals("mergeStart")){
			for(int i=0;i<10;i++){
				router.route("start", getSelf());
			}
		}else{
			finishedReceivers++;
			mergeData((Integer[])message);
			if(finishedReceivers==totalReceivers){
				//merge
				QuickSort sort = new QuickSort();
				Integer[] array = list.toArray(new Integer[list.size()]);
				sort.quickSort(array, 0, array.length-1);
				for(int i = array.length-1;i>=array.length-10;i--){
					System.out.println(array[i]);
				}
			}
			
		}
	}
	
	public void mergeData(Integer[] arr){
		lock.lock();
		for(Integer i:arr){
			list.add(i);
		}
		lock.unlock();
	}

}
