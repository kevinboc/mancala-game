import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.border.Border;

public class TraditionalFormat implements BoardFormatter{

	final static int PIT_WIDTH = 80;
	final static int PIT_HEIGHT = 100; 
	
	final static int BOARD_WIDTH =  750;
	final static int BOARD_HEIGHT = 360;
	
	final static int MANCALA_WIDTH = 80;
	final static int MANCALA_HEIGHT= 232; 
	
	final static int STONE_SIZE = 10;
	
	
	//private Color color;
	
	@Override
	public Ellipse2D.Double formatPitBorder() {
		Ellipse2D.Double pitBorder = new Ellipse2D.Double(0, 0, PIT_WIDTH, PIT_HEIGHT);
		
		return pitBorder;
	}
	
	public Color formatPitColor() {
		return Color.LIGHT_GRAY;
	}
	
	
	@Override
	public JLabel formatPitLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	//need to create this method, not sure how to implement this

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
	//need to implement this method, however, its current implementation 
	//is a part of JPanel's setBorder() method rather than adding a rectangle

	@Override
	public Color formatBoardColor() {
		return Color.WHITE;
	}

	@Override
	public Color formatStoneColor() {
		return Color.BLACK;
	}

}
