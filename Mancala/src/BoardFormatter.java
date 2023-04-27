import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

public interface BoardFormatter {
	
	//to implement this interface, we can create a Traditional and Fun format as concrete strategies
	//create an instance variable of BoardFormatter in the Pit, Mancala, and BoardPanel class
	//initialize var depending on the style player chooses in the first screen and
	//add the BoardFormatter var as a parameter in the methods mentioned below
	
	/////** Below are methods usable in Pit class **////
	
	public Ellipse2D.Double formatPitBorder();
	//to use in the drawPit() method
	
	public JLabel formatPitLabel();
	//to use in pit constructor
	
	public Ellipse2D.Double formatStoneShape();
	//to use in the drawStone() method of pit & mancala
		
	
	/////** for Mancala class**/////
	
	public Rectangle2D.Double formatMancalaBoard();
	//in drawPit() of mancala class
	
	
	/////** for BoardPanel class **/////
	
	public  Rectangle2D.Double formatBoardBorder();
	//to use in the paintComponent()
	
	public Color formatBoardColor();
	//to use in paintComponent()
	

	
	
}
