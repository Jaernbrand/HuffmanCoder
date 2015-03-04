package huffman;

/**
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
public class Node implements Comparable<Node>{

	private Character character;
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
