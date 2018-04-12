import java.util.Random;
import java.util.Scanner;

/**
 * This file is the implementation of
 * the class Player for a Yahtzee game.
 * 
 * CPSC 224-02, Spring 2018
 * Programming Assignment #6
 * 
 * @author Michelle Snowball
 * 
 * @version v1.0 3/23/18
 */

public class Player{
	public Hand hand;
	public int turn = 0;
	private int numberOfTurns = 3;
	private int numberOfSides = 6;
	private int diceInPlay = 5;
	Dice toAdd = new Dice(0, false);
	Scorecard scores;
	
	Player(int numDice, int numSides, int numTurns){
		numberOfTurns = numTurns;
		numberOfSides = numSides;
		diceInPlay = numDice;
		hand = new Hand(diceInPlay, numSides);
		scores = new Scorecard(numDice, numSides, numTurns);
	}
	
	public void addTurn() {
		turn++;
	}
	
	public void resetTurn() {
		turn = 0;
	}
	
	public void takeTurn() {
		turn = 0;
		int timesThrough = 0;
		char playAgain = 'y';
		Scanner getIt = new Scanner(System.in);

		while (playAgain == 'y')
		{
			
		    String keep = "nnnnn"; //setup to roll all dice in the first roll
		    while (turn < numberOfTurns && keep != "yyyyy")
		    {
		    	
		        //roll dice not kept
		        for (int dieNumber = 0; dieNumber < diceInPlay; dieNumber++)
		        {
		            if (keep.charAt(dieNumber) != 'y')
		                hand.set(dieNumber, rollDie(numberOfSides));
		        }
		        //output roll
		        System.out.print("Your roll was: ");
		        for (int dieNumber = 0; dieNumber < diceInPlay; dieNumber++)
		        {
		            System.out.print(hand.get(dieNumber) + " ");
		        }
		        System.out.println();
		        //if not the last roll of the hand prompt the user for dice to keep
		        if (turn < numberOfTurns)
		        {
		            System.out.print("enter dice to keep (y or n): ");
		            keep = getIt.nextLine();
		            
		        }
		        turn++;
		        
		    }
		    
		    resetTurn();
		    
		    //start scoring
		    //hand need to be sorted to check for straights
	
		    hand.sortHand();
		    System.out.print("Here is your sorted hand : ");
		    for (int dieNumber = 0; dieNumber < diceInPlay; dieNumber++)
		    {
		        System.out.print(hand.get(dieNumber) + " ");
		    }
		    System.out.println();
		    
		    if(printScores())
		        chooseScore(getIt);
		    
		    System.out.print("\nEnter 'y' to play again ");
		    String toPlayAgain = "y";
		    if(getIt.hasNextLine())
		        toPlayAgain = getIt.nextLine();
		    
		    playAgain = toPlayAgain.charAt(0);
		    
		    if(timesThrough >= 13)
		    	playAgain = 'n';
		    
		    timesThrough++;
		    
		}
		
		scores.displayTotal();
		getIt.close();
	}
	
	public boolean printScores() {
		int scoreIndex = 1;
		boolean hasDisplayed = false;
		//int scoreChoice = 0;
		
		//upper scorecard
	    for (int dieValue = 1; dieValue <= numberOfSides; dieValue++)
	    {
	    	int currentCount = 0;
	    	if(!scores.isScored.get(dieValue - 1)) { //check to see that this value has not been scored
	    		for (int diePosition = 0; diePosition < diceInPlay; diePosition++)
	 	        {
	 	            if (hand.get(diePosition) == dieValue)
	 	                currentCount++;
	 	        }
	 	        System.out.println(scoreIndex + ": Score " + dieValue * currentCount + " on the "
	 	                          + dieValue + " line");
	 	        hasDisplayed = true;
	    	}
	        
	       scoreIndex++;
	       
	    }

	    //lower scorecard
	    if(!scores.threeOfAKindScored) {
	    	if (hand.maxOfAKindFound(hand) >= 3)
		    {
		        System.out.println(scoreIndex + ": Score " + hand.totalAllDice(hand) + " on the "
		                            + "3 of a Kind line");
		    }
		    else System.out.println(scoreIndex + ": Score 0 on the 3 of a Kind line");
	    	hasDisplayed = true;
	    }
	    scoreIndex++;
        
	    if(!scores.fourOfAKindScored) {
		    if (hand.maxOfAKindFound(hand) >= 4)
		    {
		    	System.out.println(scoreIndex + ": Score " + hand.totalAllDice(hand) + " on the "
		                           + "4 of a Kind line");
		    }
		    else System.out.println(scoreIndex + ": Score 0 on the 4 of a Kind line");
		    hasDisplayed = true;
	    }
	    scoreIndex++;
        
	    if(!scores.fullHouseScored) {
	    	if (hand.fullHouseFound(hand))
		    	System.out.println(scoreIndex + ": Score 25 on the Full House line");
		    else
		    	System.out.println(scoreIndex + ": Score 0 on the Full House line");
	    	hasDisplayed = true;
	    }
	    scoreIndex++;
	    
	    if(!scores.smallStraightScored) {
	    	if (hand.maxStraightFound(hand) >= 4)
		    	System.out.println(scoreIndex + ": Score 30 on the Small Straight line");
		    else
		    	System.out.println(scoreIndex + ": Score 0 on the Small Straight line");
	    	hasDisplayed = true;
	    }
	    scoreIndex++;
	    
	    if(!scores.largeStraightScored) {
	    	if (hand.maxStraightFound(hand) >= 5)
		    	System.out.println(scoreIndex + ": Score 40 on the Large Straight line");
		    else
		    	System.out.println(scoreIndex + ": Score 0 on the Large Straight line");
	    	hasDisplayed = true;
	    } 
	    scoreIndex++;
	    
	    if(!scores.largeStraightScored) {
	    	if (hand.maxOfAKindFound(hand) >= 5)
		    	System.out.println(scoreIndex + ": Score 50 on the Yahtzee line");
		    else
		    	System.out.println(scoreIndex + ": Score 0 on the Yahtzee line");
	    	hasDisplayed = true;
	    }
	    scoreIndex++;
	    
	    if(!scores.chanceScored) {
	    	System.out.println(scoreIndex + ": Score " + hand.totalAllDice(hand) + " on the "
                    + "Chance line");
	    	hasDisplayed = true;
	    }
	    return hasDisplayed;
	}
	
