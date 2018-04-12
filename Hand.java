/**
 * This file is the implementation of
 * the class Hand for a Yahtzee game.
 * 
 * CPSC 224-02, Spring 2018
 * Programming Assignment #6
 * 
 * @author Michelle Snowball
 * 
 * @version v1.0 3/23/18
 */

public class Hand{
	private static int numberOfDice = 0;
	public static int[] hand;
	Hand(int numDice, int numSides){
		numberOfDice = numDice;
		hand = new int[numberOfDice];
	}
	
	public void add(int number, int index) {
		hand[index] = number;
	}
	
	public void sortHand() {
		int temp = 0;
		boolean hasSwapped = true;
		while(hasSwapped) {
			hasSwapped = false;
			for(int i = 0; i < hand.length - 1; i++) {
				if(hand[i] > hand[i+1]) {
					temp = hand[i];
					hand[i] = hand[i+1];
					hand[i+1] = temp;
					hasSwapped = true;
				}
			}
		}
	}
	
	public int get(int index) {
		return hand[index];
	}
	
	public void set(int index, int what) {
		hand[index] = what;
	}
	
	/**
     * this function returns the count of the die value occurring most in the hand
     * but not the value itself
     * 
     * @param hand is the player's hand
     *     
     * @returns the number of times the most-occurring value occurs in the hand
     */
	public int maxOfAKindFound(Hand hand)
	{
	    int maxCount = 0;
	    int currentCount ;
	    for (int dieValue = 1; dieValue <=6; dieValue++)
	    {
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < 5; diePosition++)
	        {
	            if (hand.get(diePosition) == dieValue)
	                currentCount++;
	        }
	        if (currentCount > maxCount)
	            maxCount = currentCount;
	    }
	    return maxCount;
	}
	
	/**
     * this function returns the total value of all dice in a hand
     * 
     * @param hand is the player's hand
     *     
     * @returns the total value of all the dice in the hand
     */
	public int totalAllDice(Hand hand)
	{
	    int total = 0;
	    for (int diePosition = 0; diePosition < 5; diePosition++)
	    {
	        total += hand.get(diePosition);
	    }
	    return total;
	}
	
	/**
     * this function returns the length of the longest
	 * straight found in a hand
     * 
     * @param hand is the player's hand
     *     
     * @returns the length of the longest straight
     * found in a hand
     */
	public int maxStraightFound(Hand hand)
	{
	    int maxLength = 1;
	    int curLength = 1;
	    for(int counter = 0; counter < 4; counter++)
	    {
	        if (hand.get(counter) + 1 == hand.get(counter + 1)) //jump of 1
	            curLength++;
	        else if (hand.get(counter) + 1 < hand.get(counter + 1)) //jump of >= 2
	            curLength = 1;
	        if (curLength > maxLength)
	            maxLength = curLength;
	    }
	    return maxLength;
	}
	
	/**
     * this function returns true if the hand is a full house
     * or false if it does not
     * 
     * @param hand is the player's hand
     *      
     * @returns true if the hand has a three of a kind and also
     *  has a two of a kind (with different numbers).
     */
	public boolean fullHouseFound(Hand hand)
	{
	    boolean foundFH = false;
	    boolean found3K = false;
	    boolean found2K = false;
	    int currentCount ;
	    for (int dieValue = 1; dieValue <=6; dieValue++)
	    {
	        currentCount = 0;
	        for (int diePosition = 0; diePosition < 5; diePosition++)
	        {
	            if (hand.get(diePosition) == dieValue)
	                currentCount++;
	        }
	        if (currentCount == 2)
	            found2K = true;
	        if (currentCount == 3)
	            found3K = true;
	    }
	    if (found2K && found3K)
	        foundFH = true;
	    return foundFH;
	}
}