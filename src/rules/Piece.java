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
	private boolean hasMoved = false;
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
		hasMoved = true;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void youNeverMoved() {
		hasMoved = false;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	@Override
	public String toString() {
		return color+" "+type+" "+pos;
	}
	
	public List<Move> realMoves(Board board) {
		return validMoves(board).stream().
				map(to -> createMove(board, pos, to)).
				filter(move -> board.kingWouldBeSafe(move)).
				collect(Collectors.toList());
	}
	
	
	protected Move createMove(Board board, Position from, Position to) {
		Move move = new Move(from, to);
		move.mover = board.pieceAt(from);
		move.target = board.pieceAt(to);
		move.thisIsFirstMove = !hasMoved;
		
		return move;
	}
	
	public abstract String pieceName();
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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Piece) {
			Piece other = (Piece) o;
			return pos.equals(other.pos) && type == other.type && color == other.color;
		}
		return false;
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

		@Override
		public String pieceName() {
			return "";
		}
		
	};
}
