package rules;

import java.util.ArrayList;
import java.util.List;

import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public class Pawn extends Piece {
	private Position homeSquare;
	public Pawn(Position pos, PieceColor color) {
		super(pos, color);
		type = PieceType.PAWN;
		homeSquare = pos;
	}

	@Override
	public void setPos(Position pos) {
		super.setPos(pos);
	}
	
	@Override
	public List<Position> validMoves(Board board) {
		List<Position> moves = new ArrayList<Position>();
		
		addForwardMoves(moves, board);
		addNormalAttackMoves(moves, board);
		addEnPassant(moves, board);
		
		return moves;
	}
	
	private void addForwardMoves(List<Position> moves, Board board) {
		int length = 1;
		if (!hasMoved()) length = 2;
		Position dir = new Position(0,1);
		if (color == PieceColor.WHITE) dir = new Position(0,-1);
		
		Position p = pos;
		int count = 0;
		while (count < length) {
			count++;
			p = Position.add(p,dir);
			if (!p.withinBounds() || board.occupied(p)) break;
			moves.add(p);
		}
	}
	
	private void addNormalAttackMoves(List<Position> moves, Board board) {
		Position p1, p2;
		if (color == PieceColor.WHITE) {
			p1 = Position.add(pos, new Position(-1,-1));
			p2 = Position.add(pos, new Position(1,-1));
		} else {
			p1 = Position.add(pos, new Position(-1,1));
			p2 = Position.add(pos, new Position(1,1));
		}
		if (attackable(p1, board)) moves.add(p1);
		if (attackable(p2, board)) moves.add(p2);
	}
	
	private boolean attackable(Position p, Board b) {
		return p.withinBounds() && b.colorAt(p) == PieceColor.opposite(color);
	}
	
	private void addEnPassant(List<Position> moves, Board board) {
		
	}
	
	private boolean hasMoved() {
		return !homeSquare.equals(pos);
	}
}
