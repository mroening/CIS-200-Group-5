import java.util.*;

public class  Choice extends Event {
	private String optionA;
	private String optionB;
	private String resolutionA;
	private String resolutionB;
	
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
		super(p,des,r,message);
		optionA = a;
		optionB = b;
	}
	public void setResolution(String a, String b) {
		resolutionA = a;
		resolutionB = b;
	}
	/* Method that allows the user to enter which option they want to take*/
	public boolean userChoice() {
		Scanner s = new Scanner(System.in);
		System.out.println(description);
		System.out.println("Do you choose to:\n\tA: "+optionA+"\n\t\tor\n\tB: "+optionB);
		String reply = s.nextLine();
		
		if(reply.equalsIgnoreCase("a")) {
			System.out.println(resolutionA);
			if(!reward.getType().equals("NONE")) {
				System.out.println(rewardMessage);
				player.addReward(reward);
			}
			return false;
		} else if(reply.equalsIgnoreCase("b")) {
			System.out.println(resolutionB);
			return true;
		} else {
			return false;
		}
	}
}