//Add import
import java.io.Serializable;

//Klasse serializable machen und Konstructor adden
public class Team implements Serializable {

	public Team(RewardSetting rewardSetting, LifeSetting lifeSetting, int clientTeamLives, int id,
			List<PlayerEntry> players, List<Team> invites, String name, List<Integer> reputation,
			List<QuestData> questData) {
		super();
		this.rewardSetting = rewardSetting;
		this.lifeSetting = lifeSetting;
		this.clientTeamLives = clientTeamLives;
		this.id = id;
		this.players = players;
		this.invites = invites;
		this.name = name;
		this.reputation = reputation;
		this.questData = questData;
	}
	
	// LifeSettings Serializable machen und Getter/Setter adden
	public enum LifeSetting implements Serializable {
	/**
		 * @param title
		 *            the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @param description
		 *            the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
	// RewardSetting Serializable machen und Getter/Setter adden	
	public enum RewardSetting implements Serializable {
	/**
		 * @return the isAllModeEnabled
		 */
		public static boolean isAllModeEnabled() {
			return isAllModeEnabled;
		}

		/**
		 * @param isAllModeEnabled
		 *            the isAllModeEnabled to set
		 */
		public static void setAllModeEnabled(boolean isAllModeEnabled) {
			RewardSetting.isAllModeEnabled = isAllModeEnabled;
		}

		/**
		 * @param title
		 *            the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @param description
		 *            the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		
		
	// PlayerEntry Serializable machen und Konstructor adden
	public static class PlayerEntry implements Serializable {
		public PlayerEntry(String name, boolean inTeam, boolean owner, boolean bookOpen) {
			super();
			this.name = name;
			this.inTeam = inTeam;
			this.owner = owner;
			this.bookOpen = false;
		}
		
		
		
		//Fehlende Getter und Setter am ende von PlayerEntry adden
		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @param inTeam
		 *            the inTeam to set
		 */
		public void setInTeam(boolean inTeam) {
			this.inTeam = inTeam;
		}

		/**
		 * @param owner
		 *            the owner to set
		 */
		public void setOwner(boolean owner) {
			this.owner = owner;
		}
		
		
		
		
	//Fehlende Getter/Setter am ende der HAUPT Team-Klasse adden
	/**
	 * @return the clientTeamLives
	 */
	public int getClientTeamLives() {
		return clientTeamLives;
	}

	/**
	 * @param clientTeamLives
	 *            the clientTeamLives to set
	 */
	public void setClientTeamLives(int clientTeamLives) {
		this.clientTeamLives = clientTeamLives;
	}

	/**
	 * @return the latestError
	 */
	public static ErrorMessage getLatestError() {
		return latestError;
	}

	/**
	 * @param latestError
	 *            the latestError to set
	 */
	public static void setLatestError(ErrorMessage latestError) {
		Team.latestError = latestError;
	}

	/**
	 * @return the reputation
	 */
	public List<Integer> getReputation() {
		return reputation;
	}

	/**
	 * @param reputation
	 *            the reputation to set
	 */
	public void setReputation(List<Integer> reputation) {
		this.reputation = reputation;
	}

	/**
	 * @return the questData
	 */
	public List<QuestData> getQuestData() {
		return questData;
	}

	/**
	 * @param questData
	 *            the questData to set
	 */
	public void setQuestData(List<QuestData> questData) {
		this.questData = questData;
	}

	/**
	 * @return the reloadedInvites
	 */
	public static boolean isReloadedInvites() {
		return reloadedInvites;
	}

	/**
	 * @param reloadedInvites
	 *            the reloadedInvites to set
	 */
	public static void setReloadedInvites(boolean reloadedInvites) {
		Team.reloadedInvites = reloadedInvites;
	}

	/**
	 * @param rewardSetting
	 *            the rewardSetting to set
	 */
	public void setRewardSetting(RewardSetting rewardSetting) {
		this.rewardSetting = rewardSetting;
	}

	/**
	 * @param lifeSetting
	 *            the lifeSetting to set
	 */
	public void setLifeSetting(LifeSetting lifeSetting) {
		this.lifeSetting = lifeSetting;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<PlayerEntry> players) {
		this.players = players;
	}

	/**
	 * @param invites
	 *            the invites to set
	 */
	public void setInvites(List<Team> invites) {
		this.invites = invites;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
		
		
		
		
		