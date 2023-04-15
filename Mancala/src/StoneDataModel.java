import java.awt.*;

import java.awt.geom.*;

import java.awt.geom.Rectangle2D.Double;

import javax.swing.*;

import javax.swing.event.*;

import java.util.*;

import java.awt.event.*;

public class StoneDataModel {



	int[] stoneAmts;




	ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();


	public StoneDataModel() {

			stoneAmts = new int[14];


	}


	public int[] getStoneAmts() {


			return stoneAmts;


	}


	public void attach(ChangeListener c) {

			listeners.add(c);

	}


	public void distributeStones(int startPit, boolean player) {

			System.out.println("distributes stones");


			int pitsToTraverse = stoneAmts[startPit];

			int index = startPit+1;


			stoneAmts[startPit] = 0;


			while(pitsToTraverse > 0) {

					stoneAmts[index] = stoneAmts[index]+1;


					index++;

					pitsToTraverse--;


					if((index==6 && player==false) || (index==13 && player==true)){

						index++;

					}


					if(index >= 14) {

						index=0;

					}


			}



			for(ChangeListener l : listeners) {

				l.stateChanged(new ChangeEvent(this));

			}


	}


	public void setStones(int amt) {

		System.out.println("initializes stones");

		for(int i = 0; i < stoneAmts.length;i++) {

			if(i != 6 && i != 13) {

				stoneAmts[i] = amt;

			}

		}


		for(ChangeListener l : listeners) {

			l.stateChanged(new ChangeEvent(this));

		}



	}


}