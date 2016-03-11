package gui;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import utils.PieceColor;
import utils.PieceType;

public class PieceIcon {
	private Icon blackKing;
	private Icon blackQueen;
	private Icon blackRook;
	private Icon blackBishop;
	private Icon blackKnight;
	private Icon blackPawn;
	private Icon whiteKing;
	private Icon whiteQueen;
	private Icon whiteRook;
	private Icon whiteBishop;
	private Icon whiteKnight;
	private Icon whitePawn;
	private Icon none;

	private static PieceIcon instance;

	private PieceIcon() {
		try {
			//BufferedImage skin = ImageIO.read(new File("images/xpskin.bmp"));
			//defaultCell = new ImageIcon(skin.getSubimage(0, 16, 16, 16));
			
			BufferedImage skin = ImageIO.read((this.getClass().getResource("/images/Chess_Pieces_Sprite.svg.png")));
			whiteKing = new ImageIcon(skin.getSubimage(0,0,33,33));
			whiteQueen = new ImageIcon(skin.getSubimage(33,0,33,33));
			whiteBishop = new ImageIcon(skin.getSubimage(67,0,33,33));
			whiteKnight = new ImageIcon(skin.getSubimage(100,0,33,33));
			whiteRook = new ImageIcon(skin.getSubimage(133,0,33,33));
			whitePawn = new ImageIcon(skin.getSubimage(167,0,33,33));
			
			blackKing = new ImageIcon(skin.getSubimage(0,33,33,33));
			blackQueen = new ImageIcon(skin.getSubimage(33,33,33,33));
			blackBishop = new ImageIcon(skin.getSubimage(67,33,33,33));
			blackKnight = new ImageIcon(skin.getSubimage(100,33,33,33));
			blackRook = new ImageIcon(skin.getSubimage(133,33,33,33));
			blackPawn = new ImageIcon(skin.getSubimage(167,33,33,33));
			//white = new ImageIcon((this.getClass().getResource("/images/white.png")));
			none = new ImageIcon(this.getClass().getResource("/images/empty.png"));
		} catch (Exception e) {
			System.err.println("unable to load images.");
			e.printStackTrace();
			System.exit(1);
		}
		instance = this;
	}

	public static PieceIcon instance() {
		if (instance == null)
			return new PieceIcon();
		else
			return instance;
	}
	
	public Icon icon(PieceColor color, PieceType type) {
		switch (color) {
		case BLACK:
			switch (type) {
			case KING: return blackKing;
			case QUEEN: return blackQueen;
			case ROOK: return blackRook;
			case BISHOP: return blackBishop;
			case KNIGHT: return blackKnight;
			case PAWN: return blackPawn;
			default: break;
			}
		case WHITE:
			switch (type) {
			case KING: return whiteKing;
			case QUEEN: return whiteQueen;
			case ROOK: return whiteRook;
			case BISHOP: return whiteBishop;
			case KNIGHT: return whiteKnight;
			case PAWN: return whitePawn;
			default: break;
			}
		default: break;
		}
		return none;
	}
}
