package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observer;

import javax.swing.JPanel;

import rules.Board;
import utils.Position;

public class BoardPanel extends JPanel {
	private SquareLabel selectedLabel = null; 
	private ClickHandler ch;
	public BoardPanel() {
		super(new GridLayout(8, 8, 0, 0));
		setBackground(Color.RED);
		setSize(500, 500);
		BoardPanel bp = this;
		MouseAdapter ma = new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON1) {
					SquareLabel sl = (SquareLabel) me.getSource();
					ch.handleClick(sl);
				} else {
					ch.undo();
				}
				//clickHandler.handleHumanClick(sl.getPos());
			}
			
		};
		

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Position pos = new Position(x, y);
				SquareLabel sl = new SquareLabel(pos);
				sl.addMouseListener(ma);
				add(sl);
			}
		}
	}
	
	public void connect(Board board) {
		ch = new ClickHandler(board, this);
		for (int y=0; y<8; y++) {
			for (int x=0; x<8; x++) {
				Observer sl = (Observer)getComponent(x+8*y);
				board.addSquareObserver(sl, new Position(x,y));
			}
		}
	}
	
	public void markLegalMoves(List<Position> legalMoves) {
		unmarkAllSquares();
		for (Position p : legalMoves) {
			//System.out.println(p);
			((SquareLabel)getComponent(p.x+8*p.y)).setBackground(Color.BLUE);
		}
	}
	
	public void unmarkAllSquares() {
		for (int y=0; y<8; y++) {
			for (int x=0; x<8; x++) {
				SquareLabel sl = (SquareLabel)getComponent(x+8*y);
				sl.restoreBackground();
			}
		}
	}
	
}
