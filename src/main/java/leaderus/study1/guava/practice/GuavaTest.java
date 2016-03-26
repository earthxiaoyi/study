package leaderus.study1.guava.practice;

import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;

public class GuavaTest {
	
	@Test
	public void multisetTest(){
		HashMultiset<Integer> multiset = HashMultiset.create();
		//multisetAdd(1000, multiset);
		//multisetAdd(10000, multiset);
		//multisetAdd(100000, multiset);
		multisetAdd(1000000, multiset);
		long startTime = System.currentTimeMillis();
		System.out.println(multiset.count(9999));
		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(multiset.size());
	}
	
	public void multiMapTest(){
		
		HashMultimap<String, Object> hashMultiMap = HashMultimap.create();
		
	}
	
	public static void multisetAdd(int number,Collection<Integer> collection){
		long startTime = System.currentTimeMillis();
		for(int i=1;i<number;i++){
			collection.add(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(number+"条数据，耗时："+(endTime-startTime));
	}
	
}
