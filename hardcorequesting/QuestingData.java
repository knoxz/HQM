//Add Import
import hardcorequesting.Team.PlayerEntry;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Austauschen
public class QuestingData implements Serializable {

//Constructor am Anfang adden
public QuestingData(Team team, int lives, String name, List<GroupData> groupData, int selectedQuest,
			int selectedTask, boolean playedLore, boolean receivedBook, DeathStats deathStat) {
		super();
		this.team = team;
		this.lives = lives;
		this.name = name;
		this.groupData = groupData;
		this.selectedQuest = selectedQuest;
		this.selectedTask = selectedTask;
		this.playedLore = playedLore;
		this.receivedBook = receivedBook;
		this.deathStat = deathStat;
	}

//Hier passiert die Magie!
//Funktion Austauschen!! WICHTIG es gibt 2 mit dem selben Namen! Achten das der Parameter richtig ist.
public static QuestingData getQuestingData(String name) {
		File file = new File(HardcoreQuesting.savedWorldPath, playerPath + name + ".qd");
		boolean containskey = data.containsKey(name);
		if (!containskey && !HardcoreQuesting.loaded.containsKey(name) && file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				QuestingData result = (QuestingData) ois.readObject();
				ois.close();

				System.out.println("HQM DEBUG: loading playerfile from: " + name);
//				System.out.println("LOADED QUESTDATA BEFORE VOID:");
//				System.out.println(result.getTeam().getQuestData());
				if (!result.team.isSingle()) {
					System.out.println("HQM DEBUG: and voiding");
//					result.setTeam(result.voidTeamData(name));
//					System.out.println("LOADED QUESTDATA AFTER VOID:");
//					System.out.println(result.getTeam().getQuestData());
				}

				data.put(name, result);

				HardcoreQuesting.loaded.put(name, true);
				containskey = true;
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (!data.containsKey(name)) {
			new QuestingData(name);
		}
		HardcoreQuesting.loaded.put(name, true);
		return data.get(name);
	}
	
	//Adden hinter private static final String path = "HardcoreQuesting/players.dat";
	private static final String playerPath = "HardcoreQuesting/";
	
	
	//Nächsten 3 Funktionen hinter save(file worldPath...  Funktion hinzufügen
	public static void savePlayerData(String name) {
		// System.out.println("Player " + name + " leaving -> saving");
		try {
			File file = new File(HardcoreQuesting.savedWorldPath, playerPath + name + ".qd");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			QuestingData d = data.get(name);
			oos.writeObject(d);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Team voidTeamData(String playerName) {
		List<QuestData> oldQuestData = team.getQuestData();
		int id = 0;
		for (PlayerEntry player : team.getPlayers()) {
			if (player.isInTeam()) {
				if (player.getName().equals(playerName)) {
					Team leaveTeam = new Team(playerName);
					// leaveTeam.getPlayers().get(0).setBookOpen(player.bookOpen);
					for (int i = 0; i < oldQuestData.size(); i++) {
						QuestData leaveData = leaveTeam.getQuestData().get(i);
						QuestData data = oldQuestData.get(i);
						if (data != null) {
							boolean[] old = data.reward;
							data.reward = new boolean[old.length - 1];
							for (int j = 0; j < data.reward.length; j++) {
								if (j < id) {
									data.reward[j] = old[j];
								} else {
									data.reward[j] = old[j + 1];
								}
							}

							leaveData.reward[0] = old[id];
						}
					}

					team.getPlayers().remove(id);

					for (int i = 0; i < oldQuestData.size(); i++) {
						QuestData leaveData = leaveTeam.getQuestData().get(i);
						QuestData data = oldQuestData.get(i);
						if (data != null && Quest.getQuest(i) != null) {
							Quest.getQuest(i).copyProgress(leaveData, data);
						}
					}

					for (int i = 0; i < Reputation.size(); i++) {
						Reputation reputation = Reputation.getReputation(i);
						if (reputation != null) {
							leaveTeam.setReputation(reputation, team.getReputation(reputation));
						}
					}

					return leaveTeam;
				}
				id++;
			}
		}
		System.out.println("HQM DEBUG: IF THIS MESSAGE IS SHOWN, SOMETHING WENT WRONG!!");
		return team;
	}
	
	public Team getTeam2() {
		// if (!team.isSingle() && !getTeams().isEmpty()) {
		// team = getTeams().get(team.getId());
		// }
		return team;
	}
	
	
	
	//Funktion überschreiben
	public static void saveAllData(DataWriter dw) {
		dw.writeBoolean(isHardcoreActive());
		dw.writeBoolean(isQuestActive());

		dw.writeData(teams.size(), DataBitHelper.TEAMS);
		for (Team team : teams) {
			team.saveData(dw, false);
		}

		dw.writeData(data.values().size(), DataBitHelper.PLAYERS);
		for (QuestingData d : data.values()) {

			dw.writeString(d.name, DataBitHelper.NAME_LENGTH);
			d.saveData(dw, false);

		}
		List<EntityPlayerMP> onlinePlayer = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		for (EntityPlayerMP object : onlinePlayer) {
			String onlinep = object.getDisplayName();
			// System.out.println("ONLINEPLAYER DISPLAY NAME: "+ onlinep);
			try {
				File file = new File(HardcoreQuesting.savedWorldPath, playerPath + onlinep + ".qd");
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				QuestingData saveObject = data.get(onlinep);
				System.out.println("HQM DEBUG: SAVING PLAYERFILE: " + onlinep);
//				System.out.println("SAVEING QUESTDATA:");
//				System.out.println(saveObject.getTeam().getQuestData());
				oos.writeObject(saveObject);
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
	//GETTER UND SETTER GANZ AM SCHLUSS ADDEN!
	
		/**
	 * @return the groupData
	 */
	public List<GroupData> getGroupData() {
		return groupData;
	}

	/**
	 * @param groupData
	 *            the groupData to set
	 */
	public void setGroupData(List<GroupData> groupData) {
		this.groupData = groupData;
	}

	/**
	 * @return the selectedQuest
	 */
	public int getSelectedQuest() {
		return selectedQuest;
	}

	/**
	 * @param selectedQuest
	 *            the selectedQuest to set
	 */
	public void setSelectedQuest(int selectedQuest) {
		this.selectedQuest = selectedQuest;
	}

	/**
	 * @return the selectedTask
	 */
	public int getSelectedTask() {
		return selectedTask;
	}

	/**
	 * @param selectedTask
	 *            the selectedTask to set
	 */
	public void setSelectedTask(int selectedTask) {
		this.selectedTask = selectedTask;
	}

	/**
	 * @return the playedLore
	 */
	public boolean isPlayedLore() {
		return playedLore;
	}

	/**
	 * @param playedLore
	 *            the playedLore to set
	 */
	public void setPlayedLore(boolean playedLore) {
		this.playedLore = playedLore;
	}

	/**
	 * @return the receivedBook
	 */
	public boolean isReceivedBook() {
		return receivedBook;
	}

	/**
	 * @param receivedBook
	 *            the receivedBook to set
	 */
	public void setReceivedBook(boolean receivedBook) {
		this.receivedBook = receivedBook;
	}

	/**
	 * @return the defaultLives
	 */
	public static int getDefaultLives() {
		return defaultLives;
	}

	/**
	 * @param defaultLives
	 *            the defaultLives to set
	 */
	public static void setDefaultLives(int defaultLives) {
		QuestingData.defaultLives = defaultLives;
	}

	/**
	 * @return the autoHardcoreActivate
	 */
	public static boolean isAutoHardcoreActivate() {
		return autoHardcoreActivate;
	}

	/**
	 * @param autoHardcoreActivate
	 *            the autoHardcoreActivate to set
	 */
	public static void setAutoHardcoreActivate(boolean autoHardcoreActivate) {
		QuestingData.autoHardcoreActivate = autoHardcoreActivate;
	}

	/**
	 * @return the autoQuestActivate
	 */
	public static boolean isAutoQuestActivate() {
		return autoQuestActivate;
	}

	/**
	 * @param autoQuestActivate
	 *            the autoQuestActivate to set
	 */
	public static void setAutoQuestActivate(boolean autoQuestActivate) {
		QuestingData.autoQuestActivate = autoQuestActivate;
	}

	/**
	 * @return the fileVersion
	 */
	public static FileVersion getFileVersion() {
		return FILE_VERSION;
	}

	/**
	 * @return the fileHelper
	 */
	public static FileHelper getFileHelper() {
		return FILE_HELPER;
	}

	/**
	 * @return the path
	 */
	public static String getPath() {
		return path;
	}

	/**
	 * @param lives
	 *            the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param deathStat
	 *            the deathStat to set
	 */
	public void setDeathStat(DeathStats deathStat) {
		this.deathStat = deathStat;
	}

	/**
	 * @param hardcoreActive
	 *            the hardcoreActive to set
	 */
	public static void setHardcoreActive(boolean hardcoreActive) {
		QuestingData.hardcoreActive = hardcoreActive;
	}

	/**
	 * @param questActive
	 *            the questActive to set
	 */
	public static void setQuestActive(boolean questActive) {
		QuestingData.questActive = questActive;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public static void setData(HashMap<String, QuestingData> data) {
		QuestingData.data = data;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public static void setTeams(List<Team> teams) {
		QuestingData.teams = teams;
	}
	