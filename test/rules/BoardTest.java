package rules;

import static org.junit.Assert.*;

import org.junit.Test;

import rules.*;
import rules.pieces.*;
import utils.*;

public class BoardTest {
	
	private Board board;

	@Test
	public void canPlacePieceOnBoard() {
		board = new Board();
		Position a1 = new Position("a1");
		Piece piece = new King(a1, PieceColor.BLACK);
		
		assertFalse(board.occupied(a1));
		board.place(piece);
		assertTrue(board.occupied(a1));
		assertEquals(piece, board.pieceAt(a1));
	}
	
	@Test
	public void movedPieceChangesPosition() {
		board = new Board();
		Position a1 = new Position("a1");
		Position a2 = new Position("a2");
		Piece piece = new King(a1, PieceColor.BLACK);
		
		assertEquals(a1,piece.getPos());
		assertNotEquals(a2,piece.getPos());
		
		board.place(piece);
		assertEquals(a1,piece.getPos());
		assertNotEquals(a2,piece.getPos());
		assertTrue(board.occupied(a1));
		assertFalse(board.occupied(a2));
		
		//board.makeMove(new Move(a1,a2));
		assertNotEquals(a1,piece.getPos());
		assertEquals(a2,piece.getPos());
		assertFalse(board.occupied(a1));
		assertTrue(board.occupied(a2));
	}
	
	@Test
	public void moveCanBeUndone() {
		board = new Board();
		Position a1 = new Position("a1");
		Position a2 = new Position("a2");
		Position a3 = new Position("a3");
		Position a4 = new Position("a4");
		Piece piece = new King(a1, PieceColor.BLACK);
		board.place(piece);
		
		Move m1 = new Move(a1,a2);
		Move m2 = new Move(a2,a3);
		Move m3 = new Move(a3,a4);
		
		//board.makeMove(m1);
		assertEquals(a2,piece.getPos());
		//board.makeMove(m2);
		assertEquals(a3,piece.getPos());
		//board.makeMove(m3);
		assertEquals(a4,piece.getPos());
		
		//board.undoMove(m3);
		assertEquals(a3,piece.getPos());
		//board.undoMove(m2);
		assertEquals(a2,piece.getPos());
		//board.undoMove(m1);
		assertEquals(a1,piece.getPos());
	}

	
	@Test
	public void capturedPieceIsGone() {
		board = new Board();
		Position c1 = new Position("c1");
		Position c5 = new Position("c5");
		Piece rook = new Rook(c1, PieceColor.WHITE);
		Piece pawn = new Pawn(c5, PieceColor.BLACK);
		
		board.place(rook);
		board.place(pawn);
		
		Move Rxc5 = new Move(c1,c5);
		//board.makeMove(Rxc5);
		
		assertEquals(rook, board.pieceAt(c5));
		assertFalse(board.occupied(c1));
		
	}
}
