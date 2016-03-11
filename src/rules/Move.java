package rules;

import utils.Position;

public class Move {
	public Piece mover;
	public Piece target;
	public Position from;
	public Position to;
	
	public Move(Position from, Position to) {
		this.from = from;
		this.to = to;
	}
	/*
	public void rememberMove(Piece mover, Piece target) {
		this.mover = mover;
		this.target = target;
	}
	
	public void undoMove(Board board) {
		
	}*/
}
