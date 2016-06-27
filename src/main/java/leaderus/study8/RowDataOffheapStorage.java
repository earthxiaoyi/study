package leaderus.study8;

//堆外存储，一次性使用，即记录保存完成后，读取完成后， 就关闭删除文件，不会修改数据
public class RowDataOffheapStorage {

	public RowDataOffheapStorage(String fileName) {
		// 创建一个MappedByteBuffer 的映射文件存储，
	}

	// 添加一行记录，返回此行的内存地址，用于RowData类记录
	public long addRow(byte[] rowData) {
		return 0;
	};

	// 读取相应位置的数据并返回
	public byte[] getRow(long startPos, short dataLength) {
		return null;
	};
}