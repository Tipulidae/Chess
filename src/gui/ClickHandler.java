package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import rules.Board;
import rules.Move;
import utils.PieceColor;
import utils.Position;

public class ClickHandler {
	private SquareLabel selected;
	private BoardPanel bp;
	private Board board;
	private List<Move> moveHistory;
	
	private List<Move> possibleMoves;
	
	public ClickHandler(Board board, BoardPanel bp) {
		this.board = board;
		this.bp = bp;
		moveHistory = new ArrayList<Move>();
	}
	
	public void undo() {
		if (moveHistory.isEmpty()) return;
		Move m = lastMove();
		System.out.println("undo: "+m);
		m.undo(board);
		m.refresh(board);
		resetSelections();
	}
	
	public void handleClick(SquareLabel sl) {
		Position pos = sl.getPos();
		if (firstClick()) {
			select(sl);
		} else if (selectionIsValid(pos)) {
			makeMove(pos);
		} else if (board.occupied(pos)) {
			select(sl);
		} else {
			resetSelections();
		}
	}
	
	private boolean firstClick() {
		return selected == null;
	}
	
	
	
	private void select(SquareLabel sl) {
		Position pos = sl.getPos();
		if (board.occupied(pos)) {
			possibleMoves = board.validMovesFrom(pos);
			bp.markLegalMoves(possibleMoves.stream().
					map(move -> move.to).
					collect(Collectors.toList()));
			
			selected = sl;
			selected.setBackground(Color.RED);
		}
	}
	
	private boolean selectionIsValid(Position pos) {
		return getMove(pos) != null;
	}
	
	private Move getMove(Position pos) {
		for (Move m : possibleMoves) {
			if (pos.equals(m.to)) return m;
		}
		return null;
	}
	
	private void makeMove(Position pos) {
		Move m = getMove(pos);
		//board.makeMove(m);
		m.perform(board);
		m.refresh(board);
		//board.refreshMove(m);
		moveHistory.add(m);
		System.out.println(m);
		resetSelections();
	}
	
	private void resetSelections() {
		bp.unmarkAllSquares();
		selected = null;
		possibleMoves = Collections.emptyList();
	}
	
	private Move lastMove() {
		return moveHistory.remove(moveHistory.size()-1);
	}
}
