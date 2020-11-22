import java.util.*;

public class  Choice extends Event {
	private String optionA;
	private String optionB;
	
	public Choice() {
		super();
		optionA = "N/A";
		optionB = "N/A";
	}
	public Choice(Player p,String d, String a, String b) {
		super(p,d);
		optionA = a;
		optionB = b;
	}
	public Choice(Player p,String d, String a, String b, Reward r) {
		super(p,d,r);
		optionA = a;
		optionB = b;
	}
	public Reward getReward() {
		return reward;
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