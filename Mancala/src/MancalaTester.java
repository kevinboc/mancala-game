import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.event.*;

public class MancalaTester {

	
	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setSize(new Dimension(750, 360));

		BoardPanel b = new BoardPanel();

		frame.add(b);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	

}