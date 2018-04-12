/**
 * This file simulates the playing and scoring of one
 * hand of yahtzee.
 * 
 * CPSC 224-02, Spring 2018
 * Programming Assignment #5
 * 
 * @author Michelle Snowball
 * 
 * @version v1.0 3/9/18
 */

public class Yahtzee{
	public static int DICE_IN_PLAY = 5;
    public static int NUMBER_OF_TURNS = 3;
    public static int NUMBER_OF_SIDES = 6;
	public static void main(String[] args)
	{
		Player player1 = new Player(DICE_IN_PLAY, NUMBER_OF_SIDES, NUMBER_OF_TURNS);

	    //initialize enough elements into the hand
	    for(int i = 0; i < DICE_IN_PLAY; i++) {
	    	player1.hand.add(0, i);
	    }
		
	    player1.takeTurn();
	}
}
