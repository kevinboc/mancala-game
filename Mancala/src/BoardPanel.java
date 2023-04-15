import java.awt.*;

import java.awt.geom.*;

import java.awt.geom.Rectangle2D.Double;



import javax.swing.*;

import javax.swing.event.*;

import java.util.*;

import java.awt.event.*;



public class BoardPanel extends JPanel{



	private ArrayList<Pit> pitArr = new ArrayList<Pit>();

//private Pit[] pitArrA = new Pit[7];

//private Pit[] pitArrB = new Pit[7];

//private Pit[] pitArr = new Pit[14];



//private BoardManager bm;

	private StoneDataModel model;



	private int undoNumA;

	private int undoNumB;


//maybe two undoNum vars

//when player a clicks on the pit, it should set player b's undoNum to 0, while keeping player a's undo the same number

//that way, if player a undos, it will still keep track

//but if player a doesn't undo, and player b does their turn, it will set player A's undoNum to 0

//that way player a's undo get refreshed for the next turn



	private boolean playerTurn;



	private int[] prevStoneAmts = new int[14];


	private ChangeListener c;


//public void setStyle(BoardManager bm) {

// this.bm = bm;

//}


//public void setPreferredSize()


	private JPanel topPanel;


	private JPanel rightPanel;

	private JPanel leftPanel;



	public BoardPanel() {


		playerTurn = true;


		model = new StoneDataModel();


		c = new ChangeListener() {
			


			@Override

			public void stateChanged(ChangeEvent e) {


/*

int[] currentStoneAmts = new int[14];


for(int i = 0; i < model.getStoneAmts().length;i++) {


currentStoneAmts[i] = model.getStoneAmts()[i];


}

*/


				for(int i = 0; i < pitArr.size();i++) {


					pitArr.get(i).setStoneAmt(model.getStoneAmts()[i]);

					//then each pit redraws the stones based on their new stoneAmt

					//not sure if its repaint or some other method, depends on how partner implements it

					pitArr.get(i).repaint();


				}


			}



		};


		model.attach(c);






		setLayout(new BorderLayout());



		setUpBoardPanel();


		setOpaque(true);

	}



	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		Rectangle2D r = new Rectangle2D.Double(0,0,getWidth(),getHeight());


		g2.setColor(Color.pink);

		g2.fill(r);

