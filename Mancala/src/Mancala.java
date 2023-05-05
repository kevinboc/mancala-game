/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tanlimco, and Brianna Gomez
 * @version 5/5/2023
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

/**
 * Mancala class responsible for drawing the mancala borders and drawing the stones within.
 */
public class Mancala extends JPanel{

	//Constants
	private static final int STONE_SIZE = 10;
	private static final int EMPTY_BORDER_SIZE = 10;
	
	//Private Instance Variables
	private String mancalaName;
	private boolean mancalaOwner;
	private int mancalaID;
	private int stoneAmt;
	private JPanel stoneStorage = new JPanel();
	
	/**
	 * Creates the Mancala object and its properties.
	 * 
	 * @param pitOwner true if it belongs to player A; otherwise, false
	 * @param pitID the mancala id
	 * @param width the width
	 * @param height the height
	 * @param bf the board formatter
	 */
	public Mancala(boolean pitOwner, int pitID, int width, int height, BoardFormatter bf) {
		setLayout(new BorderLayout());
		stoneAmt = 10;
		this.mancalaOwner = pitOwner;
		this.mancalaID = pitID;
		drawPit(width, height, bf);
	}
	
	// ****** PIT METHODS ******
	
	/**
	 * Draws the mancala pit
	 * 
	 * @param width the width
	 * @param height the height
	 * @param bf the board formatter
	 */
	public void drawPit(int width, int height, BoardFormatter bf) {
		JPanel pitStorage = new JPanel() {		
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				RoundRectangle2D.Double mancalaPit = bf.formatMancalaBorder();
				g2.setColor(bf.formatPitColor());
				g2.draw(mancalaPit);
				g2.fill(mancalaPit);
			}
		};
		pitStorage.setLayout(new BorderLayout());
		pitStorage.setPreferredSize(new Dimension(width, height));
		pitStorage.setBorder(BorderFactory.createEmptyBorder(EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE));
		add(pitStorage, BorderLayout.CENTER);
		
		stoneStorage.setLayout(new FlowLayout());
		stoneStorage.setPreferredSize(new Dimension(width, height));
		stoneStorage.setBackground(bf.formatPitColor()); //used bf
		
		pitStorage.add(stoneStorage, BorderLayout.CENTER);
	}
	
	/**
	 * Draws the stones
	 * 
	 * @param f the board formatter
	 */
	public void drawStones(BoardFormatter f) {
		stoneStorage.removeAll(); // added a call to remove all the stones before setting it again
		for (int i = 0; i < getStoneAmt(); i++) {
			drawStone(f);

		}
	}
	
	/**
	 * Helper method to draw stones.
	 * 
	 * @param bf the board formatter
	 */
	public void drawStone(BoardFormatter bf) {
		Icon stoneIcon = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double stoneShape = bf.formatStoneShape();//new Ellipse2D.Double(0, 0, STONE_SIZE, STONE_SIZE);
				
				g2.setColor(bf.formatStoneColor());
				g2.draw(stoneShape);
				g2.fill(stoneShape);
			}

			@Override
			public int getIconWidth() {
				return STONE_SIZE;
			}

			@Override
			public int getIconHeight() {
				return STONE_SIZE;
			}
			
		};
		
		JLabel stone = new JLabel(stoneIcon);
		stoneStorage.add(stone);
		stoneStorage.validate();
	}
	
	// ****** GETTER METHODS ******
	
	/**
	 * Returns the Mancala Pit ID.
	 * 
	 * @return the mancala id
	 */
	public int getPitID() {
		return mancalaID;
	}
	
	/**
	 * Returns the Mancala Pit Name.
	 * 
	 * @return the mancala pit name
	 */
	public String getPitName() {
		return mancalaName;
	}
	
	/**
	 * Returns true if the mancala pit belongs to player A; otherwise, false.
	 * 
	 * @return true if the mancala pit belongs to player A; otherwise, false
	 */
	public boolean getPitOwner() {
		return mancalaOwner;
	}
	
	/**
	 * Returns the stone amount. 
	 * 
	 * @return
	 */
	public int getStoneAmt() {
		return stoneAmt;
	}

	// ****** SETTER METHODS ******
	
	/**
	 * Sets the Mancala Pit ID.
	 * 
	 * @param pitID the mancala pit id
	 */
	public void setPitID(int pitID) {
		this.mancalaID = pitID; 
	}
	
	/**
	 * Sets the Mancala Pit Name.
	 * 
	 * @param name the mancala pit name
	 */
	public void setPitName(String name) {
		mancalaName = name;
	}
	
	/**
	 * Sets whether it's player A's player B's turn.
	 * 
	 * @param isPlayerA true if it is player A; otherwise, false
	 */
	public void setWhoseTurn(boolean isPlayerA) {
		mancalaOwner = isPlayerA;
	}
	
	/**
	 * Sets the stone amount.
	 * 
	 * @param amt the stone amount
	 */
	public void setStoneAmt(int amt) {
		stoneAmt = amt;
	}
}
