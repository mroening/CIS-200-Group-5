import java.util.*;

public class  Event{
	protected Player player;
	protected String description;
	protected Reward reward;
	protected String location;
	protected String rewardMessage;
	/*No param constructor
	*
	*/
	public Event() {
		player = new Player();
		description = "No description";
		reward = null;
	}
	/*Constructor that intializes the player and description of the event
	*
	*/
	public Event(Player p, String d) {
		player = p;
		description = d;
		reward = null;
	}
	/*Constructor that intializes the player and description of the event, as well as both the reward
	* and the message that is shown when the reward is given
	*/
	public Event(Player p, String d,Reward r, String rew) {
		player = p;
		description = d;
		reward = r;
		rewardMessage = rew + " You found " +reward.toString();
		
	}
	/* Sets the location of the trap based on story flags
	*
	*/
	public void setLocation(String loc) {
		location = loc;
	}
	public Reward getReward() {
		return reward;
	}
}