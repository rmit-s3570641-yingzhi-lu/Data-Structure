
public class Sort {
	
	public static void main(String[] args) {
		selectionSort();
		System.out.print("\n");
		bubbleSort();
		System.out.print("\n");
		perpare();
		System.out.print("\n");
		mergeSort();
	}
	//ѡ������  O(n^2)
	public static void selectionSort() {
		int[] array = {99,11,14,23,56,54,34,25};
		int length = array.length;
		int inter;
		for(int k=0;k<length-1;k++) { // n-1
			for(int i=k;i<length;i++) { // n(n-1)
				if(array[i] < array[k]) {
					inter = array[k];
					array[k] = array[i];
					array[i] = inter;
				}
			}
		}
		
		for(int i=0;i<array.length ;i++) {
			System.out.print(array[i]+" ");
		}
		
	}
	
	//ð������ O(n^2)
	public static void bubbleSort() {
		int[] array = {99,11,14,23,56,54,34,25};
		int length = array.length;
		int inter;
		for(int i=0;i<length;i++) { // n
			for(int k=i+1;k<length;k++) { // n(n-1)
				if(array[k] < array[i]) {
					inter = array[i];
					array[i] = array[k];
					array[k] = inter;
				}
			}
		}
		
		for(int i=0;i<array.length ;i++) {
			System.out.print(array[i]+" ");
		}
	}
	//��������
	public static void perpare() {
		int[] array = {99,11,14,23,56,54,34,25};
		int left =0; 
		int right = array.length - 1;
		
		quickSort(array,left,right);
		
		for(int i=0;i<array.length ;i++) {
			System.out.print(array[i]+" ");
		}
	}
	public static void quickSort(int[] array,int left,int right) {
		/*
		 * ȡһ������Ĺؼ���key(һ��Ϊ��һλ),���ҿ�ʼ����keyС������key����,����ʼ����key�������key����
		 * һ��������󽫱�keyС�������ŵ���������,��key��������ŵ�������ұ�,Ȼ���������߿��������������½�����һ������
		 * */
		if(left < right) {
			int i = left;
			int j = right;
			int key = array[i];
			while(i<j) {
				while(i < j && array[j] >= key) {
					j--;
				}
				if(i < j) {
					array[i] = array[j];
				}
				while(i < j && array[i] < key) {
					i++;
				}
				if(i < j) {
					array[j] = array[i];
				}
			}
			array[i] = key;
			quickSort(array,left,i);
			quickSort(array,i+1,right);
		}
		
	}
	//�鲢����
	public static void mergeSort() {
		/*
		 * ��һ������ָ�ɶ��С�����������(�ʼ���ֳ�����һ���С�����������),Ȼ���������ϲ�����,���ϲ������������
		 * ��һ��forѭ�����ڶ���������ô�ֽ���ϲ�(���ֽ��С����ĳ���)
		 * �ڶ���forѭ�����ڽ��ֽ��С������кϲ�
		 * i:С�������һλ���±�
		 * j:С�����һλ���±�
		 * merge()�е��ĸ�����Ϊ:����,��һ��С����ĵ�һ��Ԫ�ص��±�,��һ��С��������һ��Ԫ�ص��±�,�ڶ���С�������һλ���±�
		 * */
		int[] array = {99,11,14,23,56,54,34,25};
		for(int i=1;i<array.length;i=2*i) {
			int j;
			for(j=0;j+2*i-1<array.length;j=j+i*2) {
				merge(array,j,j+i-1,j+2*i-1);
			}
			//������һ��ϲ�������С���鳤�Ȳ�һ��,����������鳤��Ϊ����
			if(j+i-1<array.length) {
				merge(array,j,j+i-1,array.length-1);
			}
		}
		for(int i=0;i<array.length ;i++) {
			System.out.print(array[i]+" ");
		}
	}
	//�ϲ�����С����
	public static void merge(int[] array, int first, int second, int k) {
		/*
		 * i:��һ��С����ĵ�һ��Ԫ�ص��±�
		 * j:�ڶ���С����ĵ�һ��Ԫ�ص��±�
		 * k:�ڶ���С��������һ��Ԫ�ص��±�
		 * temp:������ʱ��źϲ��������С����
		 * */
		int i = first; 
		int j = second+1; 
		int[] temp = new int[k-i+1]; 
		System.out.println(i+" "+j+" "+k);
		int l = 0;
		//������С�����е�Ԫ�ذ���С���򲢺ϲ�
		while(i <= second && j <= k) { 
			if(array[i] <= array[j]) {
				temp[l] = array[i];
				i++;
				l++;
			} else {
				temp[l] = array[j];
				j++;
				l++;
			}
		}
		while(i <= second) {
			temp[l] = array[i];
			i++;
			l++;
		}
		while(j <= k) {
			temp[l] = array[j];
			j++;
			l++;
		}
		for(l=0,i=first;i<=k;l++,i++) {
			array[i] = temp[l];
		}
	}
	
	//�����������
	//�����������
	//Ԥ����
	//AVL��
	//������
	//ƽ����: 2-3 tree
}