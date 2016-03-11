package rules;

import java.util.List;
import java.util.Observer;

import utils.PieceColor;
import utils.Position;

public class Board {
	//private List<Piece> pieces;
	private Square[][] squares;
	public Board() {
		squares = new Square[8][8];
		
		for (int x = 0; x<8; x++) {
			for (int y = 0; y<8; y++) {
				squares[x][y] = new Square();
			}
		}
		
		
		
		/*
		pieces = new ArrayList<Piece>();
		pieces.add(new Rook(new Position("a8"), PieceColor.BLACK));
		pieces.add(new Knight(new Position("b8"), PieceColor.BLACK));
		pieces.add(new Bishop(new Position("c8"), PieceColor.BLACK));
		pieces.add(new Queen(new Position("d8"), PieceColor.BLACK));
		pieces.add(new King(new Position("e8"), PieceColor.BLACK));
		pieces.add(new Bishop(new Position("f8"), PieceColor.BLACK));
		pieces.add(new Knight(new Position("g8"), PieceColor.BLACK));
		pieces.add(new Rook(new Position("h8"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("a7"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("b7"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("c7"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("d7"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("e7"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("f7"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("g7"), PieceColor.BLACK));
		pieces.add(new Pawn(new Position("h7"), PieceColor.BLACK));
		
		pieces.add(new Rook(new Position("a1"), PieceColor.WHITE));
		pieces.add(new Knight(new Position("b1"), PieceColor.WHITE));
		pieces.add(new Bishop(new Position("c1"), PieceColor.WHITE));
		pieces.add(new Queen(new Position("d1"), PieceColor.WHITE));
		pieces.add(new King(new Position("e1"), PieceColor.WHITE));
		pieces.add(new Bishop(new Position("f1"), PieceColor.WHITE));
		pieces.add(new Knight(new Position("g1"), PieceColor.WHITE));
		pieces.add(new Rook(new Position("h1"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("a2"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("b2"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("c2"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("d2"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("e2"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("f2"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("g2"), PieceColor.WHITE));
		pieces.add(new Pawn(new Position("h2"), PieceColor.WHITE));
		*/
	}
	
	public void addSquareObserver(Observer o, Position pos) {
		squares[pos.x][pos.y].addObserver(o);
	}
	
	public void setStartPositions() {
		for (int x=0; x<8; x++) {
			for (int y=0; y<8; y++) {
				squares[x][y].reset();
			}
		}
		squares[0][0].place(new Rook(new Position("a8"), PieceColor.BLACK));
		squares[1][0].place(new Knight(new Position("b8"), PieceColor.BLACK));
		squares[2][0].place(new Bishop(new Position("c8"), PieceColor.BLACK));
		squares[3][0].place(new Queen(new Position("d8"), PieceColor.BLACK));
		squares[4][0].place(new King(new Position("e8"), PieceColor.BLACK));
		squares[5][0].place(new Bishop(new Position("f8"), PieceColor.BLACK));
		squares[6][0].place(new Knight(new Position("g8"), PieceColor.BLACK));
		squares[7][0].place(new Rook(new Position("h8"), PieceColor.BLACK));
		squares[0][1].place(new Pawn(new Position("a7"), PieceColor.BLACK));
		squares[1][1].place(new Pawn(new Position("b7"), PieceColor.BLACK));
		squares[2][1].place(new Pawn(new Position("c7"), PieceColor.BLACK));
		squares[3][1].place(new Pawn(new Position("d7"), PieceColor.BLACK));
		squares[4][1].place(new Pawn(new Position("e7"), PieceColor.BLACK));
		squares[5][1].place(new Pawn(new Position("f7"), PieceColor.BLACK));
		squares[6][1].place(new Pawn(new Position("g7"), PieceColor.BLACK));
		squares[7][1].place(new Pawn(new Position("h7"), PieceColor.BLACK));
		
		squares[0][7].place(new Rook(new Position("a1"), PieceColor.WHITE));
		squares[1][7].place(new Knight(new Position("b1"), PieceColor.WHITE));
		squares[2][7].place(new Bishop(new Position("c1"), PieceColor.WHITE));
		squares[3][7].place(new Queen(new Position("d1"), PieceColor.WHITE));
		squares[4][7].place(new King(new Position("e1"), PieceColor.WHITE));
		squares[5][7].place(new Bishop(new Position("f1"), PieceColor.WHITE));
		squares[6][7].place(new Knight(new Position("g1"), PieceColor.WHITE));
		squares[7][7].place(new Rook(new Position("h1"), PieceColor.WHITE));
		squares[0][6].place(new Pawn(new Position("a2"), PieceColor.WHITE));
		squares[1][6].place(new Pawn(new Position("b2"), PieceColor.WHITE));
		squares[2][6].place(new Pawn(new Position("c2"), PieceColor.WHITE));
		squares[3][6].place(new Pawn(new Position("d2"), PieceColor.WHITE));
		squares[4][6].place(new Pawn(new Position("e2"), PieceColor.WHITE));
		squares[5][6].place(new Pawn(new Position("f2"), PieceColor.WHITE));
		squares[6][6].place(new Pawn(new Position("g2"), PieceColor.WHITE));
		squares[7][6].place(new Pawn(new Position("h2"), PieceColor.WHITE));
	}
	
	public void move(Position from, Position to) {
		Piece selectedPiece = squares[from.x][from.y].getPiece();
		squares[from.x][from.y].place(Piece.NONE);
		squares[to.x][to.y].place(selectedPiece);
		selectedPiece.setPos(to);
	}
	
	public List<Position> printValidMoves(Position pos) {
		Piece p = squares[pos.x][pos.y].getPiece();
		return p.validMoves(this);
	}
	
	public boolean occupied(Position pos) {
		return squares[pos.x][pos.y].occupied();
	}
	
	public PieceColor colorAt(Position pos) {
		return squares[pos.x][pos.y].color();
	}
}
