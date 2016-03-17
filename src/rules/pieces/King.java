package rules.pieces;

import java.util.ArrayList;
import java.util.List;

import rules.Board;
import rules.Castling;
import rules.Move;
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
		//addQueenSideCastle(board, moves);
		return moves;
	}
	
	@Override
	public List<Move> realMoves(Board board) {
		List<Move> moves = super.realMoves(board);
		addQueenSideCastle(board, moves);
		addKingSideCastle(board, moves);
		return moves;
	}

	private void addQueenSideCastle(Board board, List<Move> moves) {
		if (hasMoved() || board.pieceAt(queenSideRookPos()).hasMoved()) return;
		if (!queenSidePositionsEmpty(board)) return;
		if (!positionsAreSafe(board, queenSideSafePositions())) return;
		moves.add(new Castling(board, color, true));
		//moves.add(queenSideRookPos());
	}
	
	private void addKingSideCastle(Board board, List<Move> moves) {
		if (hasMoved() || board.pieceAt(kingSideRookPos()).hasMoved()) return;
		if (!kingSidePositionsEmpty(board)) return;
		if (!positionsAreSafe(board, kingSideSafePositions())) return;
		moves.add(new Castling(board, color, false));
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
		return !(board.occupied(Position.add(pos, new Position(-1,0))) ||
				 board.occupied(Position.add(pos, new Position(-2,0))) ||
				 board.occupied(Position.add(pos, new Position(-3,0))));
	}
	
	private boolean kingSidePositionsEmpty(Board board) {
		return !(board.occupied(Position.add(pos, new Position(1,0))) ||
				 board.occupied(Position.add(pos, new Position(2,0))));
	}
	
	private List<Position> queenSideSafePositions() {
		List<Position> positions = new ArrayList<Position>();
		positions.add(pos);
		positions.add(Position.add(pos, new Position(-1,0)));
		positions.add(Position.add(pos, new Position(-2,0)));
		return positions;
	}
	
	private List<Position> kingSideSafePositions() {
		List<Position> positions = new ArrayList<Position>();
		positions.add(pos);
		positions.add(Position.add(pos, new Position(1,0)));
		positions.add(Position.add(pos, new Position(2,0)));
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
