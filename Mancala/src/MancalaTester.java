
/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tamlinco, and Brianna Gomez
 * @version 5/5/2023
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Tester class for the Mancala Game.
 */
public class MancalaTester {

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

		JButton traditionalButton = new JButton("TRADITIONAL");

		JButton funButton = new JButton("FUN");

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
