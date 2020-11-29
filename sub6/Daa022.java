import java.util.Scanner;

class Node {
  boolean isBlack;  // No preto?
  boolean isNull;   // No nulo?
  int value;        // Valor
  Node left, right; // Filhos

  Node(int v) {
    isNull  = (v==0);
    isBlack = (v>=0);
    value   = Math.abs(v);
  }
}

public class Daa022{
  // Ler input em preorder
  static Node readPreOrder(Scanner in) {
    int v = in.nextInt();
    Node aux = new Node(v);
    if (v!=0) {
      aux.left  = readPreOrder(in);
      aux.right = readPreOrder(in);
    }
    return aux;
  }
  // Menor valor da arvore
  static int minimum(Node t) {
    if (t.isNull) return Integer.MAX_VALUE;
    int minLeft  = minimum(t.left);
    int minRight = minimum(t.right);
    return Math.min(t.value, Math.min(minLeft, minRight));
  }
  // Maior valor da arvore
  static int maximum(Node t) {
    if (t.isNull) return Integer.MIN_VALUE;
    int minLeft  = maximum(t.left);
    int minRight = maximum(t.right);
    return Math.max(t.value, Math.max(minLeft, minRight));
  }
  // Quantidade de nos (internos)
  static int size(Node t) {
    if (t.isNull) return 0;
    return 1 + size(t.left) + size(t.right);
  }

  static boolean isBinarySearchTree(Node n){
    if(n.isNull) return true;
    if(!(n.left.isNull) && (maximum(n.left)) >= n.value) {
      return false;
    }
    if(!(n.right.isNull) && (minimum(n.right)) <= n.value) {
      return false;
    }
    if(!isBinarySearchTree(n.left) || !isBinarySearchTree(n.right)){
      return false;
    }
    return true;
  }

  static boolean rootProperty(Node n){
    return n.isBlack;
  }

  static boolean redProperty(Node n){
    if(n.isNull) {
      return true;
    }
    if(!n.isBlack && (!n.left.isBlack || !n.right.isBlack)){
      return false;
    }
    return (redProperty(n.left) && redProperty(n.right));
  }

  static boolean blackProperty(Node n){
    return blackHeight(n) != -1;
  }

  static int blackHeight(Node n){
    if(n.isNull) {
      return 0;
    }

    int leftHeight = blackHeight(n.left);
    int rightHeight = blackHeight(n.right);
    int count = 0;
    if(n.isBlack){
      count++;
    }

    if((leftHeight == -1 || rightHeight == -1) || leftHeight != rightHeight){
      return -1;
    }
    else {
      return leftHeight + count;
    }
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n =  in.nextInt();
    for (int i=0; i<n; i++) {
      Node root = readPreOrder(in);
      //System.out.printf("Tree with %d nodes (min=%d, max=%d)\n", size(root), minimum(root), maximum(root));
      if(isBinarySearchTree(root) && redProperty(root) && blackProperty(root) && rootProperty(root)){
        System.out.println("SIM");
      }
      else {
        System.out.println("NAO");
      }
    }
  }
}
