package leetcode;

public class BinaryTree {

}

//#include <stdio.h>
//2 int sameTree(Node root1, Node root2){
//3 //base case :both are empty
//4 if(root1==NULL && root2==NULL )
//5    return 1;
//6
//7 if(root1 == root2 ) {
//8     //subtrees
//9     Node *left=sameTree(root1.left,root2.left);
//10     Node *right=sameTree(root1.right,root2.right);
//11     return (left && right);
//12 }//end if
//13 else{
//14     return 0;