package rules.pieces;

import java.util.ArrayList;
import java.util.List;

import rules.Board;
import rules.Piece;
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
		List<Position> moves = filterOutInvalidMoves(pos.neighbours(), board);
		addQueenSideCastle(board, moves);
		return moves;
	}

	private void addQueenSideCastle(Board board, List<Position> moves) {
		if (hasMoved() || board.pieceAt(queenSideRookPos()).hasMoved()) return;
		if (!queenSidePositionsEmpty(board)) return;
		if (!positionsAreSafe(board, queenSideSafePositions())) return;
		moves.add(queenSideRookPos());
	}

	@Override
	public String pieceName() {
		return "K";
	}

	private Position queenSideRookPos() {
		return Position.add(pos, new Position(-4,0));
	}
	
	private Position kingSideRookPos() {
		return Position.add(pos, new Position(3,0));
	}
	
	private boolean queenSidePositionsEmpty(Board board) {
		if (color == PieceColor.WHITE)
			return !(board.occupied(new Position("b1")) ||
					 board.occupied(new Position("c1")) ||
					 board.occupied(new Position("d1")));
		else
			return !(board.occupied(new Position("b8")) ||
					 board.occupied(new Position("c8")) ||
					 board.occupied(new Position("d8")));
	}
	
	private List<Position> queenSideSafePositions() {
		List<Position> positions = new ArrayList<Position>();
		positions.add(pos);
		positions.add(Position.add(pos, new Position(-1,0)));
		positions.add(Position.add(pos, new Position(-2,0)));
		positions.add(Position.add(pos, new Position(-3,0)));
		return positions;
	}
	
	private boolean positionsAreSafe(Board board, List<Position> positions) {
		for (Position p : positions) {
			if (!board.squareIsSafeFrom(p, PieceColor.opposite(color)))
				return false;
		}
		return true;
	}
	
}
