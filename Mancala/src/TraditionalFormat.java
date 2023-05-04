/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tamlinco, and Brianna Gomez
 * @version 5/5/2023
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;

/**
 * The traditional format of the mancala board.
 */
public class TraditionalFormat implements BoardFormatter{

	
	//Constants
	final static int PIT_WIDTH = 80;
	final static int PIT_HEIGHT = 100; 
	
	final static int BOARD_WIDTH =  750;
	final static int BOARD_HEIGHT = 360;
	
	final static int MANCALA_WIDTH = 80;
	final static int MANCALA_HEIGHT= 232; 
	
	final static int STONE_SIZE = 10;
	

	@Override
	public Ellipse2D.Double formatPitBorder() {
		Ellipse2D.Double pitBorder = new Ellipse2D.Double(0, 0, PIT_WIDTH, PIT_HEIGHT);
		
		return pitBorder;
	}
	
	public Color formatPitColor() {
		return new Color(215, 163, 94);
	}
	
	
	public Font formatMancalaLabel() {
		return new Font("Trebuchet MS", Font.BOLD, 20);

	}
	@Override
	public Font formatPitLabel() {
		
		return new Font("Trebuchet MS", Font.BOLD, 15);
	}

	@Override
	public Ellipse2D.Double formatStoneShape() {
		Ellipse2D.Double stone = new Ellipse2D.Double(0, 0, STONE_SIZE, STONE_SIZE);
		return stone;
	}

	@Override
	public RoundRectangle2D.Double formatMancalaBorder() {
		RoundRectangle2D.Double mancalaPit = new RoundRectangle2D.Double(0, 0, MANCALA_WIDTH, MANCALA_HEIGHT, 60, 60);
		return mancalaPit;
	}

	@Override
	public Rectangle2D.Double formatBoardBorder() {
		Rectangle2D.Double boardBorder = new Rectangle2D.Double(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		return boardBorder;
	}

	@Override
	public Color formatBoardColor() {
		return new Color(245, 231, 200); 
	}

	@Override
	public Color formatStoneColor() {
		return Color.WHITE;
	}

}
