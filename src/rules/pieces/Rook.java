package rules.pieces;

import java.util.ArrayList;
import java.util.List;

import rules.Board;
import rules.Piece;
import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public class Rook extends Piece {
	private boolean hasMoved = false;
	public Rook(Position pos, PieceColor color) {
		super(pos, color);
		type = PieceType.ROOK;
	}

	@Override
	public void setPos(Position pos) {
		super.setPos(pos);
		hasMoved = true;
	}
	
	@Override
	public List<Position> validMoves(Board board) {
		List<Position> moves = new ArrayList<Position>();
		addPositionsInLine(moves, board, new Position(0,-1), 8);
		addPositionsInLine(moves, board, new Position(0,1), 8);
		addPositionsInLine(moves, board, new Position(1,0), 8);
		addPositionsInLine(moves, board, new Position(-1,0), 8);
		return moves;
	}

	@Override
	public String pieceName() {
		return "R";
	}
}
