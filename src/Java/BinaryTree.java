package leetcode;

public class BinaryTree {
	public int idx;
	public int value;
	public BinaryTree left;
	public BinaryTree right;
	static int g_cnt = 0;
	BinaryTree(int value){
		this.idx = g_cnt++;
		this.value = value;
		this.left = null;
		this.right = null;
	}
	void addNode(int value) {
		BinaryTree node = this;
		while(node != null) {
			if(node.value > value) {
				if(node.left != null) {
					node = node.left;
				}
				else {
					BinaryTree newNode = new BinaryTree(value);
					node.left = newNode;
					return;
				}
			}
			else {
				if(node.right != null) {
					node = node.right;
				}
				else {
					BinaryTree newNode = new BinaryTree(value);
					node.right = newNode;
					return;
				}
			}
		}
	}
	BinaryTree findNode(int key) {
		BinaryTree node = this;
		while(node != null) {
			if(node.value > key) {
				node = node.left;
			}
			else if(node.value < key){
				node = node.right;
			}
			else {
				return node;
			}
		}
		return null;
	}
	void traverseInOrder(BinaryTree node) {//left,root,right
		if(node == null) {
			return;
		}
		traverseInOrder(node.left);
		System.out.printf("(%d) value:%d \n",node.idx,node.value);
		traverseInOrder(node.right);
	}
	void traversePreOrder(BinaryTree node) {//root,left,right
		if(node == null) {
			return;
		}
		System.out.printf("(%d) value:%d \n",node.idx,node.value);
		traversePreOrder(node.left);
		traversePreOrder(node.right);
	}
	void traversePostOrder(BinaryTree node) {//left,right,root
		if(node == null) {
			return;
		}
		traversePostOrder(node.left);
		traversePostOrder(node.right);
		System.out.printf("(%d) value:%d \n",node.idx,node.value);

	}
	boolean sameTree(BinaryTree root1, BinaryTree root2){
		boolean left,right;
		if(root1 == null && root2 == null)
			return true;
		if(root1 != null ^ root2 != null)
			return false;
		if(root1.value == root2.value) {
			left = sameTree(root1.left,root2.left);
			right = sameTree(root1.right,root2.right);
			return (left && right);
		}
		else {
			return false;
		}
	}
}