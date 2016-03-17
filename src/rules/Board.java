package rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import rules.pieces.Bishop;
import rules.pieces.King;
import rules.pieces.Knight;
import rules.pieces.Pawn;
import rules.pieces.Queen;
import rules.pieces.Rook;
import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public class Board {
	//private List<Piece> pieces;
	private Square[][] squares;
	private Piece blackKing;
	private Piece whiteKing;
	private List<Piece> blackPieces;
	private List<Piece> whitePieces;
	public Board() {
		squares = new Square[8][8];
		
		for (int x = 0; x<8; x++) {
			for (int y = 0; y<8; y++) {
				squares[x][y] = new Square();
			}
		}
		
		blackPieces = new ArrayList<Piece>();
		whitePieces = new ArrayList<Piece>();
		blackKing = Piece.NONE;
		whiteKing = Piece.NONE;
	}
	
	public void addSquareObserver(Observer o, Position pos) {
		square(pos).addObserver(o);
	}
	
	public void setStartPositions() {
		for (int x=0; x<8; x++) {
			for (int y=0; y<8; y++) {
				squares[x][y].reset();
			}
		}
		
		place(new Rook(new Position("a1"), PieceColor.WHITE));
		place(new Knight(new Position("b1"), PieceColor.WHITE));
		place(new Bishop(new Position("c1"), PieceColor.WHITE));
		place(new Queen(new Position("d1"), PieceColor.WHITE));
		place(new King(new Position("e1"), PieceColor.WHITE));
		place(new Bishop(new Position("f1"), PieceColor.WHITE));
		place(new Knight(new Position("g1"), PieceColor.WHITE));
		place(new Rook(new Position("h1"), PieceColor.WHITE));
		place(new Pawn(new Position("a2"), PieceColor.WHITE));
		place(new Pawn(new Position("b2"), PieceColor.WHITE));
		place(new Pawn(new Position("c2"), PieceColor.WHITE));
		place(new Pawn(new Position("d2"), PieceColor.WHITE));
		place(new Pawn(new Position("e2"), PieceColor.WHITE));
		place(new Pawn(new Position("f2"), PieceColor.WHITE));
		place(new Pawn(new Position("g2"), PieceColor.WHITE));
		place(new Pawn(new Position("h2"), PieceColor.WHITE));
		
		place(new Rook(new Position("a8"), PieceColor.BLACK));
		place(new Knight(new Position("b8"), PieceColor.BLACK));
		place(new Bishop(new Position("c8"), PieceColor.BLACK));
		place(new Queen(new Position("d8"), PieceColor.BLACK));
		place(new King(new Position("e8"), PieceColor.BLACK));
		place(new Bishop(new Position("f8"), PieceColor.BLACK));
		place(new Knight(new Position("g8"), PieceColor.BLACK));
		place(new Rook(new Position("h8"), PieceColor.BLACK));
		place(new Pawn(new Position("a7"), PieceColor.BLACK));
		place(new Pawn(new Position("b7"), PieceColor.BLACK));
		place(new Pawn(new Position("c7"), PieceColor.BLACK));
		place(new Pawn(new Position("d7"), PieceColor.BLACK));
		place(new Pawn(new Position("e7"), PieceColor.BLACK));
		place(new Pawn(new Position("f7"), PieceColor.BLACK));
		place(new Pawn(new Position("g7"), PieceColor.BLACK));
		place(new Pawn(new Position("h7"), PieceColor.BLACK));
	}
	
	
	
	public List<Move> validMovesFrom(Position from) {
		Piece attacker = square(from).getPiece();
		return attacker.realMoves(this);
	}
	/*
	public List<Position> validMoves(Position from) {
		Piece attacker = square(from).getPiece();
		return attacker.validMoves(this).stream().
				filter(to -> kingWouldBeSafe(new Move(from,to))).
				collect(Collectors.toList());
	}
	
	public List<Move> validMoves(PieceColor color) {
		List<Move> moves = new ArrayList<Move>();		
		for (Piece p : pieces(color)) {
			Position from = p.getPos();
			for (Position to : validMoves(from)) {
				Move m = new Move(from, to);
				m.mover = square(from).getPiece();
				m.target = square(to).getPiece();
				moves.add(m);
			}
		}
		
		return moves;
	}*/
	
	
	
	
	
	public boolean occupied(Position pos) {
		return square(pos).occupied();
	}
	
	public Piece pieceAt(Position pos) {
		return square(pos).getPiece();
	}
	
	public PieceColor colorAt(Position pos) {
		return square(pos).color();
	}
	
	public void place(Piece piece) {
		Position p = piece.getPos();
		square(p).place(piece);
		addPiece(piece);
		
		if (piece.type() == PieceType.KING) {
			if (piece.color() == PieceColor.WHITE) whiteKing = piece;
			else if (piece.color() == PieceColor.BLACK) blackKing = piece;
		}
		square(p).refresh();
	}
	
	
	public boolean kingWouldBeSafe(Move move) {
		move.perform(this);
		boolean result = kingIsSafe(move.mover.color());
		move.undo(this);
		return result;
	}
	
	
	
	public boolean kingIsSafe(PieceColor color) {
		return squareIsSafeFrom(king(color).getPos(), PieceColor.opposite(color));
	}
	
	public boolean squareIsSafeFrom(Position pos, PieceColor color) {
		for (Piece attacker : pieces(color)) {
			for (Position attackPos : attacker.validMoves(this)) {
				if (attackPos.equals(pos)) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	public Square square(Position p) {
		return squares[p.x][p.y];
	}
	
	public void removePiece(Piece p) {
		pieces(p.color()).remove(p);
	}
	
	public void addPiece(Piece p) {
		pieces(p.color()).add(p);
	}
	
	private List<Piece> pieces(PieceColor color) {
		if (color == PieceColor.WHITE) return whitePieces;
		else if (color == PieceColor.BLACK) return blackPieces;
		return new ArrayList<Piece>();
	}
	
	private Piece king(PieceColor color) {
		if (color == PieceColor.WHITE) return whiteKing;
		else if (color == PieceColor.BLACK) return blackKing;
		return Piece.NONE;
	}
	
}
