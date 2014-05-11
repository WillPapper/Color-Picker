package me.papper.colorpicker;

import java.util.LinkedList;
import java.util.Random;

import android.graphics.Color;

public class Colors {
	static final int ARRAY_SIZE = 10;
	static final int ALPHA = 255;
	static LinkedList<Integer> red = new LinkedList<Integer>();
	static LinkedList<Integer> green = new LinkedList<Integer>();
	static LinkedList<Integer> blue = new LinkedList<Integer>();

	public static int generateColor() {
		/* When the stored values reach 100, the oldest 50 are removed */
		if (red.size() >= 100 && green.size() >= 100 && blue.size() >= 100) {
			for (int i = red.size() - 1; i >= 50; i--) {
				red.removeLast();
				green.removeLast();
				blue.removeLast();
			}
		}
		Random r = new Random();
		red.addFirst(r.nextInt(256));
		green.addFirst(r.nextInt(256));
		blue.addFirst(r.nextInt(256));
		return returnColor();
	}

	public static int returnColor() {
		return Color.argb(ALPHA, red.getFirst(), green.getFirst(),
				blue.getFirst());
	}

	public static int undo() {
		/*
		 * The checks are technically redundant, but the extra CPU time used is
		 * negligible and it's better to err on the side of caution, especially
		 * when a bug could cause a crash
		 */
		if (red.size() > 1 && green.size() > 1 && blue.size() > 1) {
			red.removeFirst();
			green.removeFirst();
			blue.removeFirst();
		}
		return returnColor();
	}
}