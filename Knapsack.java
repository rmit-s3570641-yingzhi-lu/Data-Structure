import java.util.ArrayList;

public class Knapsack {
	/*0-1��������(ÿ����Ʒֻ��ѡ��0��1��)
	 * f[i,j]:i����Ʒ��ѡ�����ɼ���������ΪJ�İ������ܻ�õ�����ֵ
	 * W(i):��i����Ʒ������
	 * V(i):��i����Ʒ�ļ�ֵ
	 * ״̬ת������:f[i,j] = max{f[i-1,j-W(i)]+V(i),f[i-1,j]}
	 * i����Ʒ��ѡ�����ɷ�������Ϊj�İ������ܻ�õ�����ֵ = 
	 * ���ֵ{ǰi-1����Ʒ��ѡ�����ɷ�������Ϊj-W(i)�İ������ܻ�õ�����ֵ+��i����Ʒ�ļ�ֵ,ǰi-1����Ʒ��ѡ�����ɷ�������Ϊj�İ������ܻ�õ�����ֵ}
	 * */
	public static void main(String[] args) {
		
		ArrayList<Fruit> fruit = new ArrayList<Fruit>();
		
		fruit.add(new Fruit(10,2,"ƻ��"));
		fruit.add(new Fruit(19,3,"�㽶"));
		fruit.add(new Fruit(27,5,"��"));
		fruit.add(new Fruit(21,4,"����"));
		fruit.add(new Fruit(37,6,"����"));
		//j
		int bagWeight = 10;
		//�����Ž� �ǵݹ�
		deal(fruit,bagWeight);
		
	}
	
	public static void deal(ArrayList<Fruit> fruit, int bagWeight) {
		/*
		 * �����Ž���Ϊһ����ά����
		 * ��ʼ�����ż�
		 * */
		int[][] f = new int[fruit.size()+1][bagWeight+1];
		
		for(int i=0;i<fruit.size()+1;i++) {
			f[i][0] = 0;
		}
		for(int i=0;i<bagWeight+1;i++) {
			f[0][i] = 0;
		}
		
		for(int i=1;i<fruit.size()+1;i++) {
			for(int j=1;j<bagWeight+1;j++) {
				/* 
				 * f[1,1] ��һ��ˮ����������Ϊ1�İ��� ��Ϊˮ������������1 ����f[1,1]=f[0,1]=0
				 * f[1,2] ��һ��ˮ����������Ϊ2�İ��� ��Ϊˮ������������2��f[0,1]<f[0,0]+10 ����f[1,2]=f[0,0]+10=10
				 * 
				 * �����i-1����Ʒ������С�ڵ����������
				 *   ���i-1��Ʒ����j������ֵС��i-1��Ʒ����j-(��i-1��Ʒ����)������ֵ+��i-1��Ʒ�ļ�ֵ
				 *     i��Ʒ����j������ֵ=i-1��Ʒ����j-(��i-1��Ʒ����)������ֵ+��i-1��Ʒ�ļ�ֵ
				 *     ����i��Ʒ����j������ֵ=i-1��Ʒ����j������ֵ
				 * ����i��Ʒ����j������ֵ=i-1��Ʒ����j������ֵ
				 * */
				if(fruit.get(i-1).getWeight() <= j) {
					if(f[i-1][j] < f[i-1][j-fruit.get(i-1).getWeight()]+fruit.get(i-1).getValue()) {
						f[i][j] = f[i-1][j-fruit.get(i-1).getWeight()] + fruit.get(i-1).getValue();
					} else {
						f[i][j] = f[i-1][j];
					}
				} else {
					f[i][j] = f[i-1][j];
				}
			}
		}

		System.out.println(f[fruit.size()][bagWeight]);
	}
	
}

class Fruit {
	private int value;
	private int weight;
	private String name;
	
	public Fruit(int value,int weight,String name) {
		this.value = value;
		this.weight = weight;
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String getName() {
		return name;
	}
}