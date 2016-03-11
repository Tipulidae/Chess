package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import rules.Square;
import utils.PieceColor;
import utils.PieceType;
import utils.Position;

public class SquareLabel extends JLabel implements Observer {
	private Position pos;
	
	public SquareLabel(Position pos) {
		this.pos = pos;
		restoreBackground();
		setOpaque(true);
		
		setIcon(PieceIcon.instance().icon(PieceColor.NONE,PieceType.NONE));
	}

	public Position getPos() {
		return pos;
	}
	
	public void restoreBackground() {
		if (pos.isWhite())
			setBackground(Color.LIGHT_GRAY);
		else
			setBackground(Color.DARK_GRAY);
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		Square sq = (Square)o;
		setIcon(PieceIcon.instance().icon(sq.color(), sq.type()));
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof SquareLabel) {
			SquareLabel other = (SquareLabel) o;
			return pos.equals(other.pos);
		}
		return false;
	}
}
