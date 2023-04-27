/**
 * Mancala Game Project
 * @author Kevin Boc, ...
 * @version 4/6/2023
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

/**
 * 
 */
public class Mancala extends JPanel{

	private static final int STONE_SIZE = 10;
	private static final int EMPTY_BORDER_SIZE = 10;
	private static final int MANCALA_HEIGHT = 232;
	private static final int MANCALA_WIDTH = 80;

	
	private String pitName;
	private boolean pitOwner;
	private int pitID;
	private int stoneAmt;
	
	public Mancala(boolean pitOwner, int pitID, int width, int height) {
		setLayout(new BorderLayout());
		stoneAmt = 10;
		
		drawPit(this, width, height);
	}

	//METHODS
	public static void drawPit(Mancala pitFrame, int width, int height) {
		JPanel pitStorage = new JPanel() {		
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawRoundRect(0, 0, width, height, 60, 60);
			}
		};
		pitStorage.setLayout(new BorderLayout());
		pitStorage.setPreferredSize(new Dimension(width, height));
		pitStorage.setBorder(BorderFactory.createEmptyBorder(EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE));
		pitFrame.add(pitStorage, BorderLayout.CENTER);
		
		JPanel stoneStorage = new JPanel();
		stoneStorage.setLayout(new FlowLayout());
		stoneStorage.setPreferredSize(new Dimension(width, height));
		
		for(int i = 0; i < pitFrame.getStoneAmt(); i ++) {
			drawStone(stoneStorage);
		}
		pitStorage.add(stoneStorage, BorderLayout.CENTER);
	}
	
	public static void drawStone(JPanel pitStorage) {
		Icon stoneIcon = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double stoneShape = new Ellipse2D.Double(0, 0, STONE_SIZE, STONE_SIZE);
				g2.setColor(Color.BLACK);
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
		pitStorage.add(stone);
	}
	
	//GETTERS
	public int getPitID() {
		return pitID;
	}
	
	public String getPitName() {
		return pitName;
	}
	
	public boolean getPitOwner() {
		return pitOwner;
	}
	
	public int getStoneAmt() {
		return stoneAmt;
	}

	//SETTERS
	public void setPitID(int pitID) {
		this.pitID = pitID; 
	}
	
	public void setPitName(String name) {
		pitName = name;
	}
	
	public void setWhoseTurn(boolean isPlayerA) {
		pitOwner = isPlayerA;
	}
	
	public void setStoneAmt(int amt) {
		stoneAmt = amt;
	}
	
	//TEST
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setLayout(new FlowLayout());
		
		Mancala pit = new Mancala(true, 0, MANCALA_WIDTH, MANCALA_HEIGHT);
		frame.add(pit);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
