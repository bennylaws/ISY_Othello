package spieler.captain_morgan;

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
	 
	public GameTree(Feld gameField){
		root = new Node();
		root.tmpFeld = gameField;
	};
	
	public Zug getBestMove(){
		if(bestMove != null)
			return bestMove;
		else return new Zug (-1,-1);
	}
	
	class Node{
		
		Feld tmpFeld;
		Zug moveToTest;
		
		int alpha;
		int beta;
		int value;
		//Soll angeben, auf welcher Ebene wir uns befinden und ob wir minimieren oder maximieren;
		//wird getoggled bei Erstellung von Kind-Nodes
		boolean isMax;
		
		Node parent;
		
		//int traversionstiefe;
		
		//Allgemeiner Konstruktor, der nur zum Erstellen des Root genutzt wird
		public Node(){
			isMax = true;
			parent = null;
			//erhält aktuelles Brett
		}
		
		//Konstruktor für die anderen Knoten, die in der Frag Min/Max alternieren.
		public Node(Node parentNode){
			this.parent = parentNode;
			this.isMax = !parent.isMax; 
			//erhält eigenes Feld
		}	
	}
	
	
	

}
