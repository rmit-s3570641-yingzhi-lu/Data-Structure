import java.util.ArrayList;
import java.util.List;

public class MaxBinaryTree<T extends Comparable<T>> {

    private List<T> mHeap;    // ����(ʵ�����Ƕ�̬����ArrayList��ʵ��)

    public MaxBinaryTree() {
        this.mHeap = new ArrayList<T>();
    }
    // ���ѵ����µ����㷨
    protected void filterdown(int start, int end) {
        int delete = start;          // ��ɾ�����ݵ�λ��
        int left = 2*delete + 1;     // ��Ҫɾ���ڵ�����ӵ�λ��
        T lastData = mHeap.get(delete);    // ��������Ԫ�ش�С(���������һλ�Ĵ�С)
        int clr;
        while(left <= end) {//����while()ѭ��������֤�������Ժ�ö������Ȼ������
        	clr = mHeap.get(left).compareTo(mHeap.get(left+1)); //��ɾ�����ݵ����Ӻ��Һ��Ӵ�С�Ƚ�  ���l>l+1 ����1 ���С�� ����-1
            if(left < end && clr<0) //������Ӳ����������һλ &&cmp����-1����ɾ�����ݵ����ӱ��Һ���С
            	left++;        // ������������ѡ��ϴ���
            clr = lastData.compareTo(mHeap.get(left)); //�Ƚ���������Ԫ�ش�С�������Ӻ��Һ����нϴ��һ�� �����������Ԫ�ش����� ����1 ���򷵻�-1
            if(clr >= 0) //�����������Ԫ�صĴ��ڵ��������Һ����нϴ��һ��
                break;        //��������
            else {//�����������Ԫ�ش�С�������Һ����нϴ��С
                mHeap.set(delete, mHeap.get(left));//�ѱ�ɾ�����ݵ�λ�õĴ�С����Ϊ���Һ����нϴ��һ��
                delete = left;	// ��ɾ�����ݵ�λ�õ���Ϊ�������Һ����нϴ�ڵ��λ��
                left = 2*left + 1;   //l����Ϊ�������ӵ�λ��
            }       
        }   
        mHeap.set(delete, lastData); //������ɾ������λ�õĴ�СΪ��������Ԫ�ش�С(�������while(),�����������ǰ�ڵ�Ĵ�С����Ϊ��������Ԫ�ش�С)
    }
    //ɾ������
    public int remove(T data) {
        // �����Ϊ�գ��򷵻�-1
        if(mHeap.isEmpty())
            return -1;

        // ��ȡdata�������е�����
        int index = mHeap.indexOf(data);
        if (index==-1){	//�����Ҫɾ�������ݲ����� ����-1
            return -1;
        }
        if(index == (mHeap.size()-1)){ //���ɾ�����������������һλ ɾ����return 0
        	mHeap.remove(index);
        	return 0;
        }
        int size = mHeap.size();
        mHeap.set(index, mHeap.get(size-1));// �����һλԪ�����ɾ��Ԫ�ص�λ��
        mHeap.remove(size - 1);// ɾ������Ԫ��

        if (mHeap.size() > 1){
            filterdown(index, mHeap.size()-1);    // ��index��λ�ÿ�ʼ�������µ���Ϊ��С��
        }
        return 0;
    }

    //���ѵ����ϵ���
    protected void filterup() {
        int current = mHeap.size()-1;            // ��ǰ�ڵ��λ��(��������������һλ)
        int parent = (current-1)/2;        // ��(parent)����λ�� 
        T cData = mHeap.get(current);        // ��ǰ�ڵ�(current)�Ĵ�С
        int cPC;
        while(current > 0) {//����while()ѭ��������֤�������Ժ�ö������Ȼ������
        	cPC = mHeap.get(parent).compareTo(cData); //������ڵ���ڵ��ڵ�ǰ��� ����1 ������ڵ�С�ڵ�ǰ�ڵ� ����-1
            if(cPC >= 0)//������ڵ��ֵ���ڵ��ڵ�ǰ����ֵ
                break;
            else {//������ڵ��ֵС�ڵ�ǰ����ֵ
                mHeap.set(current, mHeap.get(parent)); //�ѵ�ǰ����ֵ���ɸ��ڵ��ֵ
                current = parent;//��ǰ����λ������Ϊ���ڵ��λ��
                parent = (parent-1)/2;  //���ڵ��λ������Ϊ��ǰ���ڵ�ĸ��ڵ��λ��
            }       
        }
        mHeap.set(current, cData);//�ѵ�ǰ����ֵ���ɵ�ǰ���Ĵ�С(�������wihie() ���ѵ�ǰ���Ĵ�С���ɲ��������)
    }
      
    //��������
    public void insert(T data) {
        mHeap.add(data);    //�������
        filterup();     // ���ϵ�����
    }
 
    public static void main(String[] args) {
        int a[] = {32,56,75,15,34,73,89,67,83,25};
        MaxBinaryTree<Integer> tree=new MaxBinaryTree<Integer>();

        for(int i=0; i<a.length; i++) {
            tree.insert(a[i]);
        }
        for(int i=0; i<a.length; i++) {
            System.out.print(tree.mHeap.get(i)+" ");
        }
        System.out.println("\n");
        tree.remove(75);
        for(int i=0; i<tree.mHeap.size(); i++) {
            System.out.print(tree.mHeap.get(i)+" ");
        }
    }
}