		System.out.println("here: " + getWidth());

}




	private JPanel centerPanel;

	private JPanel pitsPanel;

	private JPanel mancalaPanelA;

	private JPanel mancalaPanelB;


	//holds the mancala board

	public void setUpCenterPanel() {


		FlowLayout centerFlow = new FlowLayout();

		centerFlow.setHgap(0);

		centerPanel = new JPanel(centerFlow);

		centerPanel.setBackground(Color.pink);

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





	public void generatePits(String player, boolean owner) {


		for(int i = 1; i <= 6; i++) {





			Pit pit;


			if(owner == true) {

				pit = new Pit(player + i, owner, i-1);

			}

			else {

				pit = new Pit(player + i, owner, i + 6);

			}








			pit.setSize(40,75);


			pit.addMouseListener(new MouseListener() {



				@Override

				public void mouseClicked(MouseEvent e) {}



				@Override

				public void mousePressed(MouseEvent e) {

					// TODO Auto-generated method stub

					//only does something if the current player owns the pit

					if(pit.getPitOwner() == playerTurn) {

						prevStoneAmts = model.getStoneAmts();


						//mutator method to distribute the stones, accounting for special cases

						model.distributeStones(pit.getPitID(), playerTurn);






						if(playerTurn == true) {

							//player a's turn

							undoNumB = 0;

						}

						else {

							undoNumA = 0;

						}


						setPlayerTurn(!playerTurn);




					}


}



				@Override

				public void mouseReleased(MouseEvent e) {}



				@Override

				public void mouseEntered(MouseEvent e) {}



				@Override

				public void mouseExited(MouseEvent e) {}


			});


			pitArr.add(pit);




			//pitArr.add(new PitPanel(player + i));

		}


	}



	public void populateBoard() {


		Pit mancalaA = new Pit("MA", true, 6);

		Pit mancalaB = new Pit("MB", false, 13);


		generatePits("A", true);


		pitArr.add(mancalaA);


		generatePits("B", false);


		pitArr.add(mancalaB);








		JPanel aPits = new JPanel();

		JPanel bPits = new JPanel();

		BoxLayout pitsLayoutA = new BoxLayout(aPits, BoxLayout.X_AXIS);

		BoxLayout pitsLayoutB = new BoxLayout(bPits, BoxLayout.X_AXIS);










		for(int i = 0;i<6;i++) {


			aPits.add(pitArr.get(i));

		}




		for(int i = 12;i>6;i--) {


			bPits.add(pitArr.get(i));

		}


		System.out.println("pitsPanel size: " + pitsPanel.getPreferredSize());



		BoxLayout pitsPanelL = new BoxLayout(pitsPanel,BoxLayout.Y_AXIS);


		pitsPanel.setLayout(pitsPanelL);


		pitsPanel.add(bPits);

		pitsPanel.add(aPits);


		aPits.setBackground(Color.pink);

		bPits.setBackground(Color.pink);

		pitsPanel.setBackground(Color.pink);



		System.out.println("mancala pit size: " + mancalaA.getPreferredSize());


		System.out.println("mancala a panel size: " + mancalaPanelA.getPreferredSize());

		mancalaPanelA.add(mancalaA);



		mancalaPanelB.add(mancalaB);



		mancalaPanelA.setBackground(Color.pink);

		mancalaPanelB.setBackground(Color.pink);


	}





	//hold the title and error messages

	public void setUpTopPanel() {




	}



	// ****** BOTTOM PANEL METHODS ******


	private JPanel bottomPanel;

	private JButton undoButton;

	//private JButton passTurnButton;

	private JTextField startingStoneInput;


	//holds undo and pass turn buttons

	//holds textbox for number of starting stones

	public void setUpBottomPanel() {


		bottomPanel = new JPanel();

		undoButton = new JButton("UNDO");

		//passTurnButton = new JButton("PASS TURN");

		startingStoneInput = new JTextField("Initial Stone Amt");




		undoButton.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				setPlayerTurn(!playerTurn);


				if(playerTurn == true) {

					//player a's turn

					if(undoNumA != 3) {


						//resets the model to the prev array using mutator method


						//then increments amt of undos

						undoNumA++;

					}

					else {

						System.out.println("no more undos");

						if(playerTurn == true) {

							setPlayerTurn(!playerTurn);

						}

						//so, the game doesn't revert the passing and gives the turn back to the other player

					}

				}

				else {

					//player b's turn

					if(undoNumB != 3) {


						//resets the model to the prev array using mutator method


						//then increments amt of undos

						undoNumB++;

					}

					else {

						System.out.println("no more undos");

						if(playerTurn == false) {

							setPlayerTurn(!playerTurn);

						}

						//so, the game doesn't revert the passing and gives the turn back to other player

					}

				}

			}

		});


		startingStoneInput.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				if(Integer.parseInt(startingStoneInput.getText()) != 3 && Integer.parseInt(startingStoneInput.getText()) != 4) {

					//temporarily using a print statement, may change it to a text that gets printed out to GUI or remove it altogether

					System.out.println("Invalid number of stones. Please type in either 3 or 4.");

				}

				else {


					//calls mutator method for model that will set all the stones

					model.setStones(Integer.parseInt(startingStoneInput.getText()));


				}

			}

		});


		bottomPanel = new JPanel();

		bottomPanel.add(undoButton);


		bottomPanel.add(startingStoneInput);


		add(bottomPanel,BorderLayout.SOUTH);


	}




	//******* SET UP WHOLE BOARD METHOD *******


	//method to put all the panels in their proper places

	public void setUpBoardPanel() {


		setUpBottomPanel();

		setUpCenterPanel();

		rightPanel = new JPanel();

		leftPanel = new JPanel();

		add(rightPanel, BorderLayout.EAST);

		add(leftPanel, BorderLayout.WEST);



		topPanel = new JPanel();

		add(topPanel, BorderLayout.NORTH);



	}


	//****** SETTERS ******


	public void setPlayerTurn(boolean turn) {

		playerTurn = turn;


	}




}

