package rules;

import java.util.List;

import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public class King extends Piece {

	public King(Position pos, PieceColor color) {
		super(pos, color);
		type = PieceType.KING;
	}

	@Override
	public List<Position> validMoves(Board board) {
		return filterOutInvalidMoves(pos.neighbours(), board);
	}

	
}
