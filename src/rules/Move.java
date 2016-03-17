package rules;

import utils.PieceType;
import utils.Position;

public class Move {
	public Piece mover = Piece.NONE;
	public Piece target = Piece.NONE;
	public Position from;
	public Position to;
	public boolean check = false;
	
	public Move(Position from, Position to) {
		this.from = from;
		this.to = to;
	}
	
	public Move(String from, String to) {
		this.from = new Position(from);
		this.to = new Position(to);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Move) {
			Move other = (Move) o;
			return from.equals(other.from) && to.equals(other.to);
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(mover.pieceName());
		sb.append(from);
		sb.append(connector());
		sb.append(target.pieceName());
		sb.append(to);
		sb.append(check());
		return sb.toString();
	}
	
	private String connector() {
		if (target.type() == PieceType.NONE) {
			return "-";
		} else {
			return "x";
		}
	}
	
	private String check() {
		return check?"+":"";
	}
}
