import java.util.*;

public class  Choice extends Event {
	private String optionA;
	private String optionB;
	
	public Choice() {
		super();
		optionA = "N/A";
		optionB = "N/A";
	}
	public Choice(Player p,String des, String a, String b) {
		super(p,des);
		optionA = a;
		optionB = b;
	}
	public Choice(Player p,String des, String a, String b, String message, Reward r) {
		super(p,d,r,message);
		optionA = a;
		optionB = b;
	}
	public boolean userChoice() {
		Scanner s = new Scanner(System.in);
		System.out.println(description);
		System.out.println("Do you choose to:\n\tA: "+optionA+"\n\t\tor\n\tB: "+optionB);
		String reply = s.nextLine();
		
		if(reply.equalsIgnoreCase("a")) {
			if(reward != null) {
				System.out.println(rewardMessage);
			}
			return false;
		} else if(reply.equalsIgnoreCase("b")) {
			return true;
		} else {
			return false;
		}
	}
}