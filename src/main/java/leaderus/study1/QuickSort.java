package leaderus.study1;

public class QuickSort {
	
	public static void main(String[] args) {
		int[] as = new int[]{1,5,666,2323,4598,23,545,9877,5665,76,7,68};
		QuickSort quick = new QuickSort();
		quick.quickSort(as, 0, as.length-1);
		for(int a:as){
			System.out.println(a);
		}
	}
	
	/**
	 * 快速排序
	 * @param arr 
	 * @param left 
	 * @param right
	 */
	public void quickSort(int[] as,int left , int right){
		//退出条件
		int size = right-left+1;
		if(size<=3){
			this.manualSort(as,left,right);
			return ;
		}
		//1.获取分区枢纽值
		int mv = this.medianValue(as, left, right);
		//2.按照分区枢纽值对数组进行分区
		int partition = this.partition(as, left, right, mv);
		//3.对左边的数据进行quickSort
		this.quickSort(as, left, partition-1);
		//4.对右边的数据进行quickSort
		this.quickSort(as, partition+1, right);
	}
	
	/**
	 * 对数组进行分区，小的在前面，大的放后面
	 * @param as 排序的数组
	 * @param left
	 * @param right
	 * @param mv 中值
	 * @return
	 */
	private int partition(int[] as,int left,int right,int mv){
		//1.定义两个指示位置的索引
		//左边索引位置
		int leftIndex = left;
		//右边索引位置
		int rightIndex = right-1;
		//2.进行分区，小的在前面，大的在后面
		while(true){
			//从左向右找到一个比mv大的值
			while(as[++leftIndex]<mv){
				//循环停止，找到比mv大的值
			}
			//从右向左找到一个比mv小的值
			while(as[--rightIndex]>mv){
				//循环停止，找到比mv小的值
			}
			if(leftIndex>=rightIndex){
				break;
			}else{
				this.swap(as,leftIndex,rightIndex);
			}
		}
		//3.把中值放到分区的位置
		this.swap(as,leftIndex,right-1);
		return leftIndex;
	}
	
	/**
	 * 计算分期枢纽值
	 * @param as
	 * @param left
	 * @param right
	 * @return
	 */
	public int medianValue(int[] as,int left,int right){
		int center = (left+right)/2;
		if(as[left]>as[center]){
			this.swap(as, left, center);
		}
		if(as[left] > as[right]){
			this.swap(as, left, right);
		}
		if(as[center] > as[right]){
			this.swap(as, center, right);
		}
		this.swap(as, center, right-1);
		return as[right-1];
	}
	
	//交换
	public void swap(int[] as,int left,int right){
		int index = as[left];
		as[left] = as[right];
		as[right] = index;
	}
	//手工排序
	public void manualSort(int[] as, int left, int right){
		int size = right-left+1;
		if(size == 1){
		}else if(size == 2){
			if(as[left] > as[right]){
				this.swap(as, left, right);
			}
		}else if(size == 3){
			if(as[left] > as[right-1]){
				this.swap(as, left, right-1);
			}
			if(as[left] > as[right]){
				this.swap(as, left, right);
			}
			if(as[right-1] > as[right]){
				this.swap(as, right-1, right);
			}
		}
		
	}
}
