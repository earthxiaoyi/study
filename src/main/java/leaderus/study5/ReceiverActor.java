package leaderus.study5;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;

import scala.util.Random;
import akka.actor.UntypedActor;

public class ReceiverActor extends UntypedActor{

	private String path="E:\\";
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message.equals("start")){
			String filePath = path+Sequnce.getSequnce()+".txt";
			//1.生成文件
			genaretorFile(filePath);
			//2.读取文件
			Integer[] arr = readFile(filePath);
			//3.取top 1000
			Integer[] topN = getTopN(arr, 10);
			//4.传递排序数据
			getSender().tell(topN, getSelf());
		}else{
			//不做处理
			unhandled(message);
		}
	}
	
	/**
	 * 读取文件中的数据
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public Integer[] readFile(String filePath) throws Exception{
		File file = new File(filePath);
		RandomAccessFile accessFile = new RandomAccessFile(file, "r");
		Integer[] arr = new Integer[100];
		if(!file.exists()){
			System.out.println("file is not existed!");
			return null;
		}
		String line = null;
		int i=0;
		while((line = accessFile.readLine())!=null){
			arr[i] = Integer.parseInt(line);
			i++;
		}
		return arr;
	}
	
	public void genaretorFile(String filePath) throws Exception{
		File file = new File(filePath);
		if(file.exists()){
			System.out.println("file is existed!");
			return;
		}
		Random random = new Random();
		RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
		for(int i=0;i<100;i++){
			accessFile.write((random.nextInt(1000000000)+"\r\n").getBytes("UTF-8"));
		}
		accessFile.close();
	}
	
	public Integer[] getTopN(Integer[] arr,int N){
		QuickSort sort = new QuickSort();
		sort.quickSort(arr, 0, arr.length-1);
		Integer[] topN = new Integer[N];
		for(int i=(arr.length-N);i<arr.length;i++){
			topN[--N] = arr[i];
		}
		return topN;
	}
}
