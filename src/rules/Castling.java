package rules;

import utils.PieceColor;
import utils.Position;

public class Castling extends Move {
	private Position kingDest;
	private Position rookDest;
	private boolean queenSide;
	
	public Castling(Board board, PieceColor color, boolean queenSide) {
		this.queenSide = queenSide;
		if (color == PieceColor.WHITE) from = new Position("e1");
		else from = new Position("e8");
		
		if (queenSide) {
			to = Position.add(from,new Position(-4,0));
			kingDest = Position.add(from, new Position(-2,0));
			rookDest = Position.add(from, new Position(-1,0));
		} else {
			to = Position.add(from,new Position(3,0));
			kingDest = Position.add(from, new Position(2,0));
			rookDest = Position.add(from, new Position(1,0));
		}
		
		mover = board.pieceAt(from);
		target = board.pieceAt(to);
	}
	
	@Override
	public void perform(Board board) {
		board.square(from).place(Piece.NONE);
		board.square(to).place(Piece.NONE);
		
		board.square(kingDest).place(mover);
		board.square(rookDest).place(target);
		mover.setPos(kingDest);
		target.setPos(rookDest);
	}
	
	@Override
	public void undo(Board board) {
		board.square(to).place(target);
		board.square(from).place(mover);
		board.square(kingDest).place(Piece.NONE);
		board.square(rookDest).place(Piece.NONE);
		mover.setPos(from);
		target.setPos(to);
		
		mover.youNeverMoved();
		target.youNeverMoved();
	}
	
	@Override
	public void refresh(Board board) {
		board.square(from).refresh();
		board.square(to).refresh();
		board.square(kingDest).refresh();
		board.square(rookDest).refresh();
		//if (!board.kingIsSafe(PieceColor.opposite(mover.color())))
		//	check = true;
	}
	
	@Override
	public String toString() {
		if (queenSide) return "O-O-O";
		else return "O-O";
	}
	
}
