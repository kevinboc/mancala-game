
/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tanlimco, and Brianna Gomez
 * @version 5/5/2023
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Tester class for the Mancala Game.
 */
public class MancalaTest {

	//Constants
	private static JPanel pane;
	private static JPanel welcomePanel;
	private static BoardPanel board;

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setSize(new Dimension(850, 360));

		CardLayout c = new CardLayout();

		pane = new JPanel(c);

		welcomePanel = new JPanel();
		
		JLabel welcomeMessage = new JLabel("Choose a Mancala style:");
		Font welcomeFont = new Font("Verdana", Font.PLAIN, 18);
		welcomeMessage.setFont(welcomeFont);
		
		JButton traditionalButton = new JButton("TRADITIONAL");
		traditionalButton.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		
		JButton funButton = new JButton("FUN");
		funButton.setFont(new Font("Impact", Font.BOLD, 25));

		welcomePanel.add(welcomeMessage);
		welcomePanel.add(traditionalButton);

		welcomePanel.add(funButton);
		
		pane.add(welcomePanel);

		traditionalButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				board = new BoardPanel(new TraditionalFormat());

				pane.add(board);

				c.next(pane);

			}

		});

		funButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				board = new BoardPanel(new FunFormat());

				pane.add(board);

				c.next(pane);

			}

		});

		frame.add(pane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

	}
	
}
