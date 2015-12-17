//Import adden
import java.io.Serializable;

// "implements Serializable" adden
public class DeathStats implements Serializable {

	public DeathStats(String name, int[] deaths, int totalDeaths) {
		super();
		this.name = name;
		this.deaths = deaths;
		this.totalDeaths = totalDeaths;
	}
	
	
	//*******************************************
	//Ab hier wieder am Ende Getter/Setter Adden
	//*******************************************
	
	/**
	 * @return the clientDeaths
	 */
	public static Map<String, DeathStats> getClientDeaths() {
		return clientDeaths;
	}

	/**
	 * @param clientDeaths the clientDeaths to set
	 */
	public static void setClientDeaths(Map<String, DeathStats> clientDeaths) {
		DeathStats.clientDeaths = clientDeaths;
	}

	/**
	 * @return the clientDeathList
	 */
	public static DeathStats[] getClientDeathList() {
		return clientDeathList;
	}

	/**
	 * @param clientDeathList the clientDeathList to set
	 */
	public static void setClientDeathList(DeathStats[] clientDeathList) {
		DeathStats.clientDeathList = clientDeathList;
	}

	/**
	 * @return the clientBest
	 */
	public static DeathStats getClientBest() {
		return clientBest;
	}

	/**
	 * @param clientBest the clientBest to set
	 */
	public static void setClientBest(DeathStats clientBest) {
		DeathStats.clientBest = clientBest;
	}

	/**
	 * @return the clientTotal
	 */
	public static DeathStats getClientTotal() {
		return clientTotal;
	}

	/**
	 * @param clientTotal the clientTotal to set
	 */
	public static void setClientTotal(DeathStats clientTotal) {
		DeathStats.clientTotal = clientTotal;
	}

	/**
	 * @return the deaths
	 */
	public int[] getDeaths() {
		return deaths;
	}

	/**
	 * @param deaths the deaths to set
	 */
	public void setDeaths(int[] deaths) {
		this.deaths = deaths;
	}

	/**
	 * @return the deathcomparator
	 */
	public static DeathComparator getDeathcomparator() {
		return deathComparator;
	}

	/**
	 * @return the deathtypecomparator
	 */
	public static DeathComparator[] getDeathtypecomparator() {
		return deathTypeComparator;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param totalDeaths the totalDeaths to set
	 */
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}