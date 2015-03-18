package huffman;

import java.io.Serializable;

/**
 * Node used to construct Huffman-trees.
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
class Node implements Serializable, Comparable<Node>{

	// Default value for serialVersionUID
	private static final long serialVersionUID = 1L;
	
	// Is null if the node isn't a leaf.
	private Character character;
	
	// The weight is the same as the sum of all occurrences of all characters
	// in the subtree, this node included. 
	private int weight;
	
	private Node leftChild;
	private Node rightChild;

	Node(Character car, int weight){
		character = car;
		this.weight = weight;
		leftChild = null;
		rightChild = null;
	}

	Node(Character car, int weight, Node leftChild, Node rightChild){
		this.weight = weight;
		this.character = car;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	int getWeight(){
		return weight;
	}

	void setWeight(int i){
		weight = i;
	}

	Character getChar(){
		return character;
	}

	void setChar(char c){
		character = c;
	}

	Node getRightChild(){
		return rightChild;
	}

	void setRightChild(Node right){
		rightChild = right;
	}

	Node getLeftChild(){
		return leftChild;
	}

	void setLeftChild(Node left){
		leftChild = left;
	}

	@Override
	public int compareTo(Node other){
		return weight - other.getWeight();
	}
}//Node
