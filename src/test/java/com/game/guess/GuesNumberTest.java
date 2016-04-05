package com.game.guess;

import java.util.Scanner;

import junit.framework.Assert;
import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;

/**
 * The Class GuesNumberTest.
 */
public class GuesNumberTest {

	/** The guess num. */
	private final Integer guessNum = 8;

	/**
	 * Test main.
	 */
	@Test
	public void testMain() {
		new MockUp<Scanner>() {
			@Mock
			String nextLine() {
				final Integer currentNumber = GuessNumber.getCurrentNumber();
				final String clue = "";
				if (currentNumber == null) {

				} else if (currentNumber == guessNum) {
					return "yes";
				} else if (currentNumber < guessNum) {
					return "high";
				} else if (currentNumber > guessNum) {
					return "low";
				}
				return clue + "\r\n";
			}
		};
		System.out.println("starting test");
		GuessNumber.main(new String[] {});
		Assert.assertEquals(guessNum, GuessNumber.getCurrentNumber());
		System.out.println("ending test");
	}
}
