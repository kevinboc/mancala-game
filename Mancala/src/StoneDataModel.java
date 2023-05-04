/**
 * Mancala Game Project
 * @author Kevin Boc, Averi Tamlinco, and Brianna Gomez
 * @version 5/5/2023
 */
import javax.swing.event.*;
import java.util.*;


public class StoneDataModel {

	//Private Instance Variables
	int[] stoneAmts;
	int index;

	ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();

	/**
	 * Creates the Stone Data Model Object.
	 */
	public StoneDataModel() {
		stoneAmts = new int[14];
		index = 0;
	}

	/**
	 * Returns the integer array that holds stone amounts.
	 * 
	 * @return the integer array that holds the stone amounts
	 */
	public int[] getStoneAmts() {

		return stoneAmts;

	}

	/**
	 * Returns the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Attaches the observers/listeners to the data model.
	 * 
	 * @param c the change listener
	 */
	public void attach(ChangeListener c) {
		listeners.add(c);
	}

	/**
	 * A mutator method that distributes the stones and notifies views.
	 * 
	 * @param startPit the start pit
	 * @param player the player
	 */
	public void distributeStones(int startPit, boolean player) {
		System.out.println("distributes stones");

		int pitsToTraverse = stoneAmts[startPit];
		index = startPit + 1;

		stoneAmts[startPit] = 0;

		while (pitsToTraverse > 0) {
			stoneAmts[index] = stoneAmts[index] + 1;

			pitsToTraverse--;
			if (pitsToTraverse != 0) {
				index++; // preserves the last index that was added to
			}

			if ((index == 6 && player == false) || (index == 13 && player == true)) {
				index++;
			}

			if (index >= 14) {
				index = 0;
			}

		}

//if you're at the last pit
		if (pitsToTraverse == 0) {
//and it ends up with 1
			if ((stoneAmts[index] == 1)) {
				if (((0 <= index && index <= 5) && (player == true))) {
					int pitToSteal = 12 - index;
					stoneAmts[6] = stoneAmts[6] + stoneAmts[pitToSteal];
					stoneAmts[pitToSteal] = 0;
					System.out.println("A stole from B");
				} else if ((7 <= index && index <= 12) && (player == false)) {
					int pitToSteal = 5 - (index - 7);
					stoneAmts[13] = stoneAmts[13] + stoneAmts[pitToSteal];
					stoneAmts[pitToSteal] = 0;
					System.out.println("B stole from A");
				}
			}
		}
//end of last pit check

		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}

	}

	/**
	 * Sets the stone amount array to the specified amount.
	 * 
	 * @param amt the stone amount
	 */
	public void setStones(int amt) {
		System.out.println("initializes stones");
		for (int i = 0; i < stoneAmts.length; i++) {
			if (i != 6 && i != 13) {
				stoneAmts[i] = amt;
			}
		}

		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}

	}

	/**
	 * Mutator method that resets the stone amount array and notifies its observers/listeners.
	 * 
	 * @param amtArr the stone amount array
	 */
	public void resetStones(int[] amtArr) {

		for (int i = 0; i < stoneAmts.length; i++) {
			System.out.println("amt: " + amtArr[i]);
			stoneAmts[i] = amtArr[i];
		}

		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
		System.out.println("reset");

	}

	/**
	 * Mutator method that changes the model from capturing. It notifies its observers/listeners.
	 * 
	 * @param player the player
	 */
	public void captureAll(boolean player) {
		int rest = 0;
//player a will capture all from their own side
		if (player == true) {
			for (int i = 0; i < 6; i++) {
				rest += stoneAmts[i];
				stoneAmts[i] = 0;
			}
			stoneAmts[6] += rest;
		}
//player b will capture all from their own side
		else {
			for (int i = 7; i < 13; i++) {
				rest += stoneAmts[i];
				stoneAmts[i] = 0;
			}
			stoneAmts[13] += rest;
		}
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
		System.out.println("captured all, end game");
	}

}
