package com.game.guess;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

/**
 * The Class GuessNumber.
 * 
 * @author BattiniS
 */

public class GuessNumber {

	/** The Constant CLUE_HIGH. */
	public final static String CLUE_HIGH = "high";

	/** The Constant CLUE_LOW. */
	public final static String CLUE_LOW = "low";

	/** The Constant CLUE_YES. */
	public final static String CLUE_YES = "yes";

	/** The range top. */
	private static Integer RANGE_TOP = null;

	/** The range bottom. */
	private static Integer RANGE_BOTTOM = null;

	/** The current number. */
	private static Integer currentNumber = null;

	private static Random randomGenerator =null;
	/**
	 * The main method.
	 * 
	 * @param args the args
	 */
	public static void main(final String[] args) {

		randomGenerator =new Random();
		Scanner scanner = null;
		try {

			if (args.length > 0 && args[0] != null && args[0].equals("test") && args[1] != null) {
				final URL fileInput = GuessNumber.class.getResource(args[1]);
				scanner = new Scanner(new File(fileInput.getPath()));
			} else {
				scanner = new Scanner(System.in);
			}
			System.out.println("Welcome to Guess Number game\nImagine a Number in your mind" + ", This program will guess it");
			System.out.println("Each time programs guesses number , Type yes or high or low");
			System.out.println("Press Enter when you are ready");
			scanner.nextLine();
			boolean found = false;
			currentNumber = getNextNumber();
			while (!found) {
				System.out.println("is it :  " + currentNumber);

				System.out.println("yes or high or low?:");
				String clue = null;

				clue = scanner.nextLine();

				if (clue != null && CLUE_YES.equalsIgnoreCase(clue.trim())) {
					System.out.println("Awesome!.. its is   " + currentNumber);
					found = true;
				} else if (clue != null && CLUE_HIGH.equalsIgnoreCase(clue.trim())) {
					Integer newHigher = getHigher(currentNumber);
					if (currentNumber.intValue() == newHigher.intValue()) {
						System.out.println("You have lost your track , earlier you said lower than : " + (currentNumber - 1));
						System.out.println("I am resetting , lets try again");
						RANGE_BOTTOM = currentNumber;
						RANGE_TOP = null;
						newHigher = getHigher(currentNumber);
					}
					currentNumber = newHigher;

				} else if (clue != null && CLUE_LOW.equalsIgnoreCase(clue.trim())) {
					Integer newLower = getLower(currentNumber);
					if (currentNumber.intValue() == newLower.intValue()) {
						System.out.println("You have lost your track , earlier you said higher than : " + (currentNumber + 1));
						System.out.println("I am resetting , lets try again");
						RANGE_TOP = currentNumber;
						RANGE_BOTTOM = null;
						newLower = getLower(currentNumber);
					}
					currentNumber = newLower;

				}

			}
			;
			System.out.println("Thanks for Playing\nBye");

		} catch (final Exception e) {

			e.printStackTrace(System.err);

		} finally {
			scanner.close();
		}
	}

	/**
	 * Gets the lower.
	 * 
	 * @param currentNumber the current number
	 * @return the lower
	 */
	private static Integer getLower(final Integer currentNumber) {
		RANGE_TOP = currentNumber;
		return getNextNumber();
	}

	/**
	 * Gets the higher.
	 * 
	 * @param currentNumber the current number
	 * @return the higher
	 */
	private static Integer getHigher(final Integer currentNumber) {
		RANGE_BOTTOM = currentNumber;
		return getNextNumber();
	}

	/**
	 * Gets the next number.
	 * 
	 * @return the next number
	 */
	private static Integer getNextNumber() {
		if (RANGE_TOP == null && RANGE_BOTTOM == null) {
			return randomGenerator.nextInt(10000);
		} else if (RANGE_TOP == null) {
			if (RANGE_BOTTOM == 0) {
				return RANGE_BOTTOM + Math.abs(randomGenerator.nextInt(10000));
			} else {
				return RANGE_BOTTOM + Math.abs(RANGE_BOTTOM);
			}
		} else if (RANGE_BOTTOM == null) {
			if (RANGE_TOP == 0) {
				return -Math.abs(randomGenerator.nextInt(10000));
			} else {
				return RANGE_TOP - Math.abs(RANGE_TOP);
			}
		} else {
			return (RANGE_BOTTOM + RANGE_TOP) / 2;
		}

	}

	/**
	 * Gets the current number.
	 * 
	 * @return the current number
	 */
	public static Integer getCurrentNumber() {
		return currentNumber;
	}
}
