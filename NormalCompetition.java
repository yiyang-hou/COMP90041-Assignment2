
/** 
 * @author Yiyang Hou 
 * Student ID: 1202537 
 * Username: yiyhou1
 */
import java.util.ArrayList;

/**
 * This class defines competition objects for normal mode and is inherited from
 * Competition class.
 * 
 * @author houyi
 *
 */
public class NormalCompetition extends Competition {
	public NormalCompetition(int id, String name) {
		super(id, name);
	}

	/**
	 * This method creates uncontrolled random numbers.
	 * 
	 * @param autoEntries
	 * @param inputMemberId
	 * @param tempEntryList
	 * @return
	 */
	@Override
	public ArrayList<Entry> addAutoEntries(int autoEntries, String inputMemberId,
			ArrayList<Entry> tempEntryList) {
		for (int aEntries = 0; aEntries < autoEntries; aEntries++) {
			AutoEntry autoEntry = new AutoEntry();
			autoEntry.createNumbers();
			autoEntry.setMemberId(inputMemberId);
			tempEntryList.add(autoEntry);
			setEntryNum(getEntryNum() + 1);
		}
		return tempEntryList;
	}

	/**
	 * This method creates uncontrolled random numbers.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public AutoEntry drawLuckyNumbers(int id) {
		AutoEntry luckyEntry = new AutoEntry();
		luckyEntry.createNumbers();
		return luckyEntry;
	}

}
