/* Joel Hervey - Event.java
*	A super class that has little functionality other than returning values. Used for common parameters of both the 
*	Trap and Choice subclasses
*/
import java.util.*;
public class  Event{
	protected Player player;
	protected String description;
	protected Reward reward;
	protected String location;
	protected String rewardMessage;
	/**No param constructor
	*
	*/
	public Event() {
		player = new Player();
		description = "No description";
		reward = null;
	}
	/**Constructor that intializes the player and description of the event
	*@param p Player object assigned to the event
	*@param d Description of the event
	*/
	public Event(Player p, String d) {
		player = p;
		description = d;
		reward = null;
	}
	/**Constructor that intializes the player and description of the event, as well as both the reward
	* and the message that is shown when the reward is given
	* 
	*@param p Player object assigned to the event
	*@param d Description of the event
	*@param r Reward to give the player
	*@param rew Message to show when the reward is given
	*/
	public Event(Player p, String d,Reward r, String rew) {
		player = p;
		description = d;
		reward = r;
		rewardMessage = rew;
		
	}
	/** Sets the location of the trap based on story flags
	* @param loc The location, according to the values set in the story
	*/
	public void setLocation(String loc) {
		location = loc;
	}
	/** getReward gets the reward attached to the Event object
	*@return reward The reward on the current Event
	*/
	public Reward getReward() {
		return reward;
	}
	/** setPlayer changes the player to be something new
	* @param p The player to assign to the event
	*/
	public void setPlayer (Player p) {
		this.player = p;
	}
	/** setReward changes the reward to be a new reward
	* @param r The reward to assign to the event
	*/
	public void setReward (Reward r) {
		this.reward = r;
	}
}