/**
 * This file is the implementation of
 * the class Scorecard for a Yahtzee game.
 * 
 * CPSC 224-02, Spring 2018
 * Programming Assignment #6
 * 
 * @author Michelle Snowball
 * 
 * @version v1.0 3/23/18
 */

import java.util.ArrayList; //for ArrayList
public class Scorecard{
	
	public ArrayList<Integer> score;
	public ArrayList<Boolean> isScored;
	int threeOfAKind = 0;
	boolean threeOfAKindScored = false;
	int fourOfAKind = 0;
	boolean fourOfAKindScored = false;
	int fullHouse = 0;
	boolean fullHouseScored = false;
	int smallStraight = 0;
	boolean smallStraightScored = false;
	int largeStraight = 0;
	boolean largeStraightScored = false;
	int yahtzee = 0;
	boolean yahtzeeScored = false;
	int chance = 0;
	boolean chanceScored = false;
	int totalScore = 0;
	
	Scorecard(int numDice, int numSides, int numTurns){
		score = new ArrayList<Integer>(numSides);
		isScored = new ArrayList<Boolean>(numSides);
		
		for(int i = 0; i < numSides; i++) {
			isScored.add(i, false);
		}
		for(int i = 0; i < numSides; i++) {
			score.add(i, 0);
		}
		
	}
	
	public void setScore(int index, int data) {
	    if(index > 0 && index < score.size()) {
	    	score.set(index, data);
	    	isScored.set(index, true);
	    }
	}
	
	public void displayTotal() {
		int totalScore = 0;
		for(int i = 0; i < score.size(); i++) {
			totalScore += score.get(i);
		}
		System.out.println("Your total score is " + totalScore);
	}
	
	
}