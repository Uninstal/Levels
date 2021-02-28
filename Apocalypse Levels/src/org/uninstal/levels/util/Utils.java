package org.uninstal.levels.util;

import java.util.Random;

public class Utils {

	private static Random rand = new Random();
	
	public static int random(int start, int end) {
		return rand.nextInt(end) + start;
	}
}
