package spieler.captain_morgan;

import java.util.ArrayList;

import spieler.Zug;

public class Node {

	Node parent;
	ArrayList<Node> children;
		
	boolean isFoe;
	
	int alpha = Integer.MIN_VALUE;
	int beta = Integer.MAX_VALUE;
	int value;
	
	Zug move;
	
	public Node() {
		children = new ArrayList<>();
		value = Integer.MIN_VALUE;
	}
}
