package gui;

import java.awt.Color;

import rules.Board;
import utils.Position;

public class ClickHandler {
	private Position from;
	private Position to;
	private SquareLabel selected;
	private Board board;
	
	public ClickHandler(Board board) {
		this.board = board;
	}
	
	public void handleClick(SquareLabel sl, BoardPanel bp) {
		Position pos = sl.getPos();
		//board.printValidMoves(pos);
		if (selected == null) {
			selected = sl;
			bp.markLegalMoves(board.printValidMoves(pos));
			selected.setBackground(Color.RED);
			from = pos;
		} else {
			//selected.restoreBackground();
			bp.unmarkAllSquares();
			selected = null;
			to = pos;
			makeMove();
		}
	}
	
	private void makeMove() {
		//System.out.println("making move "+from+" -> "+to);
		board.move(from,to);
	}
}
