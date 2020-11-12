import java.util.*;
public class Trap extends Event{
	private int damage;
	private String failMessage;
	private String successMessage;
	
	public Trap() {
		super();
		damage = 0;
		failMessage = "You failed";
		successMessage = "You succeded";
		
	}
	public Trap(Player p,String d, int a,String f, String s) {
		super(p,d);
		damage = a;
		failMessage = f;
		successMessage = s;
	}
	public Trap(Player p,String d, int a,String f, String s, Reward r) {
		super(p,d,r);
		damage = a;
		failMessage = f;
		successMessage = s;
	}
	public void disarm(int bound) {
		if(bound % 2 != 0) {
			bound++;
		}
		System.out.println(description);
		Random rand = new Random();
		int a = rand.nextInt(bound+1);
		int playerHealth = player.getHealth();
		if(a >= bound/2) {
			System.out.println(failMessage);
			player.setHealth(playerHealth-damage);
		} else {
			System.out.println(successMessage);
			if(reward != null) {
			player.addReward(reward);
		}
		}
		
	}
}