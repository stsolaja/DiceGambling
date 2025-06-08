/*
 * Created on: May 1, 2025
 */
package edu.ilstu;

/**
 * Class that handles the player object and its getters and setters
 */
public class Player {

	private String name;
	private int bet;
	private int roll;
	
	public Player(String name, int bet, int roll) {
         this.name = name;
         this.bet = bet;
         this.roll = roll;
     }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	
	
}
