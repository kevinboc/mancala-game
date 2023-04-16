/**
 * Mancala Game Project
 * @author Kevin Boc, ...
 * @version 4/6/2023
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.geom.*;

/**
 * 
 */
public class Pit extends JPanel{
	
	//Constant Variables
	private final int PIT_BORDER_HEIGHT = 70;	
	private final int PIT_BORDER_WIDTH = 60;
	private final int STONE_SIZE = 5;
	
	//Private Instance Variables
	private int pitID;
	private int stoneAmt;
	private boolean pitOwner; //True = Player A, False = Player B
	private String pitName;
	
	/**
	 * 
	 * @param pitName the pit name
	 * @param pitOwner true if pit belongs to Player A; otherwise, 
	 * @param pitID
	 */
	public Pit(String pitName, boolean pitOwner, int pitID) {
		this.pitName = pitName;
		this.pitOwner = pitOwner;
		this.pitID = pitID;		
		stoneAmt = 7;
		
		setLayout(new BorderLayout());
	
		//Pit Name
		if(pitOwner) {
			add(new JLabel(pitName, JLabel.CENTER), BorderLayout.SOUTH);
		}
		else {
			add(new JLabel(pitName, JLabel.CENTER), BorderLayout.NORTH);
		}
		
		//Pit Storage
		JPanel pitStorage = new JPanel() {
			@Override
			public void setBorder(Border border) {
				super.setBorder(new EmptyBorder(15, 0, 0, 0));
			}
		
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double pitBorder = new Ellipse2D.Double(0, 0, PIT_BORDER_WIDTH, PIT_BORDER_HEIGHT);
				g2.draw(pitBorder);
			}
			
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(PIT_BORDER_WIDTH, PIT_BORDER_HEIGHT);
			}
		};
		
		JPanel stoneArea = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(50, 35);
			}
		};
		stoneArea.setLayout(new FlowLayout(FlowLayout.CENTER));
		for(int i = 0; i < stoneAmt; i++) {
			addStones(stoneArea);
		}
		
		pitStorage.add(stoneArea, BorderLayout.CENTER);
		
		add(pitStorage, BorderLayout.CENTER);
	}
	
	//METHODS
	public void addStones(JPanel stoneArea) {
		Icon stoneIcon = new Icon() {
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				Ellipse2D.Double stone = new Ellipse2D.Double(x, y, STONE_SIZE, STONE_SIZE);
				g2.setColor(Color.BLACK);
				g2.fill(stone);
			}

			@Override
			public int getIconWidth() {
				// TODO Auto-generated method stub
				return STONE_SIZE;
			}

			@Override
			public int getIconHeight() {
				// TODO Auto-generated method stub
				return STONE_SIZE;
			}
			
		};
		
		JLabel stone = new JLabel(stoneIcon);
		stoneArea.add(stone);
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
		
		Pit pit = new Pit("1A", true, 0);
		frame.add(pit);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
