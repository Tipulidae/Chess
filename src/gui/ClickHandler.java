package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import rules.Board;
import rules.Move;
import utils.PieceColor;
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
		Move lastMove = lastMove();
		System.out.println("undo: "+lastMove);
		board.undoMove(lastMove);
	}
	
	private void makeMove() {
		Move theMove = new Move(from, to);
		board.makeMove(theMove);
		System.out.println(theMove);
		moveHistory.add(theMove);
		//printAllValidMoves(PieceColor.opposite(theMove.mover.color()));
		//System.out.println(theMove.mover.color()+": "+theMove);
	}
	
	private void printAllValidMoves(PieceColor color) {
		System.out.print("Valid "+color+" moves: ");
		for (Move m : board.validMoves(color)) {
			System.out.print(m+"; ");
		}
		System.out.println();
	}
	
	private Move lastMove() {
		return moveHistory.remove(moveHistory.size()-1);
	}
}
