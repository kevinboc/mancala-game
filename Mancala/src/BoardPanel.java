/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tamlinco, and Brianna Gomez
 * @version 5/5/2023
 */
//File Javadoc that contains the BoardPanel class
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.event.*;

/**
 * Sets up the board and allows for player interaction
 * @author 
 * @version 1.0 5/5/23
 */
public class BoardPanel extends JPanel {

	//Private Instance Variables
	private Pit[] pitArrA = new Pit[6];
	private Pit[] pitArrB = new Pit[6];

	private Mancala mancalaA;// = new Mancala(true, 6, 80, 232);
	private Mancala mancalaB;// = new Mancala(false, 13, 80, 232);

	private BoardFormatter bf;
	private StoneDataModel model;

	private int undoNumA;
	private int undoNumB;

	private boolean prevPlayerTurn;
	private boolean playerTurn;
	private boolean hasFreeTurn;
	private boolean hasMadeUndo; 
	
	private boolean endCondition;

	private int[] prevStoneAmts = new int[14];

	private ChangeListener c;
	
	private JLabel winMessage;

	/**
	 * Constructor for BoardPanel class that sets up the game and board organization.
	 * 
	 * @param bf the board formatter
	 */
	public BoardPanel(BoardFormatter bf) {
		playerTurn = true;
		prevPlayerTurn = true;
		hasFreeTurn = false;
		hasMadeUndo = false; 
		this.bf = bf;
		
		mancalaA = new Mancala(true, 6, 80, 232, bf);
		mancalaB =new Mancala(false, 13, 80, 232, bf);

		model = new StoneDataModel();

		c = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				winMessage.setText("");

				for (int i = 0; i < pitArrA.length; i++) {
					pitArrA[i].setStoneAmt(model.getStoneAmts()[i]);
					pitArrA[i].drawStones(bf); //passed the formatter
					pitArrA[i].repaint();
				}

				mancalaA.setStoneAmt(model.getStoneAmts()[6]);
				mancalaA.drawStones(bf); //added formatter
				mancalaA.repaint();

				for (int i = 0; i < pitArrB.length; i++) {
					pitArrB[i].setStoneAmt(model.getStoneAmts()[i + 7]);
					pitArrB[i].drawStones(bf);  
					pitArrB[i].repaint(); 
				}

				mancalaB.setStoneAmt(model.getStoneAmts()[13]);
				mancalaB.drawStones(bf);
				mancalaB.repaint();

				if ((model.getIndex() == 13 && prevPlayerTurn == false) || (model.getIndex() == 6 && prevPlayerTurn == true)) {
					//label stating you get a free turn pops up
					//winMessage.setText("Free turn!");
					//set player turn back to original player
					setPlayerTurn(prevPlayerTurn);
					hasFreeTurn = true;
				}
				
				if (endCondition == true) {
					//jlabel shows who won by comparing the values in mancala
					String lastMessage = "";
					//player a wins
					if (model.getStoneAmts()[6] > model.getStoneAmts()[13]) {
						lastMessage = "Game Over. Player A has won.";
					}
					//player b wins
					else if (model.getStoneAmts()[6] < model.getStoneAmts()[13]) {
						lastMessage = "Game Over. Player B has won.";
					}
					//tie
					else {
						lastMessage = "Game Over. Game ends in a tie.";
					}
					winMessage.setText(lastMessage);
				}
			}
		};

		model.attach(c);

		setLayout(new BorderLayout());
		setUpBoardPanel();
		setOpaque(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D r = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
		g2.setColor(Color.pink);
		g2.fill(r);
	}
	
	/**
	 * Sets the style for the board.
	 * 
	 * @param bf - the style of the board
	 */
	public void setStyle(BoardFormatter bf) {
		this.bf = bf;
	}

	//********** CENTER BOARD METHODS **************

	private JPanel centerPanel;
	private JPanel pitsPanel;
	private JPanel mancalaPanelA;
	private JPanel mancalaPanelB;

	/**
	 * Sets up the organization of the game board.
	 */
	public void setUpCenterPanel() {
		FlowLayout centerFlow = new FlowLayout();
		centerFlow.setHgap(0);
		centerPanel = new JPanel(centerFlow);
		centerPanel.setBackground(bf.formatBoardColor()); //used bf
		pitsPanel = new JPanel();
		mancalaPanelA = new JPanel();
		mancalaPanelB = new JPanel();

		populateBoard();

		centerPanel.add(mancalaPanelB);
		centerPanel.add(pitsPanel);
		centerPanel.add(mancalaPanelA);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates a set of pits (6 pits).
	 * 
	 * @param player - String of either A or B, the player that owns the pits
	 * @param owner  - true or false, the player that owns the pits
	 */
	public void generatePits(String player, boolean owner) {
		for (int i = 0; i <= 5; i++) {
			Pit pit;
			if (owner == true) {
				pit = new Pit(player + (i + 1), owner, i, 80, 100, bf);
			} else {
				pit = new Pit(player + (i + 1), owner, i + 7, 80, 100, bf);
			}
			pit.setSize(40, 75);
			pit.setBackground(bf.formatBoardColor());//used bf
			pit.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					//only does something if the current player owns the pit
					if ((pit.getPitOwner() == playerTurn) && endCondition == false) {
						hasMadeUndo = false;
						hasFreeTurn = false;
						if (playerTurn == true) {
							//player a's turn
							undoNumB = 0;
						} else {
							undoNumA = 0;
						}
						
						for (int i = 0; i < prevStoneAmts.length; i++) {
							prevStoneAmts[i] = model.getStoneAmts()[i];
						}
						setPrevPlayerTurn(playerTurn);
						setPlayerTurn(!playerTurn);
						
						//mutator method to distribute the stones, accounting for special cases
						model.distributeStones(pit.getPitID(), prevPlayerTurn);

						checkEndCondition();
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
			});

			if (owner == true) {
				pitArrA[i] = pit;
			} else {
				pitArrB[i] = pit;
			}
		}
	}

	/**
	 * Organizes the pits and mancala on the board.
	 */
	public void populateBoard() {

		generatePits("A", true);
		generatePits("B", false);

		JPanel aPits = new JPanel();
		JPanel bPits = new JPanel();
		aPits.setBackground(bf.formatBoardColor());//used bf
		bPits.setBackground(bf.formatBoardColor());//used bf
		BoxLayout pitsLayoutA = new BoxLayout(aPits, BoxLayout.X_AXIS);
		BoxLayout pitsLayoutB = new BoxLayout(bPits, BoxLayout.X_AXIS);

		for (int i = 0; i < 6; i++) {
			aPits.add(pitArrA[i]);
		}

		for (int i = 5; i >= 0; i--) {
			bPits.add(pitArrB[i]);
		}

		BoxLayout pitsPanelL = new BoxLayout(pitsPanel, BoxLayout.Y_AXIS);

		pitsPanel.setLayout(pitsPanelL);
		pitsPanel.add(bPits);
		pitsPanel.add(aPits);
		
		mancalaPanelA.setBackground(bf.formatBoardColor());//used bf
		mancalaPanelB.setBackground(bf.formatBoardColor());//used bf
		mancalaA.setBackground(bf.formatBoardColor());//used bf
		mancalaB.setBackground(bf.formatBoardColor());//used bf
		
		mancalaPanelA.add(mancalaA);
		mancalaPanelB.add(mancalaB);
	}

	// ****** BOTTOM PANEL METHODS ******

	private JPanel bottomPanel;
	private JButton undoButton;
	private JTextField startingStoneInput;

	/**
	 * Holds the undo button and textbox for number of starting stones.
	 */
	public void setUpBottomPanel() {

		bottomPanel = new JPanel();
		undoButton = new JButton("UNDO");
		startingStoneInput = new JTextField("Initial Stone Amt");

		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hasMadeUndo == false) {
					hasMadeUndo = true;
					setPlayerTurn(prevPlayerTurn);
					if (playerTurn == true) {
						//player a's turn
						if (undoNumA != 3) {
							//resets the model to the prev array using mutator method
							model.resetStones(prevStoneAmts);
							//then increments amt of undos
							undoNumA++;
						} else {
							winMessage.setText("No more undos");
							if (playerTurn == true) {
								setPlayerTurn(!playerTurn);
								if(hasFreeTurn) {
									winMessage.setText("No more undos, but you have a free turn from your last move, so go again.");//
									setPlayerTurn(!playerTurn);
								}
							}
							//so, the game doesn't revert the passing and gives the turn back to the other player
						}
					} else {
						//player b's turn
						if (undoNumB != 3) {
							//resets the model to the prev array using mutator method
							model.resetStones(prevStoneAmts);
							//then increments amt of undos
							undoNumB++;
						} else {
							winMessage.setText("No more undos");
							if (playerTurn == false) {
								setPlayerTurn(!playerTurn);
								if(hasFreeTurn) {
									winMessage.setText("No more undos, but you have a free turn from your last move, so go again.");//
									setPlayerTurn(!playerTurn);
								}
							}
							//so, the game doesn't revert the passing and gives the turn back to other player
						}
					}
				}
				
			}
		});

		startingStoneInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(startingStoneInput.getText()) != 3
						&& Integer.parseInt(startingStoneInput.getText()) != 4) {
					winMessage.setText("Invalid number of stones. Please type in either 3 or 4.");
				} else {
					//calls mutator method for model that will set all the stones
					model.setStones(Integer.parseInt(startingStoneInput.getText()));
				}
			}
		});

		bottomPanel = new JPanel();
		bottomPanel.add(undoButton);
		bottomPanel.add(startingStoneInput);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	//******* SET UP WHOLE BOARDPANEL METHOD *******

	//Private Instance Variables
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	
	private Icon aIcon = new Icon() {

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub

			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
			String label = new String("MANCALA");
			for(int i = 0; i < label.length();i++) {
				String character = "" + label.charAt(i);
				g2.drawString(character, 5, 5 + (25 * (i+1)));
				System.out.println("y is: " + 5 + (25 * (i+1)));
			}
			g2.drawString("A", 5, 230);
			}

		@Override
		public int getIconWidth() {
		// TODO Auto-generated method stub
			return 50;
		}

		@Override
		public int getIconHeight() {
		// TODO Auto-generated method stub
			return 250;
		}
	};

	//Private Instance Variable
	private Icon bIcon = new Icon() {

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
			String label = new String("MANCALA");
			for(int i = 0; i < label.length();i++) {
				String character = "" + label.charAt(i);
				g2.drawString(character, 30, 5 + (25 * (i+1)));
				System.out.println("y is: " + 5 + (25 * (i+1)));
			}
			g2.drawString("B", 30, 230);
		}

		@Override
		public int getIconWidth() {
		// TODO Auto-generated method stub
			return 50;
		}

		@Override
		public int getIconHeight() {
		// TODO Auto-generated method stub
			return 250;
		}
	};

		//private JLabel mancalaAL = new JLabel("Mancala A");

		private JLabel mancalaAL = new JLabel(aIcon);
		private JLabel mancalaBL = new JLabel(bIcon);

	
	/**
	 * Method to pit all the panels (center, bottom, etc) in their proper places.
	 */
	public void setUpBoardPanel() {

		setUpBottomPanel();
		setUpCenterPanel();
		rightPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel.add(mancalaAL);
		leftPanel.add(mancalaBL);
		add(rightPanel, BorderLayout.EAST);
		add(leftPanel, BorderLayout.WEST);

		winMessage = new JLabel("  ");

		topPanel = new JPanel();
		topPanel.add(winMessage);

		add(topPanel, BorderLayout.NORTH);

	}

	//****** SETTERS ******

	/**
	 * Sets the player turn to given value.
	 * 
	 * @param turn - new boolean value for whose turn it is
	 */
	public void setPlayerTurn(boolean turn) {
		playerTurn = turn;
	}
	
	/**
	 * Sets the previous turn's player to given value.
	 * 
	 * @param prevTurn - new boolean value for the player who went on the prev turn
	 */
	public void setPrevPlayerTurn(boolean prevTurn) {
		prevPlayerTurn = prevTurn;
	}

//******* CHECK END CONDITION METHOD *********

	/**
	 * Checks if the game is over.
	 */
	public void checkEndCondition() {

		int stonesLeftA = 0;
		int stonesLeftB = 0;

		//check player a's pits
		for (int i = 0; i < 6; i++) {
			stonesLeftA += model.getStoneAmts()[i];
		}

		//check player b's pits
		for (int i = 7; i < 13; i++) {
			stonesLeftB += model.getStoneAmts()[i];
		}

		//if either have 0 stones left, then set the condition to true
		if (stonesLeftA == 0 || stonesLeftB == 0) {
			endCondition = true;
			if (stonesLeftA == 0) {
				model.captureAll(false);
			} else {
				model.captureAll(true);
			}
		}
	}
}
