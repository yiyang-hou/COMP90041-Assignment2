
/** 
 * @author Yiyang Hou 
 * Student ID: 1202537 
 * Username: yiyhou1
 */
public class Entry {
	public static final int ENTRY_LENGTH = 7; // This is a constant, so can be set public and is
												// also widely used across all classes.
												
	private int entryId;
	private int[] numbers = new int[ENTRY_LENGTH];
	private String memberId = "";
	private int prizeDivision = 0;
	private int prize = 0;

	public int getPrize() {
		switch (prizeDivision) {
		case 1:
			this.prize = 50000;
			break;
		case 2:
			this.prize = 5000;
			break;
		case 3:
			this.prize = 1000;
			break;
		case 4:
			this.prize = 500;
			break;
		case 5:
			this.prize = 100;
			break;
		case 6:
			this.prize = 50;
			break;
		default:
			this.prize = 0;
			break;
		}
		return prize;
	}

	public void setPrizeDivision(int division) {
		this.prizeDivision = division;
	}

	public int getPrizeDivision() {
		return prizeDivision;
	}

	public void setEntryId(int newEntryId) {
		this.entryId = newEntryId;
	}

	public int getEntryId() {
		return this.entryId;
	}

	public void setNumbers(int[] newNumbers) {
		for (int i = 0; i < ENTRY_LENGTH; i++) {
			numbers[i] = newNumbers[i];
		}
	}

	public int[] getNumbers() {
		return numbers;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * Print method
	 */
	public void printNumbers() {
		for (int number : numbers) {
			if (number < 10) {
				System.out.print("  " + number);
			} else {
				System.out.print(" " + number);
			}
		}
	}

	/**
	 * 
	 * @param inputNumbers
	 */
	public void CreateNumbers(int[] inputNumbers) {
		for (int i = 0; i < ENTRY_LENGTH; i++) {
			numbers[i] = inputNumbers[i];
		}

	}
}
