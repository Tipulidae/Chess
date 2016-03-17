package rules;

import rules.pieces.Queen;

public class Promotion extends Move {
	private Piece promotion;
	public Promotion(Move move) {
		this.from = move.from;
		this.to = move.to;
		this.mover = move.mover;
		this.target = move.target;
	}
	
	@Override
	public void perform(Board board) {
		promotion = new Queen(to, mover.color());
		board.square(from).place(Piece.NONE);
		board.place(promotion);
		
		board.removePiece(target);
		board.removePiece(mover);
	}
	
	@Override
	public void undo(Board board) {
		board.removePiece(promotion);
		board.square(to).place(target);
		board.square(from).place(mover);
		
		
		board.addPiece(target);
		board.addPiece(mover);
	}
}
