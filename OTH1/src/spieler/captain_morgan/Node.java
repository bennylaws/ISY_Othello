package spieler.captain_morgan;

import java.util.ArrayList;

import spieler.Zug;

public class Node {

	Node parent;
	ArrayList<Node> children;
	ArrayList<Zug> possibleMoves;
		
	boolean isFoe = false;
	
	int alpha = Integer.MIN_VALUE;
	int beta = Integer.MAX_VALUE;
	int value = 0;
	
	Zug move;
	
	
	public Node() {
		children = new ArrayList<>();
		possibleMoves = new ArrayList<>();
	}
}
