package leaderus.study8;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

//堆外存储，一次性使用，即记录保存完成后，读取完成后， 就关闭删除文件，不会修改数据
public class RowDataOffheapStorage {
	
	private MappedByteBuffer buffer = null;
	
	public RowDataOffheapStorage(String fileName) {
		// 创建一个MappedByteBuffer 的映射文件存储，
		File dataFile = new File(fileName);
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(dataFile, "rw");
			FileChannel fileChannel = randomAccessFile.getChannel();
			buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, Integer.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 添加一行记录，返回此行的内存地址，用于RowData类记录
	public long addRow(byte[] rowData) {
		int position = buffer.position();
		buffer.put(rowData);
		return position;
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
}