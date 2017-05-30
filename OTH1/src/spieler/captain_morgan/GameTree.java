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
	//TODO: Getter und Setter für Root?
	
	Zug bestMove = null;
	 
	public GameTree(Feld gameField, int HalbZuege) {
		System.out.println("test: st = " + HalbZuege);
		root = new Node();
		root.tmpFeld = gameField;
		root.HalbZuege = HalbZuege;
	}
	
	public Zug getBestMove() {
		
		if(bestMove != null)
			return bestMove;
		
		else
			return new Zug (-1,-1);		
	}
	
	class Node {
		
		Feld tmpFeld;
		Zug moveToTest;
		ArrayList<Zug> allPossible;
		ArrayList<Node> children;
		
		int activePlayerColor, opponentColor;
		
		// Soll angeben, auf welcher Ebene wir uns befinden und ob wir minimieren oder maximieren;
		// wird getoggled bei Erstellung von Kind-Nodes
		boolean isMax;
		
		int HalbZuege;		// counter (Abbruch in Rekursion)
		int value;
		
		int alpha;
		int beta;
		
		Node parent;
		
		// int traversionstiefe;
		
		// Allgemeiner Konstruktor, der nur zum Erstellen des Root genutzt wird
		public Node(){
			
			isMax = true;
			parent = null;
			
			tmpFeld = new Feld();
			
			allPossible = new ArrayList<>();
			children = new ArrayList<>();
			
			allPossible = tmpFeld.getAllPossibleMoves(Spieler.ownColor, Spieler.opponentColor);	// fuer eigene Farbe (root = MAX Knoten)
			
			for (Zug z : allPossible)
				addChild(this.tmpFeld, z, this.HalbZuege);
			
			// erhält Kopie des Originalbrettes
			
		}
		
		//Konstruktor für die anderen Knoten, die in der Frag Min/Max alternieren.
		public Node(Node parentNode, Zug moveToTest, int HalbZuege){
			
			this.parent = parentNode;
			this.isMax = !parent.isMax;
			
			tmpFeld = parent.tmpFeld;
			
			allPossible = new ArrayList<>();
			children = new ArrayList<>();
			
			if (this.isMax) {
				tmpFeld.setField(moveToTest.getZeile(), moveToTest.getSpalte(), Spieler.ownColor);
				Test.turnAround(moveToTest.getZeile(), moveToTest.getSpalte(), Spieler.ownColor, Spieler.opponentColor);
			}
			
			else {
				tmpFeld.setField(moveToTest.getZeile(), moveToTest.getSpalte(), Spieler.opponentColor);
				Test.turnAround(moveToTest.getZeile(), moveToTest.getSpalte(), Spieler.opponentColor, Spieler.ownColor);
			}
			
			if (this.isMax)
				allPossible = tmpFeld.getAllPossibleMoves(Spieler.ownColor, Spieler.opponentColor);	// fuer eigene Farbe (root = MAX Knoten)
			
			else
				allPossible = tmpFeld.getAllPossibleMoves(Spieler.opponentColor, Spieler.ownColor);	// MIN == Gegner
			
			for (Zug z : allPossible)
				addChild(this.tmpFeld, z, parent.HalbZuege -1);
			
			//erhält Brett von parent (KEINE Kopie)
			
		}
		
		int count = 0;
		
		public void addChild(Feld f, Zug z, int HalbZuege) {

// TESTAUSGABE
System.out.println("count: " + count++ + " HZ: " + HalbZuege);
			
			if (HalbZuege <= 0)
				return;
			
			this.children.add(new Node(this, z, HalbZuege));
			
		}
	}
}
