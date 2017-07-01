
public class BinarySearchTree {
	
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(50);
		tree.insert(64);
		tree.insert(14);
		tree.insert(44);
		tree.insert(24);
		tree.delete(14);
	}
	
	/*
	 * ���������
	 * ������������ڵ��ֵ��С���丸�ڵ�
	 * ������������ڵ��ֵ�������丸�ڵ�
	 * û��ֵ��ȵĽڵ�
	 **/
	
	/*
	 * Node:�������Ľڵ�,��������,�Һ���,���ڵ��ֵ
	 * 
	 * */
	class Node {
		private Node left;
		private Node right;
		private Node parent;
		private int key;
		
		public Node(int key,Node left,Node right,Node parent) {
			this.key = key;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		
		public int getKey() {
			return key;
		}
		
		public void setKey(int key) {
			this.key = key;
		}
	}
	
	private Node node;
	
	public BinarySearchTree() {
		node = null;
	}
	
	public void insert(int key) {
		Node newNode = new Node(key, null, null, null);
		insert(this,newNode);
	}
	
	public void insert(BinarySearchTree tree, Node node) {
		Node currentNode = tree.node;
		
		while(currentNode != null) {
			if(currentNode.getKey() > node.getKey()) {
				if(currentNode.left == null) {
					currentNode.left = node;
					break;
				}else {
					currentNode = currentNode.left;
				}
			}
			if(currentNode.getKey() < node.getKey()) {
				if(currentNode.right == null) {
					currentNode.right = node;
					break;
				}else {
					currentNode = currentNode.right;
				}
			}

		}
	}
	//������nodeΪ���Ķ���������С�ڵ�
	public Node minNode(Node node) {
		if(node == null) {
			return null;
		}
		while(node.left != null) {
			node = node.left;
		}
		
		return node;
	}
	//������nodeΪ���Ķ����������ڵ�
	public Node maxNode(Node node) {
		
		if(node == null) {
			return null;
		}
		while(node.right != null) {
			node = node.right;
		}
		
		return node;
	}
	
	public Node predecessor(Node node) {
		/* ����node�ڵ��ǰ���ڵ�,��С�ڸýڵ�����ڵ�
		 * ���node��������,��node��ǰ�����Ϊ�������������ڵ�
		 * ���nodeû��������
		 * 	  ���nodeΪһ���Һ���,��node��ǰ�����Ϊ�丸�ڵ�
		 * 	 ���nodeΪһ������,��node��ǰ�����Ϊ��ӵ���Һ��ӵ���͵ĸ��ڵ�
		 * */
		if(node.left != null) {
			return maxNode(node.left);
		}
		Node parent = node.parent;
		if(node.getKey() > parent.getKey()) {
			return parent;
		}else {
			while(parent.right == null) {
				parent = parent.parent;
			}
			return parent;
		}
	}

	public Node successor(Node node) {
		/* ����node�ڵ�ĺ�̽ڵ�,�����ڸýڵ����С�ڵ�
		 * ���node��������,��node�ĺ�̽��Ϊ������������С�ڵ�
		 * ���nodeû��������
		 * 	  ���nodeΪһ������,��node�ĺ�̽��Ϊ�丸�ڵ�
		 * 	  ���nodeΪһ���Һ���,��node�ĺ�̽��Ϊ��ӵ�����ӵ���͵ĸ��ڵ�
		 * */
		if(node.right != null) {
			return minNode(node.right);
		}
		Node parent = node.parent;
		if(node.getKey() < parent.getKey()) {
			return parent;
		}else {
			while(parent.left == null) {
				parent = parent.parent;
			}
			return parent;
		}
	}
	
	public Node search(int key) {
		
		return search(this.node, key);
	}
	
	public Node search(Node node, int key) {
		if(node == null) {
			return node;
		}
		if(node.getKey() == key) {
			return node;
		}else if(node.getKey() > key) {
			return search(node.left, key);
		}else {
			return search(node.right, key);
		}
	}
	
	public void delete(int key) {
		Node node = search(this.node, key);
		if(node != null) {
			delete(this, node);
		}
	}
	//node:��Ҫ��ɾ���Ľڵ�
	public void delete(BinarySearchTree tree, Node node) {
		
		Node temp;
		// ��ɾ���Ľڵ�û���ӽڵ�
		if(node.left == null && node.right == null) {
			node = null;
		}else {
			temp = successor(node);
		}
		//��ɾ���Ľڵ�ֻ��һ���ӽڵ�
		if(node.left == null || node.right == null) {
			if(node.left == null) {
				node = node.right;
			}else {
				node = node.left;
			}
		}
		//��ɾ���Ľڵ��������ӽڵ�
		temp = successor(node);
		node.setKey(temp.getKey());
		if(temp.right == null) {
			temp = null;
		}else {
			temp = temp.right;
			
		}
		
		
	}
}

