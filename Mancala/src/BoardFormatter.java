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

public interface BoardFormatter {
	
	/** 
	 * To implement this interface, we can create a Traditional and Fun format as concrete strategies
	 * create an instance variable of BoardFormatter in the Pit, Mancala, and BoardPanel class
	 * initialize var depending on the style player chooses in the first screen and
	 * add the BoardFormatter var as a parameter in the methods mentioned below
	 */
	
	/////** Below are methods usable in Pit class **////
	
	/**
	 * Returns the formatted Pit Border.
	 * 
	 * @return the pit border ellipse
	 */
	public Ellipse2D.Double formatPitBorder();
	
	/**
	 * Returns the font for the formatted Pit Label.
	 * 
	 * @return the font for the pit label
	 */
	public Font formatPitLabel();
	
	/**
	 * Returns the font for the formatted Mancala Label.
	 * 
	 * @return the font for the Mancala label
	 */
	public Font formatMancalaLabel();
	
	
	/**
	 * Returns the formatted stone shape.
	 * 
	 * @return the stone shape
	 */
	public Ellipse2D.Double formatStoneShape();

	/////** for Mancala class**/////
	
	/**
	 * Returns the formatted Mancala Border.
	 * 
	 * @return the mancala border
	 */
	public RoundRectangle2D.Double formatMancalaBorder();
	
	/////** for BoardPanel class **/////
	
	/**
	 * Returns the formatted board border.
	 * 
	 * @return the board border
	 */
	public Rectangle2D.Double formatBoardBorder();
	
	/**
	 * Returns the formatted board color.
	 * 
	 * @return the board color
	 */
	public Color formatBoardColor();
	//to use in paintComponent()
	
	/**
	 * Returns the formatted stone color.
	 * 
	 * @return the stone color
	 */
	public Color formatStoneColor();

	/**
	 * Returns the formatted pit color,
	 * 
	 * @return the pit color
	 */
	public Color formatPitColor();
	
	
}
