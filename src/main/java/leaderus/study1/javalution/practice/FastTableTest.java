package leaderus.study1.javalution.practice;

import java.util.ArrayList;
import java.util.List;


import javolution.util.FastTable;

/**
 * javolution fasttable测试
 * @author JM
 *
 */
public class FastTableTest {
	
	
	public static void main(String args[]){
		List<Integer> arrayList = new ArrayList<Integer>();
		FastTable<Integer> fastTable = new FastTable<Integer>();
		System.out.println("========fasttable插入数据=========");
		ListAdd(1000, fastTable);
		ListAdd(10000, fastTable);
		ListAdd(100000, fastTable);
		ListAdd(1000000, fastTable);
		ListAdd(10000000, fastTable);
		//ListAdd(100000000, fastTable);
		System.out.println("========arrayList插入数据=========");
		ListAdd(1000, arrayList);
		ListAdd(10000, arrayList);
		ListAdd(100000, arrayList);
		ListAdd(1000000, arrayList);
		ListAdd(10000000, arrayList);
		//ListAdd(100000000, arrayList);
	}
	
	public static void ListAdd(int number,List<Integer> list){
		long startTime = System.currentTimeMillis();
		for(int i=1;i<number;i++){
			list.add(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(number+"条数据，插入数据耗时："+(endTime-startTime)+" 毫秒");
	}
	
}
