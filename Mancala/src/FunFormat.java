/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tanlimco, and Brianna Gomez
 * @version 5/5/2023
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The fun format of the mancala board.
 */
public class FunFormat implements BoardFormatter {
	
	//Constants
	final static int PIT_WIDTH = 80;
	final static int PIT_HEIGHT = 100; 
	
	final static int BOARD_WIDTH = 750;
	final static int BOARD_HEIGHT = 380;
	
	final static int MANCALA_WIDTH = 80;
	final static int MANCALA_HEIGHT = 232; 
	
	final static int STONE_SIZE = 10;
	
	@Override
	public Ellipse2D.Double formatPitBorder() {
		Ellipse2D.Double pitBorder = new Ellipse2D.Double(0, 0, PIT_WIDTH, PIT_HEIGHT);
		
		return pitBorder;
	}
	
	public Color formatPitColor() {
		return new Color(114, 218, 255);
	}
	
	public Font formatMancalaLabel() {
		return new Font("Impact", Font.PLAIN, 20);
	}

	@Override
	public Font formatPitLabel() {
		return new Font("Impact", Font.PLAIN, 15);
	}
	

	@Override
	public Ellipse2D.Double  formatStoneShape() {
		Ellipse2D.Double stone = new Ellipse2D.Double(0, 0, STONE_SIZE, STONE_SIZE);
		
		return stone;
	}

	@Override
	public RoundRectangle2D.Double formatMancalaBorder() {
		RoundRectangle2D.Double mancalaBorder = new RoundRectangle2D.Double(0,0, MANCALA_WIDTH, MANCALA_HEIGHT, 60, 60);
		return mancalaBorder;
	}
	

	@Override
	public Rectangle2D.Double formatBoardBorder() {
		Rectangle2D.Double boardBorder = new Rectangle2D.Double(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		return boardBorder;
	}

	@Override
	public Color formatBoardColor() {
		return new Color(239, 255, 131); 
	}
	public Color formatStoneColor() {
		Random random = new Random();
		int R = (int) Math.round(Math.random() * 255);
		int G = (int) Math.round(Math.random() * 255);
		int B = (int) Math.round(Math.random() * 255);
		return new Color(R, G, B);

	}
}
