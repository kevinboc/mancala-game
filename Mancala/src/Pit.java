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
public class Pit extends JPanel{

	private static final int STONE_SIZE = 10;
	
	private String pitName;
	private boolean pitOwner;
	private int pitID;
	private int stoneAmt;
	
	public Pit(String pitName, boolean pitOwner, int pitID, int width, int height) {
		setLayout(new BorderLayout());
		stoneAmt = 10;
		
		JLabel pitLabel = new JLabel(pitName, SwingConstants.CENTER);
		if(pitOwner) {
			add(pitLabel, BorderLayout.SOUTH);
		}
		else {
			add(pitLabel, BorderLayout.NORTH);
		}
		
		drawPit(this, width, height);
	}

	//METHODS
	public static void drawPit(Pit pitFrame, int width, int height) {
		JPanel pitStorage = new JPanel() {		
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double pitBorder = new Ellipse2D.Double(0, 0, width, height);
				g2.draw(pitBorder);
			}
		};
		pitStorage.setLayout(new BorderLayout());
		pitStorage.setPreferredSize(new Dimension(width, height));
		pitStorage.setBorder(BorderFactory.createEmptyBorder(width / 4, height / 10, width / 4, height / 10));
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
		
		Pit pit = new Pit("1A", true, 0, 80, 100);
		frame.add(pit);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
