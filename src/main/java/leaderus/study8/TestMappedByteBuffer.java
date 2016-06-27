package leaderus.study8;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class TestMappedByteBuffer {
	
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\a.txt");
		//boolean createNewFile = file.createNewFile();
		//if(createNewFile){
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			/*FileChannel wfc = raf.getChannel();
			MappedByteBuffer mbbo = wfc.map(FileChannel.MapMode.READ_WRITE, 0, 300);
			//写文件
			int i=0;
			while(i<10){
				mbbo.putInt(i);
				i++;
			}
			wfc.close();*/
			
			//读取文件
			FileChannel rfc = raf.getChannel();
			MappedByteBuffer mbbi = rfc.map(FileChannel.MapMode.READ_WRITE, 0, 300);
			//mbbi.flip();
			while(mbbi.limit()>0){
				int value = mbbi.getInt();
				System.out.println(value);
			}
			rfc.close();
			
			raf.close();
		//}
		
		
	}
	
	@Test
	public void writeByteBuffer() throws IOException{
		RandomAccessFile raf = new RandomAccessFile("D:\\b.txt", "rw");
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byteBuffer.putInt(100);
		byteBuffer.flip();
		while(byteBuffer.hasRemaining()){
			raf.write(byteBuffer.getInt());
		}
		raf.close();
	}
	
	@Test
	public void writeMbb() throws Exception{
		RandomAccessFile raf = new RandomAccessFile("D:\\c.txt", "rw");
		FileChannel fc = raf.getChannel();
		
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1000);
		
		int i=1;
		while(i<=100){
			mbb.putInt(i);
			i++;
			mbb.flip();
		}
		fc.close();
		raf.close();
	}
	
	@Test
	public void wrFile() throws IOException{
		RandomAccessFile raf = new RandomAccessFile("D:\\c.txt", "rw");
		FileChannel channel = raf.getChannel();
		long size = channel.size();
		System.out.println(size);
	}
	
	@Test
	public void copyFile() throws IOException{
		RandomAccessFile raf = 
				new RandomAccessFile("D:\\a.txt", "rw");
		RandomAccessFile copyRaf = 
				new RandomAccessFile("D:\\b.txt", "rw");
		FileChannel channel = raf.getChannel();
		long fileSize = channel.size();
		int res=0;
		//分段映射文件
		if(true){
			int part = (int) (fileSize/3);
			int partSize = 0;
			long startPos = 0;
			long endPos = 0;
			while(part>=0){
				MappedByteBuffer mbbin;
				if(part == 0){
					startPos = partSize;
					endPos = partSize+res;
				} else {
					startPos = partSize;
					endPos = partSize + 3;
				}
				mbbin = channel.map(FileChannel.MapMode.READ_ONLY
						, startPos, endPos);
				FileChannel copyChannel = copyRaf.getChannel();
				MappedByteBuffer copyMbbout = copyChannel.map(FileChannel.MapMode.READ_ONLY
						, startPos, endPos);
				mbbin.flip();
				while(mbbin.hasRemaining()){
					ByteBuffer allocate = ByteBuffer.allocate(1024);
					copyMbbout.put(mbbin.get(allocate.array()));
				}
				part--;
				//copyChannel.close();
			}
			channel.close();
			copyRaf.close();
			raf.close();
			
		}
	}
	
	@Test
	public void test(){
		System.out.println(10%5);
		System.out.println(10/5);
	}
}
