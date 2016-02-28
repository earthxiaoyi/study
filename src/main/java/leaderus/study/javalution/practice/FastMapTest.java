package leaderus.study.javalution.practice;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import javolution.util.FastMap;

/**
 * javolution fastmap练习
 * @author JM
 *
 */
public class FastMapTest {
	
	@Test
	public void testAdd(){
		System.out.println("======= fastMap 插入数据 =======");
		fastMapadd(1000);
		fastMapadd(10000);
		fastMapadd(100000);
		fastMapadd(1000000);
		fastMapadd(10000000);
		System.out.println("======= hashMap 插入数据 =======");
		hashMapAdd(1000);
		hashMapAdd(10000);
		hashMapAdd(100000);
		hashMapAdd(1000000);
		hashMapAdd(10000000);
	}
	
	
	
	/**
	 * fast map 插入数据
	 * @param number
	 */
	public static void fastMapadd(int number){
		long startTime = System.currentTimeMillis();
		FastMap<String,Object> fastMap = new FastMap<String,Object>();
		for(int i=1;i<=number;i++){
			fastMap.put("i="+i, i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(number+"条数据，插入数据耗时："+(endTime-startTime)+" 毫秒");
	}
	
	public static void hashMapAdd(int number){
		long startTime = System.currentTimeMillis();
		Map<String,Object> fastMap = new HashMap<String,Object>();
		for(int i=1;i<=number;i++){
			fastMap.put("i="+i, i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(number+"条数据，插入数据耗时："+(endTime-startTime)+" 毫秒");
	}
	
	
	
}
