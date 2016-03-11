package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import rules.Board;
import rules.Move;
import utils.Position;

public class ClickHandler {
	private Position from;
	private Position to;
	private SquareLabel selected;
	private Board board;
	private List<Move> moveHistory;
	
	public ClickHandler(Board board) {
		this.board = board;
		moveHistory = new ArrayList<Move>();
	}
	
	public void handleClick(SquareLabel sl, BoardPanel bp) {
		Position pos = sl.getPos();
		if (selected == null) {
			if (board.occupied(pos)) {
				selected = sl;
				bp.markLegalMoves(board.validMoves(pos));
				selected.setBackground(Color.RED);
				from = pos;
			}
		} else {
			if (!selected.equals(sl)) {
				to = pos;
				makeMove();
			}
			bp.unmarkAllSquares();
			selected = null;
		}
	}
	
	public void undo() {
		if (moveHistory.isEmpty()) return;
		board.undoMoveAndRefresh(moveHistory.remove(moveHistory.size()-1));
	}
	
	private void makeMove() {
		//System.out.println("making move "+from+" -> "+to);
		//board.move(from,to);
		Move theMove = new Move(from, to);
		board.makeMoveAndRefresh(theMove);
		moveHistory.add(theMove);
	}
}
