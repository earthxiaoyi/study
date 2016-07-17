package leaderus.study8;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;


//堆外存储，一次性使用，即记录保存完成后，读取完成后， 就关闭删除文件，不会修改数据
public class RowDataOffheapStorage {
	
	private MappedByteBuffer buffer = null;
	
	private CloseMappedByteBuffer close = new CloseMappedByteBuffer();
	
	private AtomicLong heapOffset = new AtomicLong(0L);
	
	public RowDataOffheapStorage(String fileName) {
		// 创建一个MappedByteBuffer 的映射文件存储，
		File dataFile = new File(fileName);
		try {
			RandomAccessFile accFile = new RandomAccessFile(dataFile, "rw");
			FileChannel channel = accFile.getChannel();
			buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, Integer.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 添加一行记录，返回此行的内存地址，用于RowData类记录
	public long addRow(byte[] rowData) {
		buffer.put(rowData);
		return heapOffset.addAndGet(heapOffset.longValue()+rowData.length);
	};
	
	// 读取相应位置的数据并返回
	public byte[] getRow(long startPos, short dataLength) {
		byte[] b = new byte[dataLength];
		int pos = (int)startPos;
		int i=0;
		for(;pos<(int) (startPos+dataLength);pos++){
			b[i++] = buffer.get(pos);
		}
		return b;
	};
	
	//删除映射文件
	public void close(){
		try {
			close.clean(buffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}