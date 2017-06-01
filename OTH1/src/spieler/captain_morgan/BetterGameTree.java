package spieler.captain_morgan;

import java.util.ArrayList;

import spieler.Zug;

public class BetterGameTree {

	public static void buildTree(int level, Node thisNode, boolean foe, Feld feldCopy) {
//System.out.println("level: " + level);
		if (level == 0) {
			thisNode.value = BoardEvaluator.getBoardValue(feldCopy, foe ? Spieler.opponentColor : Spieler.ownColor,
					!foe ? Spieler.opponentColor : Spieler.ownColor);

			if (thisNode.parent.isFoe) {
				if (thisNode.value < thisNode.parent.beta) {
					thisNode.parent.beta = thisNode.value;
				}
			} else {
				if (thisNode.value > thisNode.parent.alpha) {
					thisNode.parent.alpha = thisNode.value;
				}
			}
			return;
		} else { // Rekursion
			ArrayList<Zug> possibleMoves;

			if (foe) {
				possibleMoves = feldCopy.getAllPossibleMoves(Spieler.opponentColor, Spieler.ownColor);
			} else {
				possibleMoves = feldCopy.getAllPossibleMoves(Spieler.ownColor, Spieler.opponentColor);
			}
			if (possibleMoves.isEmpty()) {
				possibleMoves.add(new Zug(-1, -1));
			}
			thisNode.isFoe = foe;
			for (Zug move : possibleMoves) {
				Node next = new Node();
				next.move = move;
				next.parent = thisNode;
				// next.alpha=thisNode.alpha;
				// next.beta=thisNode.beta;
				thisNode.children.add(next);

			}
			for (Node nextNode : thisNode.children) {
				// Board nextBoard = BoardHandler.setAndUpdate(foe ?
				// Spieler.opponentColor : Spieler.myColor,
				// !foe ? Spieler.opponentColor : Spieler.myColor, actualBoard,
				// nextNode.move);

				nextNode.alpha = thisNode.alpha;
				nextNode.beta = thisNode.beta;
				if (thisNode.alpha >= thisNode.beta) {
					// System.err.println("Prune because "+ thisNode.alpha+ " "+
					// thisNode.beta+ " "+ level);
					break;
				}
				
				//****************** ???
				
				
				feldCopy.setField(nextNode.move.getZeile(),	nextNode.move.getSpalte(), foe ? Spieler.opponentColor : Spieler.ownColor);
				

				//****************** ???
				
				
				buildTree(level - 1, nextNode, !foe, feldCopy);
			}
			if (thisNode.parent != null) {
				// get rid of useless kids, if these are not the only relatives
				// you have.
				thisNode.children.clear();
			}
		} // Ende der Rehkuhsion-->Bambi ist wieder da :=)
		if (foe) {
			thisNode.value = thisNode.beta;
			if (thisNode.parent != null && thisNode.parent.alpha < thisNode.beta)
				thisNode.parent.alpha = thisNode.beta;
		} else {
			thisNode.value = thisNode.alpha;
			if (thisNode.parent != null && thisNode.parent.beta > thisNode.alpha)
				thisNode.parent.beta = thisNode.alpha;
		}

	}

}
