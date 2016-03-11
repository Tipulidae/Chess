package rules;

import java.util.Observable;

import utils.PieceColor;
import utils.PieceType;

public class Square extends Observable {
	public Piece piece = Piece.NONE;
	
	public boolean isEmpty() {
		return piece.equals(Piece.NONE);
	}
	
	public void place(Piece p) {
		piece = p;
	}
	
	public PieceColor color() {
		return piece.color();
	}
	
	public PieceType type() {
		return piece.type();
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public boolean occupied() {
		return piece.type() != PieceType.NONE;
	}
	
	public void refresh() {
		setChanged();
		notifyObservers();
	}
	
	public void reset() {
		piece = Piece.NONE;
		refresh();
	}
}
