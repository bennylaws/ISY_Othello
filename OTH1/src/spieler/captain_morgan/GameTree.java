package spieler.captain_morgan;

/**
 * Zuständig für Erstellen und Traversieren des GameTrees.
 * Nodes sollten Value, Alpha, Beta, Traversionstiefe, Boardstate und Arraylist an Kind-Nodes enthalten.
 * Vorsortieren der untersten Arraylist mittels entsprechender Collection sollte etwas Geschwindigkeit bringen.
 */

public class GameTree {
	
	class Node{
		
		Feld tmpFeld;
		
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
		}
		
		//Konstruktor für die anderen Knoten, die in der Frag Min/Max alternieren.
		public Node(Node parentNode){
			this.parent = parentNode;
			this.isMax = !parent.isMax; 
		}
		
		
		
	}

}
