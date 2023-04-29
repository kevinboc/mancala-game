import java.awt.*;

import java.awt.geom.*;

import java.awt.geom.Rectangle2D.Double;

import javax.swing.*;

import javax.swing.event.*;

import java.util.*;

import java.awt.event.*;

public class MancalaTester {

	private static JPanel pane;

	private static JPanel welcomePanel;

	private static BoardPanel board;

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setSize(new Dimension(750, 360));

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

				board = new BoardPanel(new TraditionalStyle());

				pane.add(board);

				c.next(pane);

			}

		});

		funButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				board = new BoardPanel(new FunStyle());

				pane.add(board);

				c.next(pane);

			}

		});

		frame.add(pane);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

	}

}
