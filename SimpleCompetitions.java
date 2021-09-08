
/** 
 * @author Yiyang Hou 
 * Student ID: 1202537 
 * Username: yiyhou1
 */
import java.util.Scanner;
import java.util.ArrayList;

public class SimpleCompetitions {
	private Competition newCompetition = null;
	private ArrayList<Competition> competitionList = new ArrayList<Competition>();
	private ArrayList<Entry> allEntries = new ArrayList<Entry>();
	private char mode = ' ';

	public void setMode(char setMode) {
		this.mode = setMode;
	}

	public char getMode() {
		return this.mode;
	}

	public ArrayList<Competition> getCompetitionList() {
		return competitionList;
	}

	public Competition getNewCompetition() {
		return newCompetition;
	}

	// Add the entries to the overall entry list and assign ID to each entry.
	public void addToAllEntries(ArrayList<Entry> newEntriesList) {
		int id = getCurrentIndex();
		for (Entry eachEntry : newEntriesList) {
			eachEntry.setEntryId(id += 1);
		}
		this.allEntries.addAll(newEntriesList);

	}

	public int getCurrentIndex() {
		return this.allEntries.size();
	}

	public boolean activeCompetitionCheck() {
		if (this.newCompetition != null && this.newCompetition.getActive()) {
			return true;
		} else
			return false;
	}

	public Competition addNewCompetition(String newCompetitionName) {
		int id = competitionList.size() + 1;
		if (this.mode == 'T') {
			this.newCompetition = new Competition(id, newCompetitionName);
		} else {
			this.newCompetition = new NormalCompetition(id, newCompetitionName);
		}
		competitionList.add(newCompetition);
		System.out.println("A new competition has been created!");
		System.out.println("Competition ID: " + newCompetition.getId() + ", Competition Name: "
				+ newCompetition.getName());
		return newCompetition;
	}

	public void report() {
		int compNum = getCompetitionList().size();
		int activeNum = 0;
		if (getNewCompetition().getActive()) {
			compNum -= 1;
			activeNum = 1;
		}

		System.out.println("----SUMMARY REPORT----");
		System.out.println("+Number of completed competitions: " + compNum);
		System.out.println("+Number of active competitions: " + activeNum);
		for (Competition competition : getCompetitionList()) {
			competition.report();
		}
	}

	/**
	 * Main program that uses the main SimpleCompetitions class
	 * 
	 * @param args main program arguments
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		SimpleCompetitions sc = new SimpleCompetitions();

		// Program starts here.
		System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");
		while (true) {
			System.out.println("Which mode would you like to run? (Type T for Testing, "
					+ "and N for Normal mode):");
			char mode = keyboard.next().toUpperCase().charAt(0);
			if (!(mode == 'T' || mode == 'N')) {
				System.out.println("Invalid mode! Please choose again.");
			} else {
				sc.setMode(mode);
				break;
			}
		}

		// Main menu starts from here.
		mainLoop: while (true) {
			System.out.println("Please select an option. Type 5 to exit.");
			System.out.println("1. Create a new competition");
			System.out.println("2. Add new entries");
			System.out.println("3. Draw winners");
			System.out.println("4. Get a summary report");
			System.out.println("5. Exit");

			int option = keyboard.nextInt();
			keyboard.nextLine();

			switch (option) {
			case 1:
				if (sc.activeCompetitionCheck()) {
					System.out.println("There is an active competition. SimpleCompetitions "
							+ "does not support concurrent competitions!");
					break;
				}
				System.out.println("Competition name: ");
				String newCompetitionName = keyboard.nextLine();
				sc.addNewCompetition(newCompetitionName);
				break;
			case 2:
				if (!sc.activeCompetitionCheck()) {
					System.out.println("There is no active competition. Please create one!");
					break;
				}
				Outer_Loop1: while (true) {
					sc.getNewCompetition().addEntries(keyboard);
					if (sc.getNewCompetition().getSuccessAdd()) {
						sc.addToAllEntries(sc.getNewCompetition().getLatestEntriesInput());
						System.out.println("The following entries have been added:");
						for (Entry eachEntry : sc.getNewCompetition().getLatestEntriesInput()) {
							System.out.printf("Entry ID: %-7dNumbers:", eachEntry.getEntryId());
							eachEntry.printNumbers();
							System.out.println();
						}
					}
					while (true) {
						System.out.println("Add more entries (Y/N)?");
						char ifMore = keyboard.next().toUpperCase().charAt(0);
						if (ifMore == 'N') {
							break Outer_Loop1;
						} else if (ifMore != 'Y') {
							System.out.println("Unsupported option. Please try again!");
						} else
							break;
					}
				}
				break;
			case 3:
				if (!sc.activeCompetitionCheck()) {
					System.out.println("There is no active competition. Please create one!");
					break;
				}
				sc.getNewCompetition().drawWinners();
				sc.addToAllEntries(sc.getNewCompetition().getLatestEntriesInput());
				break;
			case 4:
				if (sc.getCompetitionList().size() == 0) {
					System.out.println("No competition has been created yet!");
					break;
				}
				sc.report();
				break;
			case 5:
				break mainLoop;
			default:
				System.out.println("Unsupported option. Please try again!");
				break;
			}
		}

		System.out.println("Goodbye!");
		keyboard.close();
	}
}
