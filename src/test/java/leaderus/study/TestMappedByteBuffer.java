package leaderus.study;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class TestMappedByteBuffer {
	
	@Test
	public void mappedBuffer() throws IOException{
		File file = new File("E:\\迅雷下载\\CentOS-7-x86_64-Everything-1511.iso");
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		FileChannel channel = raf.getChannel();
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, Integer.MAX_VALUE, Integer.MAX_VALUE);
		System.out.println(buffer.get());
	}
}
