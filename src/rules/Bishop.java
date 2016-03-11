package rules;

import java.util.ArrayList;
import java.util.List;

import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public class Bishop extends Piece {

	public Bishop(Position pos, PieceColor color) {
		super(pos, color);
		type = PieceType.BISHOP;
	}

	@Override
	public List<Position> validMoves(Board board) {
		List<Position> moves = new ArrayList<Position>();
		addPositionsInLine(moves, board, new Position(1,1), 8);
		addPositionsInLine(moves, board, new Position(1,-1), 8);
		addPositionsInLine(moves, board, new Position(-1,1), 8);
		addPositionsInLine(moves, board, new Position(-1,-1), 8);
		return moves;
	}

}
