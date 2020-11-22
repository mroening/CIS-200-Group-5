import java.util.*;

public class  Event{
	protected Player player;
	protected String description;
	protected Reward reward;
	
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
	public Event(Player p, String d,Reward r) {
		player = p;
		description = d;
		reward = r;
	}
}