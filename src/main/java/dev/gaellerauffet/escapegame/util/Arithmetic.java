package dev.gaellerauffet.escapegame.util;

import java.util.ArrayList;
import java.util.Random;

public class Arithmetic {
	
	/**
	 * Get the min integer from an array
	 * 
	 * @param numbers		array of numbers from which get the lowest
	 * @return
	 */
	public final static int getMinNumber(ArrayList<Integer> numbers) {
		int minimum = 9;
		for(int i=0; i < numbers.size(); i++) {
			 minimum=Math.min(minimum, numbers.get(i));
		}
			
		return minimum;
	}
	
	/**
	 * Get the max integer from an array
	 * 
	 * @param numbers		array of numbers from which get the lowest
	 * @return
	 */
	public final static int getMaxNumber(ArrayList<Integer> numbers) {
		int maximum = 0;
		for(int i=0; i < numbers.size(); i++) {
			maximum=Math.max(maximum, numbers.get(i));
		}
			
		return maximum;
	}
	
	/**
	 * Defines a random number between 0 and 9
	 * 
	 * @return
	 */
	public final static int getRandomNumber(int min, int max) {
		Random random = new Random();
		int number = random.ints(1,min,max).findFirst().getAsInt();
		
		return number;
	}
	
	
}
