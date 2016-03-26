package leaderus.study1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ArrayTable;

/**
 * 基数排序
 * @author jiaming.jiang
 */
public class PersonLimitMNByRadisSort {
	
	public static void radisSort(ArrayTable<Integer, String, Person> table,int radix){
		//准备数据
		Collection<Person> persons = table.values();
		Iterator<Person> iter = persons.iterator();
		int[] as = new int[1000000];
		int k=0;
		while(iter.hasNext()){
			Person person = iter.next();
			as[k++] =person.getId(); 
		}
		long start = System.currentTimeMillis();
		//进行排序
		RadixSort.sort(as, 7,radix);
		long end = System.currentTimeMillis();
		System.out.println("基数排序耗时："+(end-start)+"毫秒");
		//打印从90w开始100条记录
		for(int i=0;i<100;i++){
			Map<String, Person> row = table.row(900000+i);
			System.out.println(row);
		}
	}
}
