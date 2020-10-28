import java.util.*;

public class  Choice extends Event {
	private String optionA;
	private String optionB;
	
	public Choice() {
		super();
		playerInfo = new Player();
		//reward = null;
		description = "No description";
		optionA = "N/A";
		optionB = "N/A";
	}
	public Choice(Player p,String d, String a, String b) {
		super(p,d);
		optionA = a;
		optionB = b;
		//reward = null;
	}
	public boolean userChoice() {
		Scanner s = new Scanner(System.in);
		System.out.println(description);
		System.out.println("Do you choose to:\n\tA: "+optionA+"\n\t\tor\n\tB: "+optionB);
		String reply = s.nextLine();
		
		if(reply.equalsIgnoreCase("a")) {
			return false;
		} else if(reply.equalsIgnoreCase("b")) {
			return true;
		} else {
			return false;
		}
	}
}