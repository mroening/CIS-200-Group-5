import java.util.*;

public class  Event{
	protected Player player;
	protected String description;
	protected Reward reward;
	protected String location;
	protected String rewardMessage;
	
	public Event() {
		player = new Player();
		description = "No description";
		reward = null;
	}
	public Event(Player p, String d) {
		player = p;
		description = d;
		reward = null;
	}
	public Event(Player p, String d,Reward r, String rew) {
		player = p;
		description = d;
		reward = r;
		rewardMessage = rew;
	}
	public void setLocation(String loc) {
		location = loc;
	}
	public Reward getReward() {
		return reward;
	}
}