	/**
     * this function simulates the rolling of a single die
     * 
     * @returns the random value of a die between 1 and the
     * number of sides
     */
	public int rollDie(int numSides) {
		Random rand = new Random();
	    int roll = rand.nextInt(numSides) + 1;
	    return roll;
	}
	
	public void chooseScore(Scanner getter) {

		int scoreIndex = 0;
		char choice = 'n';
		
		System.out.println("Enter 'y' or 'n' to choose your score:");
		//upper scorecard
	    for (int dieValue = 1; dieValue <= numberOfSides; dieValue++)
	    {
	    	int currentCount = 0;
	    	if(!scores.isScored.get(dieValue - 1)) { //check to see that this value has not been scored
	    		for (int diePosition = 0; diePosition < diceInPlay; diePosition++)
	 	        {
	 	            if (hand.get(diePosition) == dieValue)
	 	                currentCount++;
	 	        }
	 	        System.out.println("Score " + dieValue * currentCount + " on the "
	 	                          + dieValue + " line?");
	 	        
	 	        choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, dieValue*currentCount);
	 	        	return;
	 	        }
	    	}
	        
	       scoreIndex++;
	       
	    }

	    //lower scorecard
	    if(!scores.threeOfAKindScored) {
	    	if (hand.maxOfAKindFound(hand) >= 3)
		    {
		        System.out.println(scoreIndex + ": Score " + hand.totalAllDice(hand) + " on the "
		                            + "3 of a Kind line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, hand.totalAllDice(hand));
	 	        	return;
	 	        }
		    } else {
		    	System.out.println(scoreIndex + ": Score 0 on the 3 of a Kind line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 0);
	 	        	return;
	 	        }
		    }
	    	
	    }
	    scoreIndex++;
        
	    if(!scores.fourOfAKindScored) {
		    if (hand.maxOfAKindFound(hand) >= 4)
		    {
		    	System.out.println(scoreIndex + ": Score " + hand.totalAllDice(hand) + " on the "
		                           + "4 of a Kind line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, hand.totalAllDice(hand));
	 	        	return;
	 	        }
		    } else {
		    	System.out.println(scoreIndex + ": Score 0 on the 4 of a Kind line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 0);
	 	        	return;
	 	        }
		    }
	        
	    }
	    scoreIndex++;
        
	    if(!scores.fullHouseScored) {
	    	if (hand.fullHouseFound(hand)) {
		    	System.out.println(scoreIndex + ": Score 25 on the Full House line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 25);
	 	        	return;
	 	        }
	    	} else {
		    	System.out.println(scoreIndex + ": Score 0 on the Full House line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 0);
	 	        	return;
	 	        }
	    	}
	        
	    }
	    scoreIndex++;
	    
	    if(!scores.smallStraightScored) {
	    	if (hand.maxStraightFound(hand) >= 4) {
		    	System.out.println(scoreIndex + ": Score 30 on the Small Straight line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 30);
	 	        	return;
	 	        }
	    	} else {
		    	System.out.println(scoreIndex + ": Score 0 on the Small Straight line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 0);
	 	        	return;
	 	        }
	    	}
	        
	    }
	    scoreIndex++;
	    
	    if(!scores.largeStraightScored) {
	    	if (hand.maxStraightFound(hand) >= 5) {
		    	System.out.println(scoreIndex + ": Score 40 on the Large Straight line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 40);
	 	        	return;
	 	        }
	    	} else {
		    	System.out.println(scoreIndex + ": Score 0 on the Large Straight line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 0);
	 	        	return;
	 	        }
	    	}
	        
	    } 
	    scoreIndex++;
	    
	    if(!scores.largeStraightScored) {
	    	if (hand.maxOfAKindFound(hand) >= 5) {
		    	System.out.println(scoreIndex + ": Score 50 on the Yahtzee line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 50);
	 	        	return;
	 	        }
	    	} else {
		    	System.out.println(scoreIndex + ": Score 0 on the Yahtzee line?");
                choice = getter.nextLine().charAt(0);
	 	        
	 	        if(choice == 'y') {
	 	        	scores.setScore(scoreIndex, 0);
	 	        	return;
	 	        }
	    	}
	        
	    }
	    scoreIndex++;
	    
	    if(!scores.chanceScored) {
	    	System.out.println(scoreIndex + ": Score " + hand.totalAllDice(hand) + " on the "
                    + "Chance line?");
            choice = getter.nextLine().charAt(0);
 	        
 	        if(choice == 'y') {
 	        	scores.setScore(scoreIndex, hand.totalAllDice(hand));
 	        	return;
 	        }
	    }
	}
	
}