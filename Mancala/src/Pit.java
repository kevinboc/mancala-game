/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tanlimco, and Brianna Gomez
 * @version 5/5/2023
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

/**
 * Pit class responsible for drawing the pit borders and drawing the stones within. Each pit is labeled.
 */
public class Pit extends JPanel {

	//Constants
	private static final int STONE_SIZE = 10;
	
	//Private Instance Variable
	private String pitName;
	private boolean pitOwner;
	private int pitID;
	private int stoneAmt;
	private JPanel stoneStorage = new JPanel();

	/**
	 * Creates a Pit object with a label, pit, and stones.
	 * 
	 * @param pitName the pit name
	 * @param pitOwner true if pit owner is player a; otherwise, false
	 * @param pitID the pit id
	 * @param width pit width
	 * @param height pit height
	 * @param bf the board formatter
	 */
	public Pit(String pitName, boolean pitOwner, int pitID, int width, int height, BoardFormatter bf) {
		setLayout(new BorderLayout());
		this.pitOwner = pitOwner;
		this.pitID = pitID;
		JLabel pitLabel = new JLabel(pitName, SwingConstants.CENTER);
		pitLabel.setFont(bf.formatPitLabel());
		if (pitOwner) {
			add(pitLabel, BorderLayout.SOUTH);
		} else {
			add(pitLabel, BorderLayout.NORTH);
		}
		drawPit(width, height, bf);
	}

	// ****** PIT METHODS ******
	
	/**
	 * Draws the pit.
	 * 
	 * @param width the pit width
	 * @param height the pit height
	 * @param bf the boad formatter
	 */
	public void drawPit(int width, int height, BoardFormatter bf) {
		JPanel pitStorage = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double pitBorder = bf.formatPitBorder(); 
				g2.setColor(bf.formatPitColor());
				g2.draw(pitBorder);
				g2.fill(pitBorder);
			}
		};
		pitStorage.setLayout(new BorderLayout());
		pitStorage.setPreferredSize(new Dimension(width, height));
		pitStorage.setBorder(BorderFactory.createEmptyBorder(width / 4, height / 10, width / 4, height / 10));
		this.add(pitStorage, BorderLayout.CENTER);

		stoneStorage.setLayout(new FlowLayout());
		stoneStorage.setPreferredSize(new Dimension(width, height));
		stoneStorage.setBackground(bf.formatPitColor());
		pitStorage.add(stoneStorage, BorderLayout.CENTER);
	}
	
	/**
	 * Draws the stones.
	 * 
	 * @param bf the board formatter
	 */
	public void drawStones(BoardFormatter bf) {
		stoneStorage.removeAll(); // added a call to remove all the stones before setting it again
		for (int i = 0; i < getStoneAmt(); i++) {
			drawStone(bf);

		}
	}

	/**
	 * Helper method to draw the stone.
	 * 
	 * @param bf the board formatter
	 */
	public void drawStone(BoardFormatter bf) {
		Icon stoneIcon = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double stoneShape = bf.formatStoneShape();	
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

		stoneStorage.revalidate();
	}

	// ****** GETTER METHODS ******
	
	/**
	 * Returns the pit id.
	 * 
	 * @return the pit id
	 */
	public int getPitID() {
		return pitID;
	}

	/**
	 * Returns the pit name.
	 * 
	 * @return the pit name
	 */
	public String getPitName() {
		return pitName;
	}

	/**
	 * Returns true if the pit owner is player A; otherwise, false.
	 * 
	 * @return true if the pit owner is player A; otherwise, false
	 */
	public boolean getPitOwner() {
		return pitOwner;
	}

	/**
	 * Returns the stone amount.
	 * 
	 * @return the stone amount
	 */
	public int getStoneAmt() {
		return stoneAmt;
	}

	// ****** SETTER METHODS ******
	
	/**
	 * Sets the pit id.
	 * 
	 * @param pitID the pit id
	 */
	public void setPitID(int pitID) {
		this.pitID = pitID;
	}

	/**
	 * Sets the pit name. 
	 * 
	 * @param name the pit name
	 */
	public void setPitName(String name) {
		pitName = name;
	}

	/**
	 * Sets the players turn.
	 * 
	 * @param isPlayerA true if it is player A; otherwise, false
	 */
	public void setWhoseTurn(boolean isPlayerA) {
		pitOwner = isPlayerA;
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