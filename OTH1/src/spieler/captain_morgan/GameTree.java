package spieler.captain_morgan;

import java.util.ArrayList;

import spieler.Zug;

/**
 * Zuständig für Erstellen und Traversieren des GameTrees.
 * Nodes sollten Value, Alpha, Beta, Traversionstiefe, Boardstate und Arraylist an Kind-Nodes enthalten.
 * Vorsortieren der untersten Arraylist mittels entsprechender Collection sollte etwas Geschwindigkeit bringen.
 */

public class GameTree {
	
	Node root;
	Zug bestMove = null;
	
	// root node und seine children werden erzeugt
	public void addRoot(Feld feld, int suchTiefe) {
		
		root = new Node();
		root.tmpFeld = feld;
		root.suchTiefe = suchTiefe;
		
//		root.possibleMoves = root.tmpFeld.getAllPossibleMoves(Spieler.ownColor, Spieler.opponentColor);
		
		for (Zug z : root.possibleMoves) {
			root.addChild(z);
		}
	}
	
	class Node {
		
		Feld tmpFeld;
		int suchTiefe;
		boolean isMax = true;
		Zug zug = null;
		
		Node parent = null;
		
		int alpha, beta, value;			// minimax / alpha beta
		
		ArrayList<Zug> possibleMoves;
		ArrayList<Node> children;
		
		public Node () {				// im Konstruktor nur die ArrayLists erzeugen
			
			possibleMoves = new ArrayList<>();
			children = new ArrayList<>();
			
		}
		
		public void addChild (Zug z) {
			
			Node n = new Node();
			n.parent = this;
			n.isMax = !parent.isMax;
			n.suchTiefe = parent.suchTiefe - 1;
			n.tmpFeld = parent.tmpFeld;
			n.zug = z;
			
			if (n.isMax) {
				tmpFeld.setField(z.getZeile(), z.getSpalte(), Spieler.ownColor);
				Test.turnAround(tmpFeld, z.getZeile(), z.getSpalte(), Spieler.ownColor, Spieler.opponentColor);
			}
			else {
				tmpFeld.setField(z.getZeile(), z.getSpalte(), Spieler.opponentColor);
				Test.turnAround(tmpFeld, z.getZeile(), z.getSpalte(), Spieler.opponentColor, Spieler.ownColor);
			}
			
			this.children.add(n);
			
		}
	}
	public Zug getBestMove() {
		
		if(bestMove != null)
			return bestMove;
		
		else
			return new Zug (-1,-1);		
	}
}
