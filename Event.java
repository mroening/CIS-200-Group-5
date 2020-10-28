import java.util.*;

public class  Event{
	protected Player playerInfo;
	protected String description;
	//protected Reward reward;
	
	public Event() {
		playerInfo = new Player();
		description = "No description";
	}
	public Event(Player p, String d) {
		playerInfo = p;
		description = d;
	}
}