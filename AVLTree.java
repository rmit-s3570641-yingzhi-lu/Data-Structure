
public class AVLTree {
	/*
	 * AVL��Ϊ�߶�ƽ��Ķ�����,���κνڵ�����������ĸ߶�����Ϊ1
	 * ���������������
	 * ʧ��:��LL:�����ɾ��һ���ڵ��,���ڵ�������������������зǿ��ӽڵ�->�����������߶ȱ��������߶ȸ�2
	 * 	   ��LR:�����ɾ��һ���ڵ��,���ڵ�������������������зǿ��ӽڵ�->�����������߶ȱ��������߶ȸ�2
	 * 	   ��RR:�����ɾ��һ���ڵ��,���ڵ�������������������зǿ��ӽڵ�->�����������߶ȱ��������߶ȸ�2
	 * 	   ��RL:�����ɾ��һ���ڵ��,���ڵ�������������������зǿ��ӽڵ�->�����������߶ȱ��������߶ȸ�2
	 * ��ת:��LL:��ʧ��Ľڵ�T1��������T2��ɸ��ڵ�,T1���T2��������,T2�����������T1��������
	 * 	   ��LR:�ȶ�ʧ��ĸ��ڵ�T1��������T2����һ��RR��ת,�ٶ�T1����һ��LL��ת
	 * 	   ��RR:��ʧ��Ľڵ�T1��������T3��ɸ��ڵ�,T1���T3��������,T3�����������T1��������
	 *     ��RL:�ȶ�ʧ��ĸ��ڵ�T1��������T3����һ��LL��ת,�ٶ�T1����һ��RR��ת
	 * */
	
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		tree.insert(4);
		tree.insert(3);
		tree.insert(2);
		tree.insert(1);
		
	//	tree.delete(1);

