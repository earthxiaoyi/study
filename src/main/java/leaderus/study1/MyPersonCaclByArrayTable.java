package leaderus.study1;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table.Cell;

public class MyPersonCaclByArrayTable {
	
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> list = new ArrayList<Integer>(1000000);
		for(int i=1;i<=1000000;i++){
			list.add(i);
		}
		List<String> columnsTables=Lists.newArrayList("person");
		ArrayTable<Integer, String, Person> table = ArrayTable.create(list,columnsTables);
		long start = System.currentTimeMillis();
		long memoryStart = Runtime.getRuntime().freeMemory();
		readPersondb(list, table);
		long end = System.currentTimeMillis();
		long memoryEnd = Runtime.getRuntime().freeMemory();
		System.out.println("读取文件耗时："+(end-start)+"毫秒，内存占用："+((memoryEnd-memoryStart)/1024/1024)+"M");
		groupStatistics(table);
		//基数排序
		PersonLimitMNByRadisSort.radisSort(table,10);
		//快速排序
		PersonLimitMNByQuickSort.quickSort(table);
	}
	/**
	 *进行分组计算
	 *年龄在  5-11岁、16-18岁 这两个区间范围内的 首字母在a-e之间的所有人的统计信息：
     *6-11岁的 总共XXX个 ，首字母为a的xxxx个，为b的yyy个，为c的zzz个，为e的ttt个
     *16-18岁的总共XXX个，首字母为a的xxxx个，为b的yyy个，为c的zzz个，为e的ttt个
	 */
	public static void groupStatistics(ArrayTable<Integer, String, Person> table){
		Set<Cell<Integer,String,Person>> set = table.cellSet();
		Iterator<Cell<Integer, String, Person>> iter = set.iterator();
		int age6_11=0;
		int age6_11_a=0;
		int age6_11_b=0;
		int age6_11_c=0;
		int age6_11_d=0;
		int age6_11_e=0;
		int age16_18=0;
		int age16_18_a=0;
		int age16_18_b=0;
		int age16_18_c=0;
		int age16_18_d=0;
		int age16_18_e=0;
		long start = System.currentTimeMillis();
		while(iter.hasNext()){
			Cell<Integer, String, Person> cell = iter.next();
			Person person = cell.getValue();
			String name = person.getName();
			if(person.getAge()>=6 && person.getAge()<=11){
				age6_11++;
				if(name.charAt(0)=='a'){
					age6_11_a++;
				}else if(name.charAt(0)=='b'){
					age6_11_b++;
				}else if(name.charAt(0)=='c'){
					age6_11_c++;
				}else if(name.charAt(0)=='d'){
					age6_11_d++;
				}else if(name.charAt(0)=='e'){
					age6_11_e++;
				}
			}
			if(person.getAge()>=16 && person.getAge()<=18){
				age16_18++;
				if(name.charAt(0)=='a'){
					age16_18_a++;
				}else if(name.charAt(0)=='b'){
					age16_18_b++;
				}else if(name.charAt(0)=='c'){
					age16_18_c++;
				}else if(name.charAt(0)=='d'){
					age16_18_d++;
				}else if(name.charAt(0)=='e'){
					age16_18_e++;
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("分组统计耗时："+(end-start)+"毫秒");
		System.out.println("6-11岁的总共"+age6_11+"个，首字母为a的"+age6_11_a+"个，为b的"+age6_11_b+"个，为c的"+age6_11_c+"个，为d的"+age6_11_d+"个，为e的"+age6_11_e+"个");
		System.out.println("16-18岁的总共"+age16_18+"个，首字母为a的"+age16_18_a+"个，为b的"+age16_18_b+"个，为c的"+age16_18_c+"个，为d的"+age16_18_d+"个，为e的"+age16_18_e+"个");
	}
	
	
	//读取文件进入内存
	public static void readPersondb(ArrayList<Integer> list,ArrayTable<Integer, String, Person> table) throws Exception{
		RandomAccessFile accessFile = new RandomAccessFile("C:\\leaderus\\data\\persondb.txt", "rw");
		accessFile.readLine();
		String row =null;
		int rowKey = 0;
		while((row=accessFile.readLine())!=null){
			StringTokenizer token = new StringTokenizer(row,"\t");
			Person person = new Person(Integer.parseInt(token.nextToken()), 
					token.nextToken(), Integer.parseInt(token.nextToken()));
			table.put(list.get(rowKey++), "person", person);
		}
	}
	
}
