package rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import utils.PieceColor;
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
		
		blackKing = new King(new Position("e8"), PieceColor.BLACK);
		whiteKing = new King(new Position("e1"), PieceColor.WHITE);
		blackPieces = new ArrayList<Piece>();
		whitePieces = new ArrayList<Piece>();
		
		squares[0][0].place(new Rook(new Position("a8"), PieceColor.BLACK));
		squares[1][0].place(new Knight(new Position("b8"), PieceColor.BLACK));
		squares[2][0].place(new Bishop(new Position("c8"), PieceColor.BLACK));
		squares[3][0].place(new Queen(new Position("d8"), PieceColor.BLACK));
		squares[4][0].place(blackKing);
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
		for (int x=0; x<8; x++) {
			for (int y=0; y<2; y++) {
				blackPieces.add(squares[x][y].getPiece());
				squares[x][y].refresh();
			}
		}
		
		
		squares[0][7].place(new Rook(new Position("a1"), PieceColor.WHITE));
		squares[1][7].place(new Knight(new Position("b1"), PieceColor.WHITE));
		squares[2][7].place(new Bishop(new Position("c1"), PieceColor.WHITE));
		squares[3][7].place(new Queen(new Position("d1"), PieceColor.WHITE));
		squares[4][7].place(whiteKing);
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
		for (int x=0; x<8; x++) {
			for (int y=6; y<8; y++) {
				whitePieces.add(squares[x][y].getPiece());
				squares[x][y].refresh();
			}
		}
	}
	
	public void makeMoveAndRefresh(Move move) {
		makeMove(move);
		refreshMove(move);
		if (kingIsUnderAttack(PieceColor.opposite(move.mover.color())))
			System.out.println("CHECK!");
	}
	
	public void undoMoveAndRefresh(Move move) {
		undoMove(move);
		refreshMove(move);
	}
	
	public void makeMove(Move move) {
		move.mover = squares[move.from.x][move.from.y].getPiece();
		move.target = squares[move.to.x][move.to.y].getPiece();
		
		square(move.from).place(Piece.NONE);
		square(move.to).place(move.mover);
		move.mover.setPos(move.to);
		
		if (move.target.color() == PieceColor.BLACK) blackPieces.remove(move.target);
		else if (move.target.color() == PieceColor.WHITE) whitePieces.remove(move.target);
		
		
	}
	
	public void undoMove(Move move) {
		square(move.to).place(move.target);
		square(move.from).place(move.mover);
		move.mover.setPos(move.from);
		
		if (move.target.color() == PieceColor.BLACK) blackPieces.add(move.target);
		else if (move.target.color() == PieceColor.WHITE) whitePieces.add(move.target);
	}
	
	public void refreshMove(Move move) {
		square(move.from).refresh();
		square(move.to).refresh();
	}
	
	public List<Position> validMoves(Position pos) {
		Piece attacker = square(pos).getPiece();
		return attacker.validMoves(this).stream().
				filter(dest -> kingWouldBeSafe(new Move(pos,dest))).
				collect(Collectors.toList());
	}
	
	
	private boolean kingWouldBeSafe(Move move) {
		makeMove(move);
		boolean result = !kingIsUnderAttack(move.mover.color());
		undoMove(move);
		return result;
	}
	
	
	
	private boolean kingIsUnderAttack(PieceColor color) {
		
		PieceColor attackColor = PieceColor.opposite(color);
		if (attackColor == PieceColor.BLACK) {
			for (Piece potentialAttacker : blackPieces) {
				for (Position potentialAttack : potentialAttacker.validMoves(this)) {
					if (whiteKing.getPos().equals(potentialAttack)) {
						return true;
					}
				}
			}
		} else if (attackColor == PieceColor.WHITE) {
			for (Piece potentialAttacker : whitePieces) {
				for (Position potentialAttack : potentialAttacker.validMoves(this)) {
					if (blackKing.getPos().equals(potentialAttack)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	
	
	public boolean occupied(Position pos) {
		return square(pos).occupied();
	}
	
	public Piece pieceAt(Position pos) {
		return square(pos).getPiece();
	}
	
	public PieceColor colorAt(Position pos) {
		return square(pos).color();
	}
	
	private Square square(Position p) {
		return squares[p.x][p.y];
	}
}
