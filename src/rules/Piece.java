package rules;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public abstract class Piece {
	protected Position pos;
	protected PieceColor color;
	protected PieceType type;
	public Piece() {
	}
	
	public Piece(Position pos, PieceColor color) {
		this.pos = pos;
		this.color = color;
	}
	
	public PieceColor color() {
		return color;
	}
	
	public PieceType type() {
		return type;
	}
	
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public abstract List<Position> validMoves(Board board);
	
	protected void addPositionsInLine(List<Position> positions, Board board, Position dir, int length) {
		Position p = pos;
		int count = 0;
		while (count < length) {
			count++;
			p = Position.add(p,dir);
			if (p.withinBounds()) {
				if (board.colorAt(p) == color) break;
				positions.add(p);
				if (board.occupied(p)) break;
			} else {
				break;
			}
		}
	}
	
	protected List<Position> filterOutInvalidMoves(List<Position> moves, Board board) {
		return moves.stream().
				filter(p -> isValid(p,board)).
				collect(Collectors.toList());
	}
	
	protected boolean isValid(Position p, Board b) {
		return p.withinBounds() && (!b.occupied(p) || b.colorAt(p) != color);
	}
	
	public static final Piece NONE = new Piece() {
		@Override
		public PieceColor color() {
			return PieceColor.NONE;
		}

		@Override
		public PieceType type() {
			return PieceType.NONE;
		}

		@Override
		public List<Position> validMoves(Board board) {
			return Collections.emptyList();
			
		}
		
	};
}
