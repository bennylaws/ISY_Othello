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
		root.isMax = true;
		root.parent = null;
		root.possibleMoves = root.tmpFeld.getAllPossibleMoves(Spieler.ownColor, Spieler.opponentColor);
		
		for (Zug z : root.possibleMoves) {
			root.addChild(this.root, z);
		}
		System.out.println(root.value);
		
//System.out.println("root: st: " + root.suchTiefe + " anz. ki.: " + root.children.size());

	}
	
	class Node {
		
		Feld tmpFeld;
		int suchTiefe;
		boolean isMax = true;
		Zug zug = null;
		Node parent;
		
		int alpha, beta, value = 0;			// minimax / alpha beta
		
		ArrayList<Zug> possibleMoves;
		ArrayList<Node> children;
		
		public Node () {				// im Konstruktor nur die ArrayLists erzeugen
			
			possibleMoves = new ArrayList<>();
			children = new ArrayList<>();
			
		}
		
		public void addChild (Node papa, Zug z) {
			
			Node n = new Node();
			n.parent = papa;
			n.isMax = !papa.isMax;
			n.suchTiefe = papa.suchTiefe - 1;
			n.tmpFeld = papa.tmpFeld;
			n.zug = z;

			// setze temporaeren Zug, drehe das Noetige um und hole neue moegliche Zuege  (-> ANDERE Farbe!)...
			if (n.isMax) {
				tmpFeld.setField(z.getZeile(), z.getSpalte(), Spieler.ownColor);
				Test.turnAround(tmpFeld, z.getZeile(), z.getSpalte(), Spieler.ownColor, Spieler.opponentColor);
				if(n.suchTiefe > 0)
					n.possibleMoves = n.tmpFeld.getAllPossibleMoves(Spieler.opponentColor, Spieler.ownColor);
			}
			
			else {
				tmpFeld.setField(z.getZeile(), z.getSpalte(), Spieler.opponentColor);
				Test.turnAround(tmpFeld, z.getZeile(), z.getSpalte(), Spieler.opponentColor, Spieler.ownColor);
				if(n.suchTiefe > 0)
					n.possibleMoves = n.tmpFeld.getAllPossibleMoves(Spieler.ownColor, Spieler.opponentColor);
			}
						
			if (suchTiefe <= 0) {
				parent.value = BoardEvaluator.getBoardValue(n.tmpFeld, Spieler.ownColor, Spieler.opponentColor);
				return;
			}
			
			this.children.add(n);

System.out.println("n: st: " + n.suchTiefe + " anz. ki.: " + n.children.size());

			// REKURSION
			for (Node childNode : n.children)
				for (Zug zug : childNode.possibleMoves)
					childNode.addChild(childNode, zug);

			if (parent != null) {
				if (this.isMax && parent.value < this.value)
					parent.value = this.value;
				
				else if (!this.isMax && parent.value > this.value)
					parent.value = this.value;
				
			}
		}
	}
	
	// hier noch die Bewertungsfunktion versklaven!
	public Zug getBestMove() {
		
		// Baum traversieren und Ergebnisse bewerten lassen, dann im Baum hochreichen --> wie?
		
		if(bestMove != null)
			return bestMove;
		
		else
			return new Zug (-1,-1);		
	}
}
