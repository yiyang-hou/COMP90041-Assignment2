
/** 
 * @author Yiyang Hou 
 * Student ID: 1202537 
 * Username: yiyhou1
 */
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class defines competition objects.
 * 
 * @author houyi
 *
 */
public class Competition {
	private String name; // competition name
	private int id; // competition identifier
	private ArrayList<Entry> latestEntriesInput = new ArrayList<Entry>();// ArrayList of latest
																			// entry input.
	private ArrayList<Entry> cEntryList = new ArrayList<Entry>();// ArrayList of all entries in the
																	// competition.
	private ArrayList<Entry> wEntryList = new ArrayList<Entry>();// ArrayList of all winning
																	// entries;
	private int totalPrize = 0;
	private int wEntryNum = 0;
	private boolean active = true;
	private int entryNum = 0;
	private boolean successAdd = true;
	// magic numbers:
	private static final int ID_LENGTH = 6;
	private static final int AMOUNT_PER_ENTRY = 50;

	public int getEntryNum() {
		return entryNum;
	}

	public void setEntryNum(int newEntryNum) {
		this.entryNum = newEntryNum;
	}

	public boolean getSuccessAdd() {
		return successAdd;
	}

	public boolean getActive() {
		return active;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Entry> getLatestEntriesInput() {
		return latestEntriesInput;
	}

	/**
	 * Each competition will be given an id and a name when created and will never
	 * be changed, therefore id and name are put in the parameter list of the
	 * constructor and no setters are needed.
	 * 
	 * @param id
	 * @param name
	 */
	public Competition(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * This method adds new entries to the competition.
	 * 
	 * @param keyboard
	 */
	public void addEntries(Scanner keyboard) {
		String inputMemberId = "";
		String inputBillId = "";
		double inputAmount = 0;
		int totalEntries = 0;
		int manualEntries = 0;
		int autoEntries = 0;
		int tempNum = 0;
		String inputLine = "";
		int inputLength = 0;
		int[] inputNumbers = new int[Entry.ENTRY_LENGTH];
		ArrayList<Entry> tempEntryList = new ArrayList<Entry>();
		this.successAdd = true;

		Outer_Loop1: while (true) {
			System.out.println("Member ID: ");
			inputMemberId = keyboard.next();
			if (inputMemberId.length() == ID_LENGTH) {
				for (int i = 0; i < ID_LENGTH; i++) {
					if (!Character.isDigit(inputMemberId.charAt(i))) {
						System.out.println("Invalid member id! It must be a 6-digit number. "
								+ "Please try again.");
						continue Outer_Loop1;
					}
				}
				break Outer_Loop1;
			} else
				System.out.println(
						"Invalid member id! It must be a 6-digit number. Please try again.");
		}

		Outer_Loop2: while (true) {
			System.out.println("Bill ID: ");
			inputBillId = keyboard.next();
			if (inputBillId.length() == ID_LENGTH) {
				for (int i = 0; i < ID_LENGTH; i++) {
					if (!Character.isDigit(inputBillId.charAt(i))) {
						System.out.println("Invalid bill id! It must be a 6-digit number. "
								+ "Please try again.");
						continue Outer_Loop2;
					}
				}
				break Outer_Loop2;
			} else
				System.out
						.println("Invalid bill id! It must be a 6-digit number."
								+ " Please try again.");
		}

		System.out.println("Total amount: ");
		inputAmount = keyboard.nextDouble();
		totalEntries = (int) inputAmount / AMOUNT_PER_ENTRY;
		if (totalEntries == 0) {
			System.out.println(
					"This bill is not eligible for an entry."
					+ " The total amount is smaller than $50.0");
			successAdd = false;
			return;// No entry will be added.
		}
		System.out.println("This bill is eligible for " + totalEntries
				+ " entries. How many manual entries did the customer fill up?: ");
		manualEntries = keyboard.nextInt();
		keyboard.nextLine();
		autoEntries = totalEntries - manualEntries;
		for (int mEntries = 0; mEntries < manualEntries; mEntries++) {
			// Take input and do input check.
			Outer_Loop3: while (true) {
				System.out.println("Please enter 7 different numbers (from the range 1 to 35) "
						+ "separated by whitespace.");
				inputLine = keyboard.nextLine();
				StringTokenizer inputElement = new StringTokenizer(inputLine);
				inputLength = inputElement.countTokens();
				if (inputLength > Entry.ENTRY_LENGTH) {
					System.out.println(
							"Invalid input! More than 7 numbers are provided. Please try again!");
				} else if (inputLength < Entry.ENTRY_LENGTH) {
					System.out.println("Invalid input! Fewer than 7 numbers are provided. "
							+ "Please try again!");
				} else {
					for (int i = 0; i < Entry.ENTRY_LENGTH; i++) {
						tempNum = Integer.parseInt(inputElement.nextToken());
						inputNumbers[i] = tempNum;
					}
					Arrays.sort(inputNumbers);
					for (int j = 0; j < Entry.ENTRY_LENGTH - 1; j++) {
						if (inputNumbers[j] == inputNumbers[j + 1]) {
							System.out
									.println("Invalid input! All numbers must be " + "different!");
							break;
						}

						else if (j == Entry.ENTRY_LENGTH - 2) {
							break Outer_Loop3;
						}
					}

				}
			}
			Entry manualEntry = new Entry(); // temporary object holder.
			manualEntry.CreateNumbers(inputNumbers);
			manualEntry.setMemberId(inputMemberId);
			tempEntryList.add(manualEntry);
			this.entryNum += 1;
		}
		tempEntryList = addAutoEntries(autoEntries, inputMemberId, tempEntryList);
		latestEntriesInput = tempEntryList;
		cEntryList.addAll(tempEntryList);
	}

	/**
	 * Generalise the automated entries process.
	 * 
	 * @param autoEntries
	 * @param inputMemberId
	 * @param tempEntryList
	 * @return
	 */
	public ArrayList<Entry> addAutoEntries(int autoEntries, String inputMemberId,
			ArrayList<Entry> tempEntryList) {
		for (int aEntries = 0; aEntries < autoEntries; aEntries++) {
			AutoEntry autoEntry = new AutoEntry();
			autoEntry.createNumbers(entryNum);
			autoEntry.setMemberId(inputMemberId);
			tempEntryList.add(autoEntry);
			this.entryNum += 1;
		}
		return tempEntryList;
	}

	/**
	 * Generalise the automated drawing process.
	 * 
	 * @param id
	 * @return
	 */
	public AutoEntry drawLuckyNumbers(int id) {
		AutoEntry luckyEntry = new AutoEntry();
		luckyEntry.createNumbers(id);
		return luckyEntry;
	}

	/**
	 * 
	 */
	public void drawWinners() {
		if (cEntryList.size() == 0) {
			System.out.println("The current competition has no entries yet!");
		} else {
			System.out.println(
					"Lucky entry for Competition ID: " + id + ", Competition Name: " + name);
			ArrayList<Entry> luckyDraw = new ArrayList<Entry>(1);
			luckyDraw.add(drawLuckyNumbers(id));
			latestEntriesInput = luckyDraw;
			active = false;
			System.out.print("Numbers:");
			luckyDraw.get(0).printNumbers();
			System.out.println();
			for (Entry entry : cEntryList) {
				int prizeDivision = Entry.ENTRY_LENGTH + 1;
				for (int i = 0; i < Entry.ENTRY_LENGTH; i++) {
					for (int j = 0; j < Entry.ENTRY_LENGTH; j++) {
						if (entry.getNumbers()[i] == luckyDraw.get(0).getNumbers()[j]) {
							prizeDivision--;
						}
					}
				}
				if (prizeDivision <= 6) {
					entry.setPrizeDivision(prizeDivision);
					wEntryList.add(entry);
				}
			}
			System.out.println("Winning entries:");
			Outer_Loop4: for (int x = 0; x < wEntryList.size(); x++) {
				for (int y = 0; y < wEntryList.size(); y++) {
					if (wEntryList.get(x).getMemberId() == wEntryList.get(y).getMemberId()) {
						if (wEntryList.get(x).getPrizeDivision() > wEntryList.get(y)
								.getPrizeDivision()) {
							continue Outer_Loop4;
						} else if (wEntryList.get(x).getPrizeDivision() == wEntryList.get(y)
								.getPrizeDivision()
								&& wEntryList.get(x).getEntryId() > wEntryList.get(y)
										.getEntryId()) {
							continue Outer_Loop4;
						}
					}
				}

				totalPrize += wEntryList.get(x).getPrize();
				wEntryNum += 1;
				System.out.print("Member ID: " + wEntryList.get(x).getMemberId());
				System.out.printf(", Entry ID: %-6d", wEntryList.get(x).getEntryId());
				System.out.printf(", Prize: %-5d", wEntryList.get(x).getPrize());
				System.out.print(", Numbers:");
				wEntryList.get(x).printNumbers();
				System.out.println();
			}
		}
	}

	public void report() {
		System.out.println();
		if (active) {
			System.out.println("Competition ID: " + id + ", name: " + name + ", active: yes");
			System.out.println("Number of entries: " + cEntryList.size());
		} else {
			System.out.println("Competition ID: " + id + ", name: " + name + ", active: no");
			System.out.println("Number of entries: " + cEntryList.size());
			System.out.println("Number of winning entries: " + wEntryNum);
			System.out.println("Total awarded prizes: " + totalPrize);
		}
	}
}
