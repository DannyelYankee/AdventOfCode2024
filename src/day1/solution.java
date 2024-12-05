package day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class solution {
	private static List<Integer> left = new ArrayList<Integer>();

	private static HashMap<Integer, Integer> rightNumbersCounter = new HashMap<Integer, Integer>();
	private static List<Integer> right = new ArrayList<Integer>();

	public static void main(String[] args) {
		fillLists();

		System.out.println(calculateDistancesSums());
		System.out.println(calculateSimilarityScore());

	}

	private static Integer calculateSimilarityScore() {
		var result = 0;

		for (final Integer number : left) {
			final var times = rightNumbersCounter.get(number) == null ? 0 : rightNumbersCounter.get(number);
			result += number * times;
		}

		return result;
	}

	private static Integer calculateDistancesSums() {
		var result = 0;
		for (int i = 0; i < left.size(); i++) {

			result += Math.abs(left.get(i) - right.get(i));
		}
		return result;
	}

	private static void fillLists() {
		final ClassLoader classLoader = solution.class.getClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream("day1/input.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

			reader.lines().forEach(line -> {
				final var splitted = line.split("   ");
				final var leftNumber = toNumber(splitted[0]);
				final var rightNumber = toNumber(splitted[1]);

				left.add(leftNumber);
				right.add(rightNumber);

				if (!rightNumbersCounter.containsKey(rightNumber)) {
					rightNumbersCounter.put(rightNumber, 1);
				} else {
					final var lastScore = rightNumbersCounter.get(rightNumber);
					rightNumbersCounter.put(rightNumber, lastScore + 1);
				}

			});
			Collections.sort(left);
			Collections.sort(right);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static Integer toNumber(String number) {
		try {
			return Integer.parseInt(number);
		} catch (final Exception e) {
			System.out.println("Error --> " + number);
			e.printStackTrace();
			return null;
		}
	}
}
