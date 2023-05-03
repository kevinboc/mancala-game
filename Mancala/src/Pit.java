
/**
 * Mancala Game Project
 * @author Kevin Boc, ...
 * @version 4/6/2023
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

/**
 * *
 */
public class Pit extends JPanel {

	private static final int STONE_SIZE = 10;
	private String pitName;
	private boolean pitOwner;
	private int pitID;
	private int stoneAmt;


	public Pit(String pitName, boolean pitOwner, int pitID, int width, int height, BoardFormatter bf) {
		setLayout(new BorderLayout());
		//added pitID initialization and pitOwner initialization
		this.pitOwner = pitOwner;
		this.pitID = pitID;
		stoneAmt = 10;
		JLabel pitLabel = new JLabel(pitName, SwingConstants.CENTER);
		if (pitOwner) {
			add(pitLabel, BorderLayout.SOUTH);
		} else {
			add(pitLabel, BorderLayout.NORTH);
		}
		drawPit(width, height, bf);
	}

	// METHODS
	//removing the pitFrame parameter and static
	//public static void drawPit(Pit pitFrame, int width, int height) {
	public void drawPit(int width, int height, BoardFormatter bf) {
		JPanel pitStorage = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double pitBorder = bf.formatPitBorder(); //new Ellipse2D.Double(0, 0, width, height);
				g2.setColor(bf.formatPitColor());
				g2.draw(pitBorder);
				g2.fill(pitBorder);
			}
		};
		pitStorage.setLayout(new BorderLayout());
		pitStorage.setPreferredSize(new Dimension(width, height));
		pitStorage.setBorder(BorderFactory.createEmptyBorder(width / 4, height / 10, width / 4, height / 10));
		this.add(pitStorage, BorderLayout.CENTER);

		//JPanel stoneStorage = new JPanel(); //making this into a global variable
		stoneStorage.setLayout(new FlowLayout());
		stoneStorage.setPreferredSize(new Dimension(width, height));

		/*
		 * for(int i = 0; i < pitFrame.getStoneAmt();i ++) { drawStone(stoneStorage); }
		 */ // moving this to its own method
		pitStorage.add(stoneStorage, BorderLayout.CENTER);
	}

	//made stoneStorage a global variable and moved the drawing of stones to its own method
	private JPanel stoneStorage = new JPanel();

	public void drawStones(BoardFormatter bf) {
		stoneStorage.removeAll(); // added a call to remove all the stones before setting it again
		for (int i = 0; i < getStoneAmt(); i++) {
			drawStone(bf);

		}
	}

	public void drawStone(BoardFormatter bf) {
		Icon stoneIcon = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				
				Ellipse2D.Double stoneShape = bf.formatStoneShape();	 //new Ellipse2D.Double(0, 0, STONE_SIZE, STONE_SIZE);
				g2.setColor(bf.formatStoneColor()); 	//Color.BLACK);
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

		stoneStorage.revalidate(); // added revalidate

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
	 * public static void main(String[] args) { JFrame frame = new JFrame();
	 * frame.setLayout(new FlowLayout());
	 * 
	 * Pit pit = new Pit("1A", true, 0, 80, 100); frame.add(pit);
	 * 
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.pack();
	 * frame.setVisible(true); }
	 */
}