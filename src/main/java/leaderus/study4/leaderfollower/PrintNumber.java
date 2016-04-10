package leaderus.study4.leaderfollower;

public class PrintNumber implements EventHandler{

	public void handleData(String jsonRequest) {
		System.out.println(Thread.currentThread().getName()+","+"数字为："+jsonRequest);
	}

}
