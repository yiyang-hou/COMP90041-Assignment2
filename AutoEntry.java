
/** 
 * @author Yiyang Hou 
 * Student ID: 1202537 
 * Username: yiyhou1
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AutoEntry extends Entry {
	/**
	 * This method generates completely random numbers.
	 * 
	 * @return
	 */
	public int[] createNumbers() {
		ArrayList<Integer> validList = new ArrayList<Integer>();
		int[] tempNumbers = new int[7];
		for (int i = 1; i <= 35; i++) {
			validList.add(i);
		}
		Collections.shuffle(validList);
		for (int i = 0; i < 7; i++) {
			tempNumbers[i] = validList.get(i);
		}
		Arrays.sort(tempNumbers);
		setNumbers(tempNumbers);
		return tempNumbers;
	}

	/**
	 * Automated generation method with control by seed.
	 * 
	 * @param seed
	 * @return
	 */
	public int[] createNumbers(int seed) {
		ArrayList<Integer> validList = new ArrayList<Integer>();
		int[] tempNumbers = new int[7];
		for (int i = 1; i <= 35; i++) {
			validList.add(i);
		}
		Collections.shuffle(validList, new Random(seed));
		for (int i = 0; i < 7; i++) {
			tempNumbers[i] = validList.get(i);
		}
		Arrays.sort(tempNumbers);
		setNumbers(tempNumbers);
		return tempNumbers;
	}

	/**
	 * 
	 */
	@Override
	public void printNumbers() {
		super.printNumbers();
		System.out.print(" [Auto]");
	}
}
