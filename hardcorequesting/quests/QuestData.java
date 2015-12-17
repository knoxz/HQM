//Add import
import java.io.Serializable;

//Mache Klasse serializable
public class QuestData implements Serializable {
	
	
	//Diesen Konstructor adden!
    public QuestData(boolean[] reward, boolean completed, boolean claimed, QuestDataTask[] tasks, boolean available,
			int time) {
		super();
		this.reward = reward;
		this.completed = completed;
		this.claimed = claimed;
		this.tasks = tasks;
		this.available = available;
		this.time = time;
	}
	
	
	
	//Ab hier einfach am Ende hinzufügen!
	/**
	 * @return the reward
	 */
	public boolean[] getReward() {
		return reward;
	}


	/**
	 * @param reward the reward to set
	 */
	public void setReward(boolean[] reward) {
		this.reward = reward;
	}


	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}


	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}


	/**
	 * @return the claimed
	 */
	public boolean isClaimed() {
		return claimed;
	}


	/**
	 * @param claimed the claimed to set
	 */
	public void setClaimed(boolean claimed) {
		this.claimed = claimed;
	}


	/**
	 * @return the tasks
	 */
	public QuestDataTask[] getTasks() {
		return tasks;
	}


	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(QuestDataTask[] tasks) {
		this.tasks = tasks;
	}


	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}


	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}


	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
}