package leaderus.study;

import leaderus.study8.RowDataOffheapStorage;

import org.junit.Test;

public class TestRowDataOffheapStorage {
	
	@Test
	public void rowDataTest(){
		String fileName = "D:\\data.txt";
		RowDataOffheapStorage heap = new RowDataOffheapStorage(fileName);
		String data1 = "this is a";
		long position1 = heap.addRow(data1.getBytes());
		byte[] rowData1 = heap.getRow(position1, (short) data1.length());
		String string = new String(rowData1);
		System.out.println(string);
		
		String data2 = "this is B";
		long position2 = heap.addRow(data2.getBytes());
		byte[] rowData2 = heap.getRow(position2, (short) data2.length());
		System.out.println(new String(rowData2));
	}
	
}
