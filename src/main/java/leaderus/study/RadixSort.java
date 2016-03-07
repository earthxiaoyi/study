package leaderus.study;

public class RadixSort {
	
	public static void main(String[] args){
		int[] arr = new int[]{1,5,666,2323,4598,23,545,9877,5665,76,7,68};
		sort(arr, 4);
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
	
	/**
	 * 基数排序
	 * @param arr
	 * @param maxSite
	 */
	public static void sort(int[] arr,int maxSite){
		//1.记录当前处理的位数
		int nowSite = 1;
		int n=1;//取某位上面的值
		int[][] temp = new int[10][arr.length];//记录10个组的值
		int[] order = new int[10];//记录桶中数据的个数
		while(nowSite<=maxSite){
			//2.按照该位的数值，把数据分散到temp中去
			for(int i=0;i<arr.length;i++){
				int siteNum = (arr[i]/n)%10;
				temp[siteNum][order[siteNum]] = arr[i];
				order[siteNum] = order[siteNum]+1;
			}
			//3.按照0-9的顺序，把temp里面的值，设置回到原始的数组中
			int k=0;
			for(int i=0;i<10;i++){
				if(order[i]!=0){
					for(int j=0;j<order[i];j++){
						arr[k] = temp[i][j];
						k++;
					}
				}
				order[i] = 0;//桶内数据重置为0
			}
			//计算下一位/的值
			n *= 10;
			nowSite++;//位数加1
		}
	}
}
