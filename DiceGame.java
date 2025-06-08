/*
 * Created on: May 2, 2025
 *
 */
package edu.ilstu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Driver class
 */
public class DiceGame {

	/**
	 * @param args
	 */
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	static ArrayList<Player> players = new ArrayList<>();
	static int numOfPlayers;
	
	public static void main(String[] args) {
		System.out.println("____Rules____");
		System.out.println("This is a multiplayer dice game. \nEach player places a bet and whoever rolls the highest number wins the total prize."
				+ "\nIf there's a tie, the winners roll again until theres a winner. \n");
		initial();
		int grandPrize = getTotalBet(players);
		System.out.println("The total money on the table is $" + grandPrize + " winner takes all!!!");
		rollDice(players);
		ArrayList<Player> winners = getWinners(players);
		if (winners.size() == 1) {
            System.out.println("\n🏆 " + winners.get(0).getName() + " wins the grand prize of $" + grandPrize + "!");
        }
		else
		tieBreaker(winners, grandPrize);
	}
	
	
	
	public static void initial() {
		
		do {
			System.out.print("Enter the number of players (minimum of 2 ): ");
            while (!scan.hasNextInt()) {
                System.out.print("Invalid input. Please enter an integer greater than or equal to 2:");
                scan.next(); 
            }
		numOfPlayers = scan.nextInt();
		scan.nextLine();
		}while (numOfPlayers < 2);
		
		
		for(int i = 1; i <= numOfPlayers; i++) {
			
			System.out.print("Enter player " + i +"\'s name: ");
			String name = scan.nextLine();
			
			int bet;
			
			do {
				System.out.print("Enter bet amount for " + name + " (minimum of $1 bet): $");
	            while (!scan.hasNextInt()) {
	                System.out.print("Invalid input. Please enter an integer (minimum of $1 bet): ");
	                scan.next(); 
	            }
	           bet = scan.nextInt();
	            scan.nextLine();
			}while (bet < 1);
			
           
            
            players.add(new Player(name, bet, 0));
            
		}
	}
		
	public static void rollDice(ArrayList<Player> players) {
		
		for(Player player : players) {
			System.out.println("\n"+ player.getName() + ", press ENTER to roll the dice...");
	        scan.nextLine();
	        System.out.println("🎲");
			int roll = rand.nextInt(6) + 1;
			player.setRoll(roll);
		    System.out.println(player.getName() + " rolled a " + roll);
		}
		     
		}
	
	public static int getTotalBet(ArrayList<Player> players) {
		    int total = 0;
		    for (Player player : players) {
		        total += player.getBet();
		    }
		    return total;
	
	}
		
	public static ArrayList<Player> getWinners(ArrayList<Player> players) {
	    ArrayList<Player> winners = new ArrayList<>();
	    int highest = 0;

	    for (Player p : players) {
	        if (p.getRoll() > highest) {
	            highest = p.getRoll();
	            winners.clear();
	            winners.add(p);
	        } else if (p.getRoll() == highest) {
	            winners.add(p);
	        }
	    }

	    return winners;
	}
	
	public static void tieBreaker(ArrayList<Player> players, int grandPrize) {
	    int round = 1;
	    System.out.println("\n🎲 Round " + round + ": Rolling dice only for tied player");
	        rollDice(players);
	        
	        ArrayList<Player> winners = getWinners(players);

	        if (winners.size() == 1) {
	            Player winner = winners.get(0);
	            System.out.println("\n🏆 " + winner.getName() + " wins the grand prize of $" +  grandPrize + "!");
	          
	        } else {
	            System.out.print("\n🤝 another Tie between: ");
	            for (Player p : winners) {
	                System.out.print(p.getName() + " ");
	            }
	            System.out.println("\nRe-rolling only for tied players...");
	            tieBreaker(winners, grandPrize);
	        }
	    }
	}
		

