package leaderus.study;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

public class GenRandomersonDBFile {
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		String path = "D:/persondbfile.txt";
		int num = 1000*1000;
		genFile(path, num);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	/**
	 * 生成db file
	 * @param path
	 * @param num
	 * @throws Exception 
	 */
	public static void genFile(String path,int num) throws Exception{
		File file = new File(path);
		if(file.exists()){
			System.out.println("file is existed!");
			return;
		}
		Random random = new Random();
		RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
		accessFile.write("Id	name	age\r\n".getBytes("UTF-8"));
		for(int i=1;i<=num;i++){
			accessFile.write((i+"	"+genName()+"	"+random.nextInt(18)+"\r\n").getBytes("UTF-8"));
		}
		accessFile.close();
	}
	
	/**
	 * 生成姓名
	 * @return 姓名
	 */
	public static String genName(){
		String str= "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int length = str.length();
		for(int i=0;i<8;i++){
			buffer.append(str.charAt(random.nextInt(length)));
		}
		return buffer.toString();
	}
}
