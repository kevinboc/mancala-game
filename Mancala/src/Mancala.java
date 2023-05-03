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
	
	public Mancala(boolean pitOwner, int pitID, int width, int height, BoardFormatter bf) {
		setLayout(new BorderLayout());
		stoneAmt = 10;
		this.pitOwner = pitOwner;
		this.pitID = pitID;
		//drawPit(this, width, height);
		drawPit(width, height, bf);
	}
	
	private JPanel pitStorage;

	//METHODS
	//public static void drawPit(Mancala pitFrame, int width, int height) {
	public void drawPit(int width, int height, BoardFormatter bf) {
		JPanel pitStorage = new JPanel() {		
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				RoundRectangle2D.Double mancalaPit = bf.formatMancalaBorder();
				g2.setColor(bf.formatPitColor());
				g2.draw(mancalaPit);
				g2.fill(mancalaPit);
				//g2.setBackground(bf.formatPitColor());
				//g2.drawRoundRect(0, 0, width, height, 60, 60);
			}
		};
		pitStorage.setLayout(new BorderLayout());
		pitStorage.setPreferredSize(new Dimension(width, height));
		pitStorage.setBorder(BorderFactory.createEmptyBorder(EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE, EMPTY_BORDER_SIZE));
		//pitFrame.add(pitStorage, BorderLayout.CENTER);
		add(pitStorage, BorderLayout.CENTER);
		
		//JPanel stoneStorage = new JPanel();
		stoneStorage.setLayout(new FlowLayout());
		stoneStorage.setPreferredSize(new Dimension(width, height));
		
		//for(int i = 0; i < pitFrame.getStoneAmt(); i ++) {
		//for(int i = 0; i < getStoneAmt(); i ++) {
		//	drawStone(stoneStorage);
		//}
		pitStorage.add(stoneStorage, BorderLayout.CENTER);
	}
	
	private JPanel stoneStorage = new JPanel();
	
	public void drawStones(BoardFormatter f) {
		stoneStorage.removeAll(); // added a call to remove all the stones before setting it again
		for (int i = 0; i < getStoneAmt(); i++) {
			drawStone(f);

		}
	}
	
	//public static void drawStone(JPanel pitStorage) {
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
		//pitStorage.add(stone);
		stoneStorage.add(stone);
		
		stoneStorage.validate();
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
	/*
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setLayout(new FlowLayout());
		
		Mancala pit = new Mancala(true, 0, MANCALA_WIDTH, MANCALA_HEIGHT);
		frame.add(pit);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	*/
}