		System.out.println(tree.root.left.height);
		System.out.println(tree.root.right.height);
		System.out.println(tree.root.height);
	

	
	}
	
	class Node {
		//����,�Һ���,�߶�,ֵ
		private Node left;
		private Node right;
		private int height;
		private int key;
		
		public Node(int key, Node left, Node right) {
			this.key = key;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
		
		public int getKey() {
			return key;
		}
		
		public int getHeight() {
			return height;
		}
	}
	
	private Node root;
	
	public Node getRoot() {
		return root;
	}
	
	public AVLTree() {
		root = null;
	}
	
	public void insert(int key) {
		root = insert(root, key);
	}
	
	public Node insert(Node root, int key) {
		if(root == null) {
			root = new Node(key, null, null);
		//	root.height = root
		}else {
			//���keyֵС�ڵ�ǰ�ڵ��keyֵ,�������Ľڵ�Ӧ��λ�ڵ�ǰ�ڵ��������
			if(key < root.key) {
				root.left = insert(root.left, key);
				//��������ڵ��ǰ�������������������ĸ߶Ȳ�Ϊ2,���е���
			//	System.out.println(getHeight(root.left) - getHeight(root.right));
				if(getHeight(root.left) - getHeight(root.right) == 2) {
					/* 
					 * rootΪ����ڵ�ĸ��ڵ�ĸ��ڵ�
					 * ���keyֵС�ڵ�ǰ�������ӵ�keyֵ,˵������Ľڵ�Ϊ���� --> LL��� --> LL��ת
					 * ���keyֵ���ڵ�ǰ�������ӵ�keyֵ,˵������Ľڵ�Ϊ�Һ��� --> LR��� --> LR��ת
					 * */
					if(key < root.left.key) {
						root = LLRotation(root);
					}else {
						root = LRRotation(root);
					}		
				}
			}else if(key > root.key) {
				root.right = insert(root.right, key);
				if(getHeight(root.right) - getHeight(root.left) == 2) {
					if(key > root.right.key) {
						root = RRRotation(root);
					}else {
						root = RLRotation(root);
					}
				}
			}else {
				System.out.println("error");
			}
		}
		//��ȡ�ڵ�ĸ߶�
		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
		
		return root;
		
	}
	
	public void delete(int key) {
		//������Ҫ��ɾ���Ľڵ�
		Node deleteNode = search(this.root, key);
		delete(this.root, deleteNode);
	}
	
	public Node delete(Node node, Node deleteNode) {
		if(node == null || deleteNode == null) {
			return null;
		}
		//���ݱ�ɾ���Ľڵ�keyֵ�ж����������ϻ�����������,Ȼ��һ�����ݹ���в��Ҳ�ɾ��,ɾ������е���
		if(deleteNode.key <= node.left.key) {
			node.left = delete(node.left, deleteNode);
			if(getHeight(node.right) - getHeight(node.left) == 2) {
				Node newRoot = node.right;
				if(getHeight(newRoot.left) > getHeight(newRoot.right)) {
					node = RLRotation(node);
				}else {
					node = RRRotation(node);
				}
			}
		}else if(deleteNode.key >= node.left.key) {
			node.right = delete(node.right, deleteNode);
			if(getHeight(node.right) - getHeight(node.right) == 2) {
				Node newRoot = node.left;
				if(getHeight(newRoot.right) > getHeight(newRoot.left)) {
					node = LRRotation(node);
				}else {
					node = LLRotation(node);
				}
			}
		}else {
			if((node.left != null) && (node.right != null)) {
				/*
				 * �ڱ�ɾ���ڵ��в�Ϊ�յ����Һ��ӵ������
				 * ������ӵĸ߶ȴ����Һ��ӵĸ߶�,���ҳ�������keyֵ���ĵ�,��keyֵ����ɾ���ڵ�,Ȼ��ɾ�����ڵ�
				 * ������ӵĸ߶�С���Һ��ӵĸ߶�,���ҳ��Һ�����keyֵ���ĵ�,��keyֵ����ɾ���ڵ�,Ȼ��ɾ�����ڵ�
				 * */
				if(getHeight(node.left) > getHeight(node.right)) {
					Node max = getMax(node.left);
					node.key = max.key;
					node.left = delete(node.left, max);
				}else {
					Node min = getMin(node.right);
					node.key = min.key;
					node.right = delete(node.right, min);
				}
			}else {
				/*
				 * ���ɾ���ڵ���Һ���Ϊ��,��ֱ��ɾ���ڵ�,����ɾ���ڵ�����ӷŵ���ɾ���ڵ��λ��
				 * ���ɾ���ڵ������Ϊ��,��ֱ��ɾ���ڵ�,����ɾ���ڵ���Һ��ӷŵ���ɾ���ڵ��λ��
				 * */
				if(node.left != null) {
					node = node.left;
				}else {
					node = node.right;
				}
			}
		}
		
		return node;
	}
	//LL��ת
	public Node LLRotation(Node node) {
		Node root = node.left;
		node.left = root.right;
		root.right = node;
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
		
		return root;
	}
	//RR��ת
	public Node RRRotation(Node node) {
		Node root = node.right;
		node.right = root.left;
		root.left = node;
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
		
		return root;
	}
	//LR��ת
	public Node LRRotation(Node node) {
		node.left = RRRotation(node.left);
		
		return LLRotation(node);
	}
	//RL��ת
	public Node RLRotation(Node node) {
		node.right = LLRotation(node.right);
		
		return RRRotation(node);
	}
	
	public int getHeight(Node node) {
		if(node != null) {
			return node.height;
		}else {
			return 0;
		}	
	}
	//����keyֵ���ҽڵ�
	public void search(int key) {
		search(this.root, key);
	}
	
	public Node search(Node node, int key) {
		if(node == null) {
			return node;
		}
		/* 
		 * ���keyֵС�ڸ��ڵ��keyֵ,˵�����ҵĵ��ڸ��ڵ����������,���еݹ�
		 * ���keyֵ���ڸ��ڵ��keyֵ,˵�����ҵĵ��ڸ��ڵ����������,���еݹ�
		 * ���keyֵ���ڸ��ڵ��keyֵ,ֱ�ӷ���
		 * */
		if(key < node.key) {
			return search(node.left, key);
		}else if(key > node.key) {
			return search(node.right, key);
		}else {
			return node;
		}
	}
	//��ȡ�ýڵ���ӵ����Сkeyֵ�Ľڵ�
	public Node getMin(Node node) {
		if(node == null) {
			return node;
		}
		while(root.left != null) {
			node = node.left;
		}
		
		return node;
	}
	//��ȡ�ýڵ���ӵ�����keyֵ�Ľڵ�
	public Node getMax(Node node) {
		if(node == null) {
			return node;
		}
		while(node.right != null) {
			node = root.right;
		}
		
		return node;
	}
	//ǰ�����
	public void preOrder(Node node) {
		if(node != null) {
			System.out.print(node.key);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	//�������
	public void inOrder(Node node) {
		if(node != null) {
			preOrder(node.left);
			System.out.print(node.key);
			preOrder(node.right);
		}
	}
	//�������
	public void postOrder(Node node) {
		if(node != null) {
			preOrder(node.left);
			preOrder(node.right);
			System.out.print(node.key);
		}
	}
}
