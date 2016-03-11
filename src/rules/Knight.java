package rules;

import java.util.ArrayList;
import java.util.List;

import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public class Knight extends Piece {

	public Knight(Position pos, PieceColor color) {
		super(pos, color);
		type = PieceType.KNIGHT;
	}

	@Override
	public List<Position> validMoves(Board board) {
		List<Position> moves = new ArrayList<Position>();
		moves.add(Position.add(pos, new Position(1,-2)));
		moves.add(Position.add(pos, new Position(2,-1)));
		moves.add(Position.add(pos, new Position(2,1)));
		moves.add(Position.add(pos, new Position(1,2)));
		moves.add(Position.add(pos, new Position(-1,2)));
		moves.add(Position.add(pos, new Position(-2,1)));
		moves.add(Position.add(pos, new Position(-2,-1)));
		moves.add(Position.add(pos, new Position(-1,-2)));
		
		return filterOutInvalidMoves(moves, board);
	}

